package com.wilplayer.android.ui

import android.content.ComponentName
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.*
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.MoreExecutors
import com.wilplayer.android.data.repository.MusicRepository
import com.wilplayer.android.domain.model.*
import com.wilplayer.android.service.MusicPlayerService
import com.wilplayer.android.data.extractor.YoutubeStreamExtractor
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: MusicRepository,
    private val extractor: YoutubeStreamExtractor,
) : ViewModel() {

    // ── Player state ──────────────────────────────────────────────────────────

    private val _playerState = MutableStateFlow(PlayerState())
    val playerState: StateFlow<PlayerState> = _playerState.asStateFlow()

    private val _queue = MutableStateFlow<List<Song>>(emptyList())
    val queue: StateFlow<List<Song>> = _queue.asStateFlow()

    private var mediaController: MediaController? = null
    private var progressJob: Job? = null

    // ── Connect to MediaSession ────────────────────────────────────────────────

    fun connectPlayer() {
        val sessionToken = SessionToken(
            context,
            ComponentName(context, MusicPlayerService::class.java)
        )
        val controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()
        controllerFuture.addListener({
            mediaController = controllerFuture.get()
            setupPlayerListener()
            startProgressPolling()
        }, MoreExecutors.directExecutor())
    }

    private fun setupPlayerListener() {
        mediaController?.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) = updateState()
            override fun onIsPlayingChanged(isPlaying: Boolean) = updateState()
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) = updateState()
            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) = updateState()
            override fun onRepeatModeChanged(repeatMode: Int) = updateState()
            override fun onPlayerError(error: PlaybackException) {
                // Auto-skip on error
                skipToNext()
            }
        })
    }

    private fun updateState() {
        val mc = mediaController ?: return
        val currentSong = findSongByMediaId(mc.currentMediaItem?.mediaId)
        _playerState.update { state ->
            state.copy(
                currentSong = currentSong,
                isPlaying = mc.isPlaying,
                duration = mc.duration.coerceAtLeast(0L),
                shuffleMode = when {
                    mc.shuffleModeEnabled -> ShuffleMode.SMART
                    else -> ShuffleMode.OFF
                },
                repeatMode = when (mc.repeatMode) {
                    Player.REPEAT_MODE_ONE -> RepeatMode.REPEAT_ONE
                    Player.REPEAT_MODE_ALL -> RepeatMode.REPEAT_ALL
                    else -> RepeatMode.OFF
                },
                isBuffering = mc.playbackState == Player.STATE_BUFFERING
            )
        }
    }

    private fun startProgressPolling() {
        progressJob?.cancel()
        progressJob = viewModelScope.launch {
            while (isActive) {
                mediaController?.let { mc ->
                    _playerState.update { it.copy(progress = mc.currentPosition.coerceAtLeast(0L)) }
                }
                delay(500)
            }
        }
    }

    // ── Playback commands ─────────────────────────────────────────────────────

    fun playSong(song: Song, queue: List<Song> = listOf(song)) {
        val mc = mediaController ?: return
        viewModelScope.launch {
            _playerState.update { it.copy(isBuffering = true, currentSong = song) }
            repository.recordPlay(song)

            // Extract stream URL for the selected song
            val streamUrl = extractor.extractStreamUrl(song.videoId)
            if (streamUrl == null) {
                _playerState.update { it.copy(isBuffering = false) }
                // Handle error or skip
                return@launch
            }

            val mediaItems = queue.map { s ->
                val uri = if (s.id == song.id) streamUrl else "https://www.youtube.com/watch?v=${s.videoId}"
                MediaItem.Builder()
                    .setMediaId(s.id)
                    .setUri(uri)
                    .setMediaMetadata(
                        MediaMetadata.Builder()
                            .setTitle(s.title)
                            .setArtist(s.artist)
                            .setArtworkUri(android.net.Uri.parse(s.thumbnailUrl))
                            .build()
                    )
                    .build()
            }

            val startIndex = queue.indexOfFirst { it.id == song.id }.coerceAtLeast(0)
            _queue.value = queue

            mc.setMediaItems(mediaItems, startIndex, 0L)
            mc.prepare()
            mc.play()
            _playerState.update { it.copy(isBuffering = false) }
        }
    }

    fun playPause() {
        val mc = mediaController ?: return
        if (mc.isPlaying) mc.pause() else mc.play()
    }

    fun skipToNext() {
        val mc = mediaController ?: return
        viewModelScope.launch {
            _playerState.value.currentSong?.let { repository.recordSkip(it) }
        }
        if (mc.hasNextMediaItem()) mc.seekToNextMediaItem()
    }

    fun skipToPrevious() {
        val mc = mediaController ?: return
        if (mc.currentPosition > 3000L) {
            mc.seekTo(0L)
        } else if (mc.hasPreviousMediaItem()) {
            mc.seekToPreviousMediaItem()
        }
    }

    fun seekTo(fraction: Float) {
        val mc = mediaController ?: return
        val duration = mc.duration
        if (duration > 0) mc.seekTo((duration * fraction).toLong())
    }

    fun toggleRepeat() {
        val mc = mediaController ?: return
        mc.repeatMode = when (mc.repeatMode) {
            Player.REPEAT_MODE_OFF -> Player.REPEAT_MODE_ALL
            Player.REPEAT_MODE_ALL -> Player.REPEAT_MODE_ONE
            else -> Player.REPEAT_MODE_OFF
        }
    }

    fun toggleShuffle() {
        val mc = mediaController ?: return
        val currentMode = _playerState.value.shuffleMode
        when (currentMode) {
            ShuffleMode.OFF -> enableSmartShuffle()
            ShuffleMode.SMART -> {
                mc.shuffleModeEnabled = false
                _playerState.update { it.copy(shuffleMode = ShuffleMode.OFF) }
            }
            else -> {
                mc.shuffleModeEnabled = false
                _playerState.update { it.copy(shuffleMode = ShuffleMode.OFF) }
            }
        }
    }

    private fun enableSmartShuffle() {
        viewModelScope.launch {
            val currentQueue = _queue.value
            if (currentQueue.isEmpty()) return@launch

            val smartQueue = repository.getSmartShuffleQueue(currentQueue)
            val currentSong = _playerState.value.currentSong

            val mediaItems = smartQueue.map { s ->
                MediaItem.Builder()
                    .setMediaId(s.id)
                    .setUri("https://www.youtube.com/watch?v=${s.videoId}")
                    .setMediaMetadata(
                        MediaMetadata.Builder()
                            .setTitle(s.title)
                            .setArtist(s.artist)
                            .build()
                    )
                    .build()
            }

            _queue.value = smartQueue
            mediaController?.let { mc ->
                val currentPos = mc.currentPosition
                val startIdx = smartQueue.indexOfFirst { it.id == currentSong?.id }.coerceAtLeast(0)
                mc.setMediaItems(mediaItems, startIdx, currentPos)
                mc.shuffleModeEnabled = true
            }

            _playerState.update { it.copy(shuffleMode = ShuffleMode.SMART) }
        }
    }

    fun toggleLike(song: Song) {
        viewModelScope.launch {
            val newLiked = repository.toggleLike(song)
            if (_playerState.value.currentSong?.id == song.id) {
                _playerState.update { it.copy(currentSong = song.copy(isLiked = newLiked)) }
            }
        }
    }

    private fun findSongByMediaId(mediaId: String?): Song? {
        if (mediaId == null) return null
        return _queue.value.find { it.id == mediaId }
    }

    override fun onCleared() {
        progressJob?.cancel()
        mediaController?.release()
        super.onCleared()
    }
}
