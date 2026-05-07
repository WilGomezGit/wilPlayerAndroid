package com.wilplayer.android.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.wilplayer.android.data.local.dao.*;
import com.wilplayer.android.data.local.entity.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\t"}, d2 = {"Lcom/wilplayer/android/data/local/WilPlayerDatabase;", "Landroidx/room/RoomDatabase;", "()V", "historyDao", "Lcom/wilplayer/android/data/local/dao/HistoryDao;", "playlistDao", "Lcom/wilplayer/android/data/local/dao/PlaylistDao;", "songDao", "Lcom/wilplayer/android/data/local/dao/SongDao;", "app_debug"})
@androidx.room.Database(entities = {com.wilplayer.android.data.local.entity.SongEntity.class, com.wilplayer.android.data.local.entity.PlaylistEntity.class, com.wilplayer.android.data.local.entity.PlaylistSongCrossRef.class, com.wilplayer.android.data.local.entity.PlayHistoryEntity.class, com.wilplayer.android.data.local.entity.SkipHistoryEntity.class}, version = 2, exportSchema = false)
public abstract class WilPlayerDatabase extends androidx.room.RoomDatabase {
    
    public WilPlayerDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.wilplayer.android.data.local.dao.SongDao songDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.wilplayer.android.data.local.dao.PlaylistDao playlistDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.wilplayer.android.data.local.dao.HistoryDao historyDao();
}