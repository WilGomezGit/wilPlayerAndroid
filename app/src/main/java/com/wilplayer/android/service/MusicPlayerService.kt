package com.wilplayer.android.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.media3.common.*
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MusicPlayerService : MediaSessionService() {

    private var mediaSession: MediaSession? = null
    private lateinit var player: ExoPlayer

    override fun onCreate() {
        super.onCreate()
        player = ExoPlayer.Builder(this)
            .setHandleAudioBecomingNoisy(true)   // auto-pause on headphone unplug
            .setWakeMode(C.WAKE_MODE_NETWORK)    // keep CPU + network alive during playback
            .build()

        mediaSession = MediaSession.Builder(this, player)
            .setCallback(WilMediaSessionCallback())
            .build()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? =
        mediaSession

    override fun onTaskRemoved(rootIntent: Intent?) {
        val player = mediaSession?.player ?: return
        if (!player.playWhenReady || player.mediaItemCount == 0) {
            stopSelf()
        }
    }

    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        super.onDestroy()
    }

    // ── Session callback ───────────────────────────────────────────────────────

    private inner class WilMediaSessionCallback : MediaSession.Callback {
        override fun onAddMediaItems(
            mediaSession: MediaSession,
            controller: MediaSession.ControllerInfo,
            mediaItems: List<MediaItem>,
        ): ListenableFuture<List<MediaItem>> {
            // Resolve URIs before passing to player
            val resolvedItems = mediaItems.map { item ->
                item.buildUpon()
                    .setUri(item.requestMetadata.mediaUri)
                    .build()
            }
            return Futures.immediateFuture(resolvedItems)
        }
    }
}

// ── Boot Receiver ──────────────────────────────────────────────────────────────


class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // No auto-start on boot — respect user privacy
    }
}
