package com.wilplayer.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilplayer.android.data.repository.MusicRepository
import com.wilplayer.android.data.repository.Result
import com.wilplayer.android.domain.model.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class SearchUiState(
    val query: String = "",
    val results: List<Song> = emptyList(),
    val trending: List<Song> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val nextPageToken: String? = null,
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MusicRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState(isLoading = true))
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init {
        loadTrending()
    }

    private fun loadTrending() {
        viewModelScope.launch {
            when (val result = repository.getTrending()) {
                is Result.Success -> _uiState.update { it.copy(trending = result.data, isLoading = false) }
                is Result.Error   -> _uiState.update { it.copy(isLoading = false, error = result.message) }
                else -> {}
            }
        }
    }

    fun onQueryChange(query: String) {
        _uiState.update { it.copy(query = query, error = null) }
        // Debounced auto-search
        searchJob?.cancel()
        if (query.length >= 2) {
            searchJob = viewModelScope.launch {
                delay(400)
                search()
            }
        }
    }

    fun search() {
        val query = _uiState.value.query.trim()
        if (query.isBlank()) return

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = repository.search(query)) {
                is Result.Success -> _uiState.update {
                    it.copy(
                        isLoading = false,
                        results = result.data.songs,
                        nextPageToken = result.data.nextPageToken
                    )
                }
                is Result.Error -> _uiState.update {
                    it.copy(isLoading = false, error = result.message)
                }
                else -> {}
            }
        }
    }

    fun loadMore() {
        val token = _uiState.value.nextPageToken ?: return
        val query = _uiState.value.query.trim().takeIf { it.isNotBlank() } ?: return

        viewModelScope.launch {
            when (val result = repository.search(query, token)) {
                is Result.Success -> _uiState.update {
                    it.copy(
                        results = it.results + result.data.songs,
                        nextPageToken = result.data.nextPageToken
                    )
                }
                else -> {}
            }
        }
    }
}
