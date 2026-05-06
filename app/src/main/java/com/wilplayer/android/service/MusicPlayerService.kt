package com.wilplayer.android.service

import android.content.Intent
import android.net.Uri
import androidx.media3.common.*
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.DefaultMediaNotificationProvider
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture
import com.wilplayer.android.R
import com.wilplayer.android.data.extractor.YoutubeStreamExtractor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class MusicPlayerService : MediaSessionService() {

    @Inject
    lateinit var extractor: YoutubeStreamExtractor

    private var mediaSession: MediaSession? = null
    private lateinit var player: ExoPlayer
    private val serviceScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun onCreate() {
        super.onCreate()

        // Show a rich media notification on the lock screen and notification shade.
        // Media3 calls startForeground() automatically when playing starts.
        setMediaNotificationProvider(
            DefaultMediaNotificationProvider.Builder(this)
                .setChannelName(R.string.channel_playback_name)
                .setNotificationId(1001)
                .build()
        )

        player = ExoPlayer.Builder(this)
            .setHandleAudioBecomingNoisy(true)          // pause on headphone unplug
            .setWakeMode(C.WAKE_MODE_NETWORK)           // keep network alive, screen off
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(C.USAGE_MEDIA)
                    .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                    .build(),
                /* handleAudioFocus = */ true
            )
            .build()

        // When the player transitions to the next song, resolve its stream URL in
        // the background so there is no gap when that song actually starts playing.
        player.addListener(object : Player.Listener {
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                preResolveNextItem()
            }
        })

        mediaSession = MediaSession.Builder(this, player)
            .setCallback(WilMediaSessionCallback())
            .build()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? = mediaSession

    override fun onTaskRemoved(rootIntent: Intent?) {
        val player = mediaSession?.player ?: return
        if (!player.playWhenReady || player.mediaItemCount == 0) {
            stopSelf()
        }
    }

    override fun onDestroy() {
        serviceScope.cancel()
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        super.onDestroy()
    }

    // ── Next-item URL pre-resolution ──────────────────────────────────────────

    /**
     * While the current song plays, resolve the next song's audio stream URL
     * in the background and replace the placeholder YouTube watch URL with the
     * real stream URL so ExoPlayer can load it without buffering gaps.
     */
    private fun preResolveNextItem() {
        val nextIndex = player.currentMediaItemIndex + 1
        if (nextIndex >= player.mediaItemCount) return

        val nextItem = player.getMediaItemAt(nextIndex)
        val uri = nextItem.localConfiguration?.uri ?: return
        val videoId = extractYouTubeVideoId(uri) ?: return  // null = already a stream URL

        serviceScope.launch {
            val streamUrl = extractor.extractStreamUrl(videoId) ?: return@launch
            val resolvedItem = nextItem.buildUpon()
                .setUri(Uri.parse(streamUrl))
                .build()
            withContext(Dispatchers.Main) {
                // Guard: index may have shifted if the queue changed
                if (nextIndex < player.mediaItemCount) {
                    player.replaceMediaItem(nextIndex, resolvedItem)
                }
            }
        }
    }

    /**
     * Returns the YouTube video ID if [uri] is a standard YouTube watch URL,
     * or null when [uri] is already a direct audio stream (no extraction needed).
     */
    private fun extractYouTubeVideoId(uri: Uri): String? {
        val str = uri.toString()
        return when {
            str.contains("youtube.com/watch") -> uri.getQueryParameter("v")
            str.contains("youtu.be/")         -> str.substringAfterLast("/").substringBefore("?")
            else                              -> null
        }
    }

    // ── Media session callback ────────────────────────────────────────────────

    private inner class WilMediaSessionCallback : MediaSession.Callback {
        override fun onAddMediaItems(
            mediaSession: MediaSession,
            controller: MediaSession.ControllerInfo,
            mediaItems: List<MediaItem>
        ): ListenableFuture<List<MediaItem>> {
            // Pass items through as-is; the PlayerViewModel resolves the current
            // song's URL before calling setMediaItems, and preResolveNextItem()
            // handles the rest while the current song plays.
            val passthrough = mediaItems.map { item ->
                item.buildUpon()
                    .setUri(item.localConfiguration?.uri ?: item.requestMetadata.mediaUri)
                    .build()
            }
            return Futures.immediateFuture(passthrough)
        }
    }
}
