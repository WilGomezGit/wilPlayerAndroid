package com.wilplayer.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilplayer.android.data.repository.MusicRepository
import com.wilplayer.android.data.repository.Result
import com.wilplayer.android.domain.model.Playlist
import com.wilplayer.android.domain.model.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LibraryUiState(
    val playlists: List<Playlist> = emptyList(),
    val likedSongs: List<Song> = emptyList(),
    val artists: List<String> = emptyList(),
    val isLoading: Boolean = true,
    val artistSongs: List<Song> = emptyList(),
    val selectedArtist: String? = null,
    val isImporting: Boolean = false,
    val importError: String? = null,
)

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val repository: MusicRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LibraryUiState())
    val uiState: StateFlow<LibraryUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                repository.getAllPlaylists(),
                repository.getLikedSongs(),
                repository.getArtists(),
            ) { playlists, liked, artists ->
                _uiState.update {
                    it.copy(
                        playlists = playlists,
                        likedSongs = liked,
                        artists = artists,
                        isLoading = false,
                    )
                }
            }.collect()
        }
    }

    fun createPlaylist(name: String) {
        viewModelScope.launch {
            repository.createPlaylist(name)
        }
    }

    // ── Artist songs ──────────────────────────────────────────────────────────

    fun selectArtist(artist: String) {
        viewModelScope.launch {
            val songs = repository.getSongsByArtist(artist)
            _uiState.update { it.copy(selectedArtist = artist, artistSongs = songs) }
        }
    }

    fun clearSelectedArtist() {
        _uiState.update { it.copy(selectedArtist = null, artistSongs = emptyList()) }
    }

    // ── Import YouTube playlist ───────────────────────────────────────────────

    fun importYouTubePlaylist(url: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isImporting = true, importError = null) }
            when (val result = repository.importYouTubePlaylist(url)) {
                is Result.Success -> _uiState.update { it.copy(isImporting = false) }
                is Result.Error   -> _uiState.update {
                    it.copy(isImporting = false, importError = result.message)
                }
                else -> {}
            }
        }
    }

    fun clearImportError() {
        _uiState.update { it.copy(importError = null) }
    }
}
