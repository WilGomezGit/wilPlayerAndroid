package com.wilplayer.android.data.local.dao

import androidx.room.*
import com.wilplayer.android.data.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Query("SELECT * FROM songs ORDER BY lastPlayedAt DESC")
    fun getAllSongs(): Flow<List<SongEntity>>

    @Query("SELECT * FROM songs WHERE isLiked = 1 ORDER BY addedAt DESC")
    fun getLikedSongs(): Flow<List<SongEntity>>

    @Query("SELECT * FROM songs WHERE id = :id")
    suspend fun getSongById(id: String): SongEntity?

    @Upsert
    suspend fun upsertSong(song: SongEntity)

    @Upsert
    suspend fun upsertSongs(songs: List<SongEntity>)

    @Query("UPDATE songs SET isLiked = :liked WHERE id = :id")
    suspend fun setLiked(id: String, liked: Boolean)

    @Query("UPDATE songs SET playCount = playCount + 1, lastPlayedAt = :time WHERE id = :id")
    suspend fun incrementPlayCount(id: String, time: Long = System.currentTimeMillis())

    @Query("SELECT * FROM songs WHERE title LIKE '%' || :q || '%' OR artist LIKE '%' || :q || '%' ORDER BY playCount DESC LIMIT 50")
    suspend fun searchLocal(q: String): List<SongEntity>

    @Query("SELECT * FROM songs WHERE artist = :artist ORDER BY playCount DESC")
    suspend fun getSongsByArtist(artist: String): List<SongEntity>
}

@Dao
interface PlaylistDao {
    @Query("SELECT * FROM playlists ORDER BY createdAt DESC")
    fun getAllPlaylists(): Flow<List<PlaylistEntity>>

    @Transaction
    @Query("SELECT * FROM playlists WHERE id = :id")
    fun getPlaylistWithSongs(id: String): Flow<PlaylistWithSongs?>

    @Upsert
    suspend fun upsertPlaylist(playlist: PlaylistEntity)

    @Query("DELETE FROM playlists WHERE id = :id")
    suspend fun deletePlaylist(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSongToPlaylist(crossRef: PlaylistSongCrossRef)

    @Query("DELETE FROM playlist_songs WHERE playlistId = :playlistId AND songId = :songId")
    suspend fun removeSongFromPlaylist(playlistId: String, songId: String)

    @Query("UPDATE playlist_songs SET position = :position WHERE playlistId = :playlistId AND songId = :songId")
    suspend fun updateSongPosition(playlistId: String, songId: String, position: Int)

    @Query("SELECT COUNT(*) FROM playlist_songs WHERE playlistId = :playlistId")
    suspend fun getPlaylistSongCount(playlistId: String): Int
}

@Dao
interface HistoryDao {
    @Query("SELECT videoId FROM play_history ORDER BY playedAt DESC LIMIT :limit")
    suspend fun getRecentVideoIds(limit: Int = 20): List<String>

    @Insert
    suspend fun recordPlay(history: PlayHistoryEntity)

    @Query("DELETE FROM play_history WHERE playedAt < :before")
    suspend fun pruneOldHistory(before: Long)

    // Skip history
    @Upsert
    suspend fun upsertSkip(skip: SkipHistoryEntity)

    @Query("SELECT * FROM skip_history")
    suspend fun getAllSkips(): List<SkipHistoryEntity>

    @Query("UPDATE skip_history SET skipCount = MAX(0, skipCount - 1) WHERE videoId = :videoId")
    suspend fun decrementSkip(videoId: String)

    @Query("DELETE FROM skip_history WHERE skipCount <= 0")
    suspend fun pruneEmptySkips()
}
