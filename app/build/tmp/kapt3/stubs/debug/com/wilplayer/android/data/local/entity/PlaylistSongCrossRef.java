package com.wilplayer.android.data.local.entity;

import androidx.room.*;
import com.wilplayer.android.domain.model.Song;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0016"}, d2 = {"Lcom/wilplayer/android/data/local/entity/PlaylistSongCrossRef;", "", "playlistId", "", "songId", "position", "", "(Ljava/lang/String;Ljava/lang/String;I)V", "getPlaylistId", "()Ljava/lang/String;", "getPosition", "()I", "getSongId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "playlist_songs", primaryKeys = {"playlistId", "songId"}, indices = {@androidx.room.Index(value = {"songId"})}, foreignKeys = {@androidx.room.ForeignKey(entity = com.wilplayer.android.data.local.entity.PlaylistEntity.class, parentColumns = {"id"}, childColumns = {"playlistId"}, onDelete = 5), @androidx.room.ForeignKey(entity = com.wilplayer.android.data.local.entity.SongEntity.class, parentColumns = {"id"}, childColumns = {"songId"}, onDelete = 5)})
public final class PlaylistSongCrossRef {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String playlistId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String songId = null;
    private final int position = 0;
    
    public PlaylistSongCrossRef(@org.jetbrains.annotations.NotNull()
    java.lang.String playlistId, @org.jetbrains.annotations.NotNull()
    java.lang.String songId, int position) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPlaylistId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSongId() {
        return null;
    }
    
    public final int getPosition() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.local.entity.PlaylistSongCrossRef copy(@org.jetbrains.annotations.NotNull()
    java.lang.String playlistId, @org.jetbrains.annotations.NotNull()
    java.lang.String songId, int position) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}