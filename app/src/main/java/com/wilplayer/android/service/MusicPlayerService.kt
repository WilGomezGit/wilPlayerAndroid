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

    /**
     * While the current song plays, resolve the next 3 songs' audio stream URLs
     * in parallel so ExoPlayer can load them without buffering gaps.
     */
    private fun preResolveNextItem() {
        val currentIndex = player.currentMediaItemIndex
        val totalItems = player.mediaItemCount

        // Collect up to 3 upcoming video IDs that still have placeholder YouTube URLs
        val upcomingIds = (1..3).mapNotNull { offset ->
            val idx = currentIndex + offset
            if (idx >= totalItems) return@mapNotNull null
            val item = player.getMediaItemAt(idx)
            val uri = item.localConfiguration?.uri ?: return@mapNotNull null
            extractYouTubeVideoId(uri)?.let { videoId -> Pair(idx, videoId) }
        }

        if (upcomingIds.isEmpty()) return

        serviceScope.launch {
            // Preload all 3 in parallel via preloadBatch
            extractor.preloadBatch(upcomingIds.map { it.second })

            // Replace placeholder URIs with resolved stream URLs on Main thread
            withContext(Dispatchers.Main) {
                upcomingIds.forEach { (idx, videoId) ->
                    val streamUrl = extractor.extractStreamUrl(videoId) ?: return@forEach
                    if (idx < player.mediaItemCount) {
                        val item = player.getMediaItemAt(idx)
                        player.replaceMediaItem(idx, item.buildUpon().setUri(Uri.parse(streamUrl)).build())
                    }
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
