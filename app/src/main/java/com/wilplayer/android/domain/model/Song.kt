package com.wilplayer.android.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Core domain model representing a playable track.
 * Decoupled from YouTube API specifics.
 */
@Parcelize
data class Song(
    val id: String,
    val videoId: String,
    val title: String,
    val artist: String,
    val thumbnailUrl: String,
    val duration: Long = 0L,             // milliseconds
    val durationFormatted: String = "",
    val viewCount: Long = 0L,
    val isLiked: Boolean = false,
    val playCount: Int = 0,
    val lastPlayedAt: Long = 0L,
    val addedAt: Long = System.currentTimeMillis(),
    val tags: List<String> = emptyList(),
    val categoryId: String = "",
    /** Score used by the smart shuffle algorithm */
    val shuffleWeight: Float = 1.0f
) : Parcelable {

    val youtubeUrl: String get() = "https://www.youtube.com/watch?v=$videoId"

    val highResThumbnail: String
        get() = "https://img.youtube.com/vi/$videoId/maxresdefault.jpg"

    val mediumThumbnail: String
        get() = "https://img.youtube.com/vi/$videoId/hqdefault.jpg"

    companion object {
        fun empty() = Song(
            id = "",
            videoId = "",
            title = "",
            artist = "",
            thumbnailUrl = ""
        )
    }
}

/**
 * A playlist that the user creates or imports.
 */
data class Playlist(
    val id: String,
    val name: String,
    val description: String = "",
    val songs: List<Song> = emptyList(),
    val thumbnailUrl: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val isYouTubePlaylist: Boolean = false,
    val youtubePlaylistId: String? = null
) {
    val songCount: Int get() = songs.size
    val totalDuration: Long get() = songs.sumOf { it.duration }
}

/**
 * Search result container — may contain songs or playlists.
 */
data class SearchResult(
    val songs: List<Song> = emptyList(),
    val playlists: List<Playlist> = emptyList(),
    val nextPageToken: String? = null,
    val totalResults: Int = 0
)

/**
 * Player state snapshot for UI consumption.
 */
data class PlayerState(
    val currentSong: Song? = null,
    val isPlaying: Boolean = false,
    val progress: Long = 0L,           // ms
    val duration: Long = 0L,           // ms
    val shuffleMode: ShuffleMode = ShuffleMode.OFF,
    val repeatMode: RepeatMode = RepeatMode.OFF,
    val queue: List<Song> = emptyList(),
    val queueIndex: Int = 0,
    val isBuffering: Boolean = false,
    val volume: Float = 1.0f,
    val isMuted: Boolean = false,
    /** Non-null when stream extraction or playback fails; shown as a Snackbar. */
    val errorMessage: String? = null,
) {
    val progressFraction: Float
        get() = if (duration > 0) progress.toFloat() / duration.toFloat() else 0f
}

enum class ShuffleMode {
    OFF,
    /** Standard Fisher-Yates shuffle */
    STANDARD,
    /**
     * WilPlayer Smart Shuffle™
     * Weighted randomization based on:
     *  - Play history (songs rarely played get higher weight)
     *  - Tags / genres (avoids clustering too many similar songs)
     *  - Last-played recency (avoids recently played for N songs)
     *  - Time-of-day affinity (build up over time)
     *  - User's explicit skips (penalizes skipped songs temporarily)
     */
    SMART
}

enum class RepeatMode {
    OFF,
    REPEAT_ONE,
    REPEAT_ALL
}
