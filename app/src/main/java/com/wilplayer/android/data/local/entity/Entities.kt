package com.wilplayer.android.data.local.entity

import androidx.room.*
import com.wilplayer.android.domain.model.Song

// ── Entities ──────────────────────────────────────────────────────────────────

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey val id: String,
    val videoId: String,
    val title: String,
    val artist: String,
    val thumbnailUrl: String,
    val duration: Long,
    val durationFormatted: String,
    val viewCount: Long,
    val isLiked: Boolean,
    val playCount: Int,
    val lastPlayedAt: Long,
    val addedAt: Long,
    val tags: String,        // JSON array as string
    val categoryId: String,
) {
    fun toDomain() = Song(
        id = id,
        videoId = videoId,
        title = title,
        artist = artist,
        thumbnailUrl = thumbnailUrl,
        duration = duration,
        durationFormatted = durationFormatted,
        viewCount = viewCount,
        isLiked = isLiked,
        playCount = playCount,
        lastPlayedAt = lastPlayedAt,
        addedAt = addedAt,
        tags = tags.split(",").filter { it.isNotBlank() },
        categoryId = categoryId,
    )
}

fun Song.toEntity() = SongEntity(
    id = id,
    videoId = videoId,
    title = title,
    artist = artist,
    thumbnailUrl = thumbnailUrl,
    duration = duration,
    durationFormatted = durationFormatted,
    viewCount = viewCount,
    isLiked = isLiked,
    playCount = playCount,
    lastPlayedAt = lastPlayedAt,
    addedAt = addedAt,
    tags = tags.joinToString(","),
    categoryId = categoryId,
)

@Entity(tableName = "playlists")
data class PlaylistEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val createdAt: Long,
    val isYouTubePlaylist: Boolean,
    val youtubePlaylistId: String?,
)

@Entity(
    tableName = "playlist_songs",
    primaryKeys = ["playlistId", "songId"],
    foreignKeys = [
        ForeignKey(
            entity = PlaylistEntity::class,
            parentColumns = ["id"],
            childColumns = ["playlistId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SongEntity::class,
            parentColumns = ["id"],
            childColumns = ["songId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PlaylistSongCrossRef(
    val playlistId: String,
    val songId: String,
    val position: Int,
)

data class PlaylistWithSongs(
    @Embedded val playlist: PlaylistEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = PlaylistSongCrossRef::class,
            parentColumn = "playlistId",
            entityColumn = "songId"
        )
    )
    val songs: List<SongEntity>
)

@Entity(tableName = "play_history")
data class PlayHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val videoId: String,
    val playedAt: Long = System.currentTimeMillis(),
)

@Entity(tableName = "skip_history")
data class SkipHistoryEntity(
    @PrimaryKey val videoId: String,
    val skipCount: Int,
    val lastSkippedAt: Long = System.currentTimeMillis(),
)
