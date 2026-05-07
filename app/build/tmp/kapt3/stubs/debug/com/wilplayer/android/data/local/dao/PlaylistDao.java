package com.wilplayer.android.data.local.dao;

import androidx.room.*;
import com.wilplayer.android.data.local.entity.*;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fH\'J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\f2\u0006\u0010\b\u001a\u00020\tH\'J\u001e\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0016J&\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u001c\u00a8\u0006\u001d"}, d2 = {"Lcom/wilplayer/android/data/local/dao/PlaylistDao;", "", "addSongToPlaylist", "", "crossRef", "Lcom/wilplayer/android/data/local/entity/PlaylistSongCrossRef;", "(Lcom/wilplayer/android/data/local/entity/PlaylistSongCrossRef;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePlaylist", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllPlaylists", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/wilplayer/android/data/local/entity/PlaylistEntity;", "getPlaylistSongCount", "", "playlistId", "getPlaylistWithSongs", "Lcom/wilplayer/android/data/local/entity/PlaylistWithSongs;", "removeSongFromPlaylist", "songId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSongPosition", "position", "(Ljava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertPlaylist", "playlist", "(Lcom/wilplayer/android/data/local/entity/PlaylistEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface PlaylistDao {
    
    @androidx.room.Query(value = "SELECT * FROM playlists ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.wilplayer.android.data.local.entity.PlaylistEntity>> getAllPlaylists();
    
    @androidx.room.Transaction()
    @androidx.room.Query(value = "SELECT * FROM playlists WHERE id = :id")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.wilplayer.android.data.local.entity.PlaylistWithSongs> getPlaylistWithSongs(@org.jetbrains.annotations.NotNull()
    java.lang.String id);
    
    @androidx.room.Upsert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertPlaylist(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.local.entity.PlaylistEntity playlist, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM playlists WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deletePlaylist(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addSongToPlaylist(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.local.entity.PlaylistSongCrossRef crossRef, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM playlist_songs WHERE playlistId = :playlistId AND songId = :songId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object removeSongFromPlaylist(@org.jetbrains.annotations.NotNull()
    java.lang.String playlistId, @org.jetbrains.annotations.NotNull()
    java.lang.String songId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE playlist_songs SET position = :position WHERE playlistId = :playlistId AND songId = :songId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateSongPosition(@org.jetbrains.annotations.NotNull()
    java.lang.String playlistId, @org.jetbrains.annotations.NotNull()
    java.lang.String songId, int position, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM playlist_songs WHERE playlistId = :playlistId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPlaylistSongCount(@org.jetbrains.annotations.NotNull()
    java.lang.String playlistId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}