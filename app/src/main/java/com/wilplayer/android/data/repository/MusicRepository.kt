package com.wilplayer.android.data.repository

import com.wilplayer.android.BuildConfig
import com.wilplayer.android.data.api.YouTubeApiService
import com.wilplayer.android.data.local.dao.*
import com.wilplayer.android.data.local.entity.*
import com.wilplayer.android.data.model.*
import com.wilplayer.android.domain.model.*
import com.wilplayer.android.util.DurationParser
import com.wilplayer.android.util.SmartShuffleEngine
import com.wilplayer.android.data.preferences.UserPreferences
import kotlinx.coroutines.flow.*
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable, val message: String = exception.message ?: "Error desconocido") : Result<Nothing>()
    data object Loading : Result<Nothing>()
}

@Singleton
class MusicRepository @Inject constructor(
    private val apiService: YouTubeApiService,
    private val songDao: SongDao,
    private val playlistDao: PlaylistDao,
    private val historyDao: HistoryDao,
    private val userPrefs: UserPreferences,
) {
    private suspend fun getApiKey(): String {
        return userPrefs.apiKey.first() ?: BuildConfig.YOUTUBE_API_KEY
    }

    private suspend fun getRegionCode(): String {
        return userPrefs.regionCode.first()
    }

    // ── Search ────────────────────────────────────────────────────────────────

    suspend fun search(query: String, pageToken: String? = null): Result<SearchResult> = runCatching {
        val key = getApiKey()
        val response = apiService.search(query = query, pageToken = pageToken, apiKey = key)
        val videoIds = response.items.mapNotNull { it.id.videoId }.joinToString(",")
        val details = if (videoIds.isNotBlank()) {
            apiService.getVideoDetails(videoIds = videoIds, apiKey = key)
        } else null

        val detailsMap = details?.items?.associateBy { it.id } ?: emptyMap()
        val songs = response.items.mapNotNull { item ->
            val videoId = item.id.videoId ?: return@mapNotNull null
            val detail = detailsMap[videoId]
            mapToSong(
                videoId    = videoId,
                title      = item.snippet.title,
                channelTitle = item.snippet.channelTitle,
                thumbnailUrl = item.snippet.thumbnails.high?.url ?: item.snippet.thumbnails.medium?.url ?: "",
                duration   = detail?.contentDetails?.duration ?: "",
                viewCount  = detail?.statistics?.viewCount,
            )
        }

        // Cache locally
        songDao.upsertSongs(songs.map { it.toEntity() })

        SearchResult(
            songs = songs,
            nextPageToken = response.nextPageToken,
            totalResults = response.pageInfo.totalResults
        )
    }.fold(
        onSuccess = { Result.Success(it) },
        onFailure = { Result.Error(it) }
    )

    // ── Trending ──────────────────────────────────────────────────────────────

    suspend fun getTrending(regionCode: String? = null): Result<List<Song>> = runCatching {
        val key = getApiKey()
        val region = regionCode ?: getRegionCode()
        val response = apiService.getTrending(regionCode = region, apiKey = key)
        val songs = response.items.map { item ->
            mapToSong(
                videoId      = item.id,
                title        = item.snippet?.title ?: "",
                channelTitle = item.snippet?.channelTitle ?: "",
                thumbnailUrl = item.snippet?.thumbnails?.high?.url ?: "",
                duration     = item.contentDetails?.duration ?: "",
                viewCount    = item.statistics?.viewCount,
            )
        }
        songDao.upsertSongs(songs.map { it.toEntity() })
        songs
    }.fold(
        onSuccess = { Result.Success(it) },
        onFailure = { Result.Error(it) }
    )

    // ── All Songs ─────────────────────────────────────────────────────────────

    fun getAllSongs(): Flow<List<Song>> =
        songDao.getAllSongs().map { list -> list.map { it.toDomain() } }

    fun getArtists(): Flow<List<String>> =
        songDao.getAllSongs().map { list ->
            list.map { it.artist }.distinct().filter { it.isNotBlank() }.sorted()
        }

    // ── Liked Songs ───────────────────────────────────────────────────────────

    fun getLikedSongs(): Flow<List<Song>> =
        songDao.getLikedSongs().map { list -> list.map { it.toDomain() } }

    suspend fun toggleLike(song: Song): Boolean {
        val newLiked = !song.isLiked
        songDao.upsertSong(song.copy(isLiked = newLiked).toEntity())
        return newLiked
    }

    // ── Playlists ─────────────────────────────────────────────────────────────

    fun getAllPlaylists(): Flow<List<Playlist>> =
        playlistDao.getAllPlaylists().map { list ->
            list.map {
                Playlist(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    thumbnailUrl = it.thumbnailUrl,
                    createdAt = it.createdAt,
                    isYouTubePlaylist = it.isYouTubePlaylist,
                    youtubePlaylistId = it.youtubePlaylistId,
                )
            }
        }

    fun getPlaylistWithSongs(id: String): Flow<Playlist?> =
        playlistDao.getPlaylistWithSongs(id).map { pws ->
            pws?.let {
                Playlist(
                    id = it.playlist.id,
                    name = it.playlist.name,
                    description = it.playlist.description,
                    songs = it.songs.map { s -> s.toDomain() },
                    thumbnailUrl = it.playlist.thumbnailUrl,
                    createdAt = it.playlist.createdAt,
                )
            }
        }

    suspend fun createPlaylist(name: String, description: String = ""): String {
        val id = UUID.randomUUID().toString()
        playlistDao.upsertPlaylist(
            PlaylistEntity(
                id = id,
                name = name,
                description = description,
                thumbnailUrl = "",
                createdAt = System.currentTimeMillis(),
                isYouTubePlaylist = false,
                youtubePlaylistId = null,
            )
        )
        return id
    }

    suspend fun addSongToPlaylist(playlistId: String, song: Song, position: Int) {
        songDao.upsertSong(song.toEntity())
        playlistDao.addSongToPlaylist(
            PlaylistSongCrossRef(playlistId = playlistId, songId = song.id, position = position)
        )
    }

    suspend fun getPlaylistSongCount(playlistId: String): Int {
            return playlistDao.getPlaylistSongCount(playlistId)
            }
    
    // ── Playlist helpers ─────────────────────────────────────────────────────

        /** Devuelve la cantidad de canciones en una playlist (para saber la posición siguiente). */
        suspend fun getPlaylistSongCount(playlistId: String): Int {
            return playlistDao.getPlaylistSongCount(playlistId)
        }
        
        /** Crea una playlist nueva y agrega una canción de inmediato. */
        suspend fun createPlaylistWithSong(name: String, description: String = "", song: Song) {
            val playlistId = createPlaylist(name, description)
            // La posición 0 la coloca al principio; puedes cambiarla según necesites.
            addSongToPlaylist(playlistId, song, position = 0)
        }

    // ── Play History ──────────────────────────────────────────────────────────

    suspend fun recordPlay(song: Song) {
        historyDao.recordPlay(PlayHistoryEntity(videoId = song.videoId))
        songDao.incrementPlayCount(song.id)
    }

    suspend fun recordSkip(song: Song) {
        val current = historyDao.getAllSkips().find { it.videoId == song.videoId }
        historyDao.upsertSkip(
            SkipHistoryEntity(
                videoId = song.videoId,
                skipCount = (current?.skipCount ?: 0) + 1,
            )
        )
    }

    suspend fun getSmartShuffleQueue(songs: List<Song>): List<Song> {
        val recentIds = historyDao.getRecentVideoIds(20)
        val skips = historyDao.getAllSkips().associate { it.videoId to it.skipCount }
        return SmartShuffleEngine.buildQueue(songs, recentIds, skips)
    }

    // ── Local search ──────────────────────────────────────────────────────────

    suspend fun searchLocal(query: String): List<Song> =
        songDao.searchLocal(query).map { it.toDomain() }

    // ── Mapper ────────────────────────────────────────────────────────────────

    private fun mapToSong(
        videoId: String,
        title: String,
        channelTitle: String,
        thumbnailUrl: String,
        duration: String,
        viewCount: String?,
    ): Song {
        val durationMs = if (duration.isNotBlank()) DurationParser.parseDuration(duration) else 0L
        return Song(
            id = videoId,
            videoId = videoId,
            title = cleanTitle(title),
            artist = channelTitle,
            thumbnailUrl = thumbnailUrl,
            duration = durationMs,
            durationFormatted = DurationParser.formatDuration(durationMs),
            viewCount = viewCount?.toLongOrNull() ?: 0L,
        )
    }

    private fun cleanTitle(title: String): String {
        // Remove common YouTube suffixes like (Official Video), [HD], etc.
        return title
            .replace(Regex("\\(Official (Music )?Video\\)", RegexOption.IGNORE_CASE), "")
            .replace(Regex("\\[Official (Music )?Video\\]", RegexOption.IGNORE_CASE), "")
            .replace(Regex("\\(Official Audio\\)", RegexOption.IGNORE_CASE), "")
            .replace(Regex("\\[HD\\]", RegexOption.IGNORE_CASE), "")
            .replace(Regex("\\s{2,}", RegexOption.MULTILINE), " ")
            .trim()
    }
}
