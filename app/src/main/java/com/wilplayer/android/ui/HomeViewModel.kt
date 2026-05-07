package com.wilplayer.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilplayer.android.data.repository.MusicRepository
import com.wilplayer.android.data.repository.Result
import com.wilplayer.android.domain.model.Playlist
import com.wilplayer.android.domain.model.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = false,
    val trending: List<Song> = emptyList(),
    val recentSongs: List<Song> = emptyList(),
    val recentPlaylists: List<Playlist> = emptyList(),
    val recommendations: List<Song> = emptyList(),
    val isLoadingRecs: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MusicRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = false))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        observeLibrary()
        loadTrending()
        loadRecommendations()
    }

    fun loadTrending() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            // Using a generic rock/metal search for the main trending view
            when (val result = repository.search("rock metal clásicos")) {
                is Result.Success -> _uiState.update {
                    it.copy(isLoading = false, trending = result.data.songs)
                }
                is Result.Error -> _uiState.update {
                    it.copy(isLoading = false, error = result.message)
                }
                else -> {}
            }
        }
    }

    /**
     * Loads Rock/Metal recommendations from 3 parallel searches.
     * Excludes duplicates and caps at 20 songs.
     */
    fun loadRecommendations() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingRecs = true) }
            try {
                val queries = listOf(
                    "Rock en español clásicos",
                    "Heavy Metal best songs",
                    "Metal rock internacional",
                )
                // Launch all 3 searches in parallel
                val deferred = queries.map { q ->
                    async {
                        when (val r = repository.search(q)) {
                            is Result.Success -> r.data.songs
                            else -> emptyList()
                        }
                    }
                }
                val combined = deferred
                    .flatMap { it.await() }
                    .distinctBy { it.videoId }
                    // Filter out anything reggaeton-related in title/artist
                    .filter { song ->
                        val lower = (song.title + song.artist).lowercase()
                        !lower.contains("reggaet") && !lower.contains("reguet")
                    }
                    .take(20)

                _uiState.update { it.copy(isLoadingRecs = false, recommendations = combined) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoadingRecs = false) }
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

    fun refresh() {
        loadTrending()
        loadRecommendations()
    }

    fun searchByMood(mood: String) {
        viewModelScope.launch {
            when (val result = repository.search(mood)) {
                is Result.Success -> _uiState.update { it.copy(trending = result.data.songs) }
                is Result.Error   -> _uiState.update { it.copy(error = result.message) }
                else -> {}
            }
        }
    }
}
