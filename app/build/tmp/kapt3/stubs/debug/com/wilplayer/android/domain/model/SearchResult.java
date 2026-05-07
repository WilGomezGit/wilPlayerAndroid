package com.wilplayer.android.domain.model;

import android.os.Parcelable;
import kotlinx.parcelize.Parcelize;

/**
 * Search result container — may contain songs or playlists.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\t\u0010\u0016\u001a\u00020\nH\u00c6\u0003J?\u0010\u0017\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\nH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\bH\u00d6\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001d"}, d2 = {"Lcom/wilplayer/android/domain/model/SearchResult;", "", "songs", "", "Lcom/wilplayer/android/domain/model/Song;", "playlists", "Lcom/wilplayer/android/domain/model/Playlist;", "nextPageToken", "", "totalResults", "", "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;I)V", "getNextPageToken", "()Ljava/lang/String;", "getPlaylists", "()Ljava/util/List;", "getSongs", "getTotalResults", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class SearchResult {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.wilplayer.android.domain.model.Song> songs = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.wilplayer.android.domain.model.Playlist> playlists = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String nextPageToken = null;
    private final int totalResults = 0;
    
    public SearchResult(@org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.domain.model.Song> songs, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.domain.model.Playlist> playlists, @org.jetbrains.annotations.Nullable()
    java.lang.String nextPageToken, int totalResults) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.domain.model.Song> getSongs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.domain.model.Playlist> getPlaylists() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getNextPageToken() {
        return null;
    }
    
    public final int getTotalResults() {
        return 0;
    }
    
    public SearchResult() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.domain.model.Song> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.domain.model.Playlist> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.domain.model.SearchResult copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.domain.model.Song> songs, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.domain.model.Playlist> playlists, @org.jetbrains.annotations.Nullable()
    java.lang.String nextPageToken, int totalResults) {
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