package com.wilplayer.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilplayer.android.data.repository.MusicRepository
import com.wilplayer.android.domain.model.Playlist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlaylistDetailUiState(
    val playlist: Playlist? = null,
    val isLoading: Boolean = true,
)

@HiltViewModel
class PlaylistDetailViewModel @Inject constructor(
    private val repository: MusicRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlaylistDetailUiState())
    val uiState: StateFlow<PlaylistDetailUiState> = _uiState.asStateFlow()

    fun loadPlaylist(id: String) {
        viewModelScope.launch {
            repository.getPlaylistWithSongs(id).collect { playlist ->
                _uiState.update { it.copy(playlist = playlist, isLoading = false) }
            }
        }
    }
}
