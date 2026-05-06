package com.wilplayer.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilplayer.android.data.extractor.YoutubeStreamExtractor
import com.wilplayer.android.data.preferences.UserPreferences
import com.wilplayer.android.data.repository.MusicRepository
import com.wilplayer.android.data.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userPrefs: UserPreferences,
    private val repository: MusicRepository,
    private val extractor: YoutubeStreamExtractor,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                userPrefs.apiKey,
                userPrefs.shuffleQuality,
                userPrefs.regionCode,
                repository.getAllPlaylists(),
                repository.getAllSongs(),
            ) { key, quality, region, playlists, songs ->
                _uiState.update {
                    it.copy(
                        apiKey = key ?: "",
                        shuffleQuality = quality,
                        regionCode = region,
                        playlistCount = playlists.size,
                        songCount = songs.size,
                    )
                }
            }.collect()
        }
    }

    fun saveApiKey(key: String) {
        viewModelScope.launch {
            userPrefs.saveApiKey(key)
            _uiState.update { it.copy(isApiKeyValid = null) }
        }
    }

    fun testApiKey() {
        viewModelScope.launch {
            _uiState.update { it.copy(isCheckingKey = true) }
            // Test by fetching trending
            when (repository.getTrending()) {
                is Result.Success -> _uiState.update { it.copy(isApiKeyValid = true, isCheckingKey = false) }
                is Result.Error -> _uiState.update { it.copy(isApiKeyValid = false, isCheckingKey = false) }
                else -> _uiState.update { it.copy(isCheckingKey = false) }
            }
        }
    }

    fun saveShuffleQuality(quality: String) {
        viewModelScope.launch {
            userPrefs.saveShuffleQuality(quality)
        }
    }

    /** Clears the in-memory stream URL cache so the next play re-extracts fresh URLs. */
    fun clearStreamCache() {
        extractor.clearCache()
    }
}
