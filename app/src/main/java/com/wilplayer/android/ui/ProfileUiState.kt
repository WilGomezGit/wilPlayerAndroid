package com.wilplayer.android.ui

data class ProfileUiState(
    val apiKey: String = "",
    val isApiKeyValid: Boolean? = null,
    val shuffleQuality: String = "MEDIUM",
    val regionCode: String = "CO",
    val songCount: Int = 0,
    val playlistCount: Int = 0,
    val isCheckingKey: Boolean = false,
)
