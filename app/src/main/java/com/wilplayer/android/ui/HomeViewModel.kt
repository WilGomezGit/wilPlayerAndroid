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

data class HomeUiState(
    val isLoading: Boolean = false,
    val trending: List<Song> = emptyList(),
    val recentSongs: List<Song> = emptyList(),
    val recentPlaylists: List<Playlist> = emptyList(),
    val error: String? = null,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MusicRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadData()
        observeLibrary()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = repository.getTrending()) {
                is Result.Success -> _uiState.update {
                    it.copy(isLoading = false, trending = result.data)
                }
                is Result.Error -> _uiState.update {
                    it.copy(isLoading = false, error = result.message)
                }
                else -> {}
            }
        }
    }

    private fun observeLibrary() {
        viewModelScope.launch {
            repository.getAllPlaylists()
                .collect { playlists ->
                    _uiState.update { it.copy(recentPlaylists = playlists.take(4)) }
                }
        }
    }

    fun refresh() = loadData()

    fun searchByMood(mood: String) {
        viewModelScope.launch {
            when (val result = repository.search(mood)) {
                is Result.Success -> _uiState.update { it.copy(trending = result.data.songs) }
                is Result.Error -> _uiState.update { it.copy(error = result.message) }
                else -> {}
            }
        }
    }
}
