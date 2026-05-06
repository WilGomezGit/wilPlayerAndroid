package com.wilplayer.android.ui

import android.content.ComponentName
import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.*
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.MoreExecutors
import com.wilplayer.android.data.extractor.YoutubeStreamExtractor
import com.wilplayer.android.data.repository.MusicRepository
import com.wilplayer.android.domain.model.*
import com.wilplayer.android.service.MusicPlayerService
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

    private val _playerState = MutableStateFlow(PlayerState())
    val playerState: StateFlow<PlayerState> = _playerState.asStateFlow()

    private val _queue = MutableStateFlow<List<Song>>(emptyList())
    val queue: StateFlow<List<Song>> = _queue.asStateFlow()

    private var mediaController: MediaController? = null
    private var progressJob: Job? = null
    private var pendingPlay: Pair<Song, List<Song>>? = null

    // ── Playlist dialog state ─────────────────────────────────────────────────
    private val _allPlaylists = MutableStateFlow<List<Playlist>>(emptyList())
    val allPlaylists: StateFlow<List<Playlist>> = _allPlaylists.asStateFlow()

    private val _showAddToPlaylistDialog = MutableStateFlow<Song?>(null)
    val showAddToPlaylistDialog: StateFlow<Song?> = _showAddToPlaylistDialog.asStateFlow()

    fun connectPlayer() {
        val sessionToken = SessionToken(
            context,
            ComponentName(context, MusicPlayerService::class.java)
        )
        val controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()
        controllerFuture.addListener({
            try {
                mediaController = controllerFuture.get()
                setupPlayerListener()
                startProgressPolling()
                loadAllPlaylists()
                pendingPlay?.let { (song, q) ->
                    pendingPlay = null
                    playSong(song, q)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _playerState.update {
                    it.copy(isBuffering = false, errorMessage = "No se pudo conectar al reproductor")
                }
            }
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
                val failedVideoId = _playerState.value.currentSong?.videoId
                if (failedVideoId != null) extractor.invalidate(failedVideoId)
                _playerState.update { it.copy(errorMessage = "Error al reproducir. Saltando...") }
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

    fun playSong(song: Song, queue: List<Song> = listOf(song)) {
        val mc = mediaController
        if (mc == null) {
            pendingPlay = song to queue
            _playerState.update { it.copy(isBuffering = true, currentSong = song, errorMessage = null) }
            return
        }

        viewModelScope.launch {
            _playerState.update { it.copy(isBuffering = true, currentSong = song, errorMessage = null) }
            repository.recordPlay(song)

            val streamUrl = try {
                extractor.extractStreamUrl(song.videoId)
            } catch (e: Exception) {
                _playerState.update {
                    it.copy(
                        isBuffering = false,
                        errorMessage = "Error: ${e.message ?: "desconocido"}"
                    )
                }
                return@launch
            }

            if (streamUrl == null) {
                _playerState.update {
                    it.copy(
                        isBuffering = false,
                        errorMessage = "No se pudo obtener el stream (URL nula)."
                    )
                }
                return@launch
            }

            val mediaItems = queue.map { s ->
                val uri = if (s.id == song.id) streamUrl
                          else "https://www.youtube.com/watch?v=${s.videoId}"
                MediaItem.Builder()
                    .setMediaId(s.id)
                    .setUri(Uri.parse(uri))
                    .setMediaMetadata(
                        MediaMetadata.Builder()
                            .setTitle(s.title)
                            .setArtist(s.artist)
                            .setArtworkUri(Uri.parse(s.thumbnailUrl))
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
            else -> {
                mc.shuffleModeEnabled = false
                _playerState.update { it.copy(shuffleMode = ShuffleMode.OFF) }
            }
        }
    }

       private fun enableSmartShuffle() {
        viewModelScope.launch {
            val mc = mediaController ?: return@launch
            val currentQueue = _queue.value
            if (currentQueue.isEmpty()) return@launch
    
            _playerState.update { it.copy(isBuffering = true) }
    
            // Resuelve las URLs de todas las canciones de la cola para evitar fallos
            val resolvedItems = currentQueue.map { song ->
                val streamUrl = try {
                    extractor.extractStreamUrl(song.videoId)
                } catch (e: Exception) {
                    null
                }
                val uri = if (streamUrl != null) streamUrl
                          else "https://www.youtube.com/watch?v=${song.videoId}"
                MediaItem.Builder()
                    .setMediaId(song.id)
                    .setUri(Uri.parse(uri))
                    .setMediaMetadata(
                        MediaMetadata.Builder()
                            .setTitle(song.title)
                            .setArtist(song.artist)
                            .setArtworkUri(Uri.parse(song.thumbnailUrl))
                            .build()
                    )
                    .build()
            }
    
            mc.setMediaItems(resolvedItems, 0, 0L)
            mc.shuffleModeEnabled = true
            mc.prepare()
            mc.play()
            _playerState.update { it.copy(isBuffering = false, shuffleMode = ShuffleMode.SMART) }
        }
    }

    fun toggleLike(song: Song) {
        viewModelScope.launch {
            val newLiked = repository.toggleLike(song)
            _queue.update { list ->
                list.map { if (it.id == song.id) it.copy(isLiked = newLiked) else it }
            }
            if (_playerState.value.currentSong?.id == song.id) {
                _playerState.update { it.copy(currentSong = song.copy(isLiked = newLiked)) }
            }
        }
    }

    fun addToQueue(song: Song) {
        val mc = mediaController ?: return
        val mediaItem = MediaItem.Builder()
            .setMediaId(song.id)
            .setUri(Uri.parse("https://www.youtube.com/watch?v=${song.videoId}"))
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setTitle(song.title)
                    .setArtist(song.artist)
                    .setArtworkUri(Uri.parse(song.thumbnailUrl))
                    .build()
            )
            .build()
        mc.addMediaItem(mediaItem)
        _queue.update { it + song }
    }

    // ── Playlist functions ────────────────────────────────────────────────────

    fun loadAllPlaylists() {
        viewModelScope.launch {
            repository.getAllPlaylists().collect { playlists ->
                _allPlaylists.value = playlists
            }
        }
    }

    fun onAddToPlaylistRequest(song: Song) {
        loadAllPlaylists()
        _showAddToPlaylistDialog.value = song
    }

    fun onDismissAddToPlaylistDialog() {
        _showAddToPlaylistDialog.value = null
    }

       fun addToPlaylist(playlist: Playlist, song: Song) {
        viewModelScope.launch {
            val position = repository.getPlaylistSongCount(playlist.id) + 1
            repository.addSongToPlaylist(playlist.id, song, position)
            _showAddToPlaylistDialog.value = null
        }
    }

    fun addToNewPlaylist(playlistName: String, song: Song) {
        viewModelScope.launch {
            repository.createPlaylistWithSong(playlistName, song = song)
            _showAddToPlaylistDialog.value = null
        }
    }

    fun clearError() {
        _playerState.update { it.copy(errorMessage = null) }
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
