package com.wilplayer.android.data.local.dao;

import androidx.room.*;
import com.wilplayer.android.data.local.entity.*;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\f\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ \u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0013\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u001c\u0010\u001b\u001a\u00020\u000e2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u001d\u00a8\u0006\u001e"}, d2 = {"Lcom/wilplayer/android/data/local/dao/SongDao;", "", "getAllSongs", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/wilplayer/android/data/local/entity/SongEntity;", "getLikedSongs", "getSongById", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSongsByArtist", "artist", "incrementPlayCount", "", "time", "", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchLocal", "q", "setLiked", "liked", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertSong", "song", "(Lcom/wilplayer/android/data/local/entity/SongEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertSongs", "songs", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface SongDao {
    
    @androidx.room.Query(value = "SELECT * FROM songs ORDER BY lastPlayedAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.wilplayer.android.data.local.entity.SongEntity>> getAllSongs();
    
    @androidx.room.Query(value = "SELECT * FROM songs WHERE isLiked = 1 ORDER BY addedAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.wilplayer.android.data.local.entity.SongEntity>> getLikedSongs();
    
    @androidx.room.Query(value = "SELECT * FROM songs WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSongById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.wilplayer.android.data.local.entity.SongEntity> $completion);
    
    @androidx.room.Upsert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertSong(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.local.entity.SongEntity song, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Upsert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertSongs(@org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.data.local.entity.SongEntity> songs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE songs SET isLiked = :liked WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setLiked(@org.jetbrains.annotations.NotNull()
    java.lang.String id, boolean liked, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE songs SET playCount = playCount + 1, lastPlayedAt = :time WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object incrementPlayCount(@org.jetbrains.annotations.NotNull()
    java.lang.String id, long time, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM songs WHERE title LIKE \'%\' || :q || \'%\' OR artist LIKE \'%\' || :q || \'%\' ORDER BY playCount DESC LIMIT 50")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchLocal(@org.jetbrains.annotations.NotNull()
    java.lang.String q, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.wilplayer.android.data.local.entity.SongEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM songs WHERE artist = :artist ORDER BY playCount DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSongsByArtist(@org.jetbrains.annotations.NotNull()
    java.lang.String artist, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.wilplayer.android.data.local.entity.SongEntity>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}