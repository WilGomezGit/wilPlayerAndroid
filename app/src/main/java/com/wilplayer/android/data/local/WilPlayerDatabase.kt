package com.wilplayer.android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wilplayer.android.data.local.dao.*
import com.wilplayer.android.data.local.entity.*

@Database(
    entities = [
        SongEntity::class,
        PlaylistEntity::class,
        PlaylistSongCrossRef::class,
        PlayHistoryEntity::class,
        SkipHistoryEntity::class,
    ],
    version = 2,
    exportSchema = false,
)
abstract class WilPlayerDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun historyDao(): HistoryDao
}
