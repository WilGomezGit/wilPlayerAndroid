package com.wilplayer.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilplayer.android.data.repository.MusicRepository
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
}
