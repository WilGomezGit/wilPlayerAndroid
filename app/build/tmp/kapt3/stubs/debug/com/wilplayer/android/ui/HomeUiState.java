package com.wilplayer.android.ui;

import androidx.lifecycle.ViewModel;
import com.wilplayer.android.data.repository.MusicRepository;
import com.wilplayer.android.data.repository.Result;
import com.wilplayer.android.domain.model.Playlist;
import com.wilplayer.android.domain.model.Song;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Be\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0005\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u0005H\u00c6\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\rH\u00c6\u0003Ji\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00052\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rH\u00c6\u0001J\u0013\u0010\u001f\u001a\u00020\u00032\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010!\u001a\u00020\"H\u00d6\u0001J\t\u0010#\u001a\u00020\rH\u00d6\u0001R\u0013\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0011R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013\u00a8\u0006$"}, d2 = {"Lcom/wilplayer/android/ui/HomeUiState;", "", "isLoading", "", "trending", "", "Lcom/wilplayer/android/domain/model/Song;", "recentSongs", "recentPlaylists", "Lcom/wilplayer/android/domain/model/Playlist;", "recommendations", "isLoadingRecs", "error", "", "(ZLjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLjava/lang/String;)V", "getError", "()Ljava/lang/String;", "()Z", "getRecentPlaylists", "()Ljava/util/List;", "getRecentSongs", "getRecommendations", "getTrending", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class HomeUiState {
    private final boolean isLoading = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.wilplayer.android.domain.model.Song> trending = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.wilplayer.android.domain.model.Song> recentSongs = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.wilplayer.android.domain.model.Playlist> recentPlaylists = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.wilplayer.android.domain.model.Song> recommendations = null;
    private final boolean isLoadingRecs = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    
    public HomeUiState(boolean isLoading, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.domain.model.Song> trending, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.domain.model.Song> recentSongs, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.domain.model.Playlist> recentPlaylists, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.domain.model.Song> recommendations, boolean isLoadingRecs, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        super();
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.domain.model.Song> getTrending() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.domain.model.Song> getRecentSongs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.domain.model.Playlist> getRecentPlaylists() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.domain.model.Song> getRecommendations() {
        return null;
    }
    
    public final boolean isLoadingRecs() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public HomeUiState() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.domain.model.Song> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.domain.model.Song> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.domain.model.Playlist> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.domain.model.Song> component5() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.ui.HomeUiState copy(boolean isLoading, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.domain.model.Song> trending, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.domain.model.Song> recentSongs, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.domain.model.Playlist> recentPlaylists, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.domain.model.Song> recommendations, boolean isLoadingRecs, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
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