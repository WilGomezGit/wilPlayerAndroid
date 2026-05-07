package com.wilplayer.android.data.repository;

import com.wilplayer.android.BuildConfig;
import com.wilplayer.android.data.api.YouTubeApiService;
import com.wilplayer.android.data.local.dao.*;
import com.wilplayer.android.data.local.entity.*;
import com.wilplayer.android.data.model.*;
import com.wilplayer.android.domain.model.*;
import com.wilplayer.android.util.DurationParser;
import com.wilplayer.android.util.SmartShuffleEngine;
import com.wilplayer.android.data.preferences.UserPreferences;
import kotlinx.coroutines.flow.*;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ&\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0010H\u0002J \u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\b\b\u0002\u0010\u001a\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u001bJ(\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u00102\b\b\u0002\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010 J\u0012\u0010!\u001a\u0004\u0018\u00010\u00102\u0006\u0010\"\u001a\u00020\u0010H\u0002J\u0012\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0%0$J\u0012\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120%0$J\u000e\u0010(\u001a\u00020\u0010H\u0082@\u00a2\u0006\u0002\u0010)J\u0012\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100%0$J\u0012\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120%0$J\u0016\u0010,\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010 J\u0016\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0$2\u0006\u0010\u001f\u001a\u00020\u0010J\u000e\u0010.\u001a\u00020\u0010H\u0082@\u00a2\u0006\u0002\u0010)J\"\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00120%2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00120%H\u0086@\u00a2\u0006\u0002\u00101J\u001c\u00102\u001a\b\u0012\u0004\u0012\u00020\u00120%2\u0006\u00103\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010 J&\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120%052\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u0010H\u0086@\u00a2\u0006\u0002\u0010 J\u001c\u00107\u001a\b\u0012\u0004\u0012\u00020&052\u0006\u0010\"\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010 J:\u00108\u001a\u00020\u00122\u0006\u00109\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u00102\u0006\u0010<\u001a\u00020\u00102\b\u0010=\u001a\u0004\u0018\u00010\u0010H\u0002J\u0016\u0010>\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010?J\u0016\u0010@\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010?J(\u0010A\u001a\b\u0012\u0004\u0012\u00020B052\u0006\u0010C\u001a\u00020\u00102\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u0010H\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u001c\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00120%2\u0006\u0010C\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010 J\u0016\u0010F\u001a\u00020G2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010?R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006H"}, d2 = {"Lcom/wilplayer/android/data/repository/MusicRepository;", "", "apiService", "Lcom/wilplayer/android/data/api/YouTubeApiService;", "songDao", "Lcom/wilplayer/android/data/local/dao/SongDao;", "playlistDao", "Lcom/wilplayer/android/data/local/dao/PlaylistDao;", "historyDao", "Lcom/wilplayer/android/data/local/dao/HistoryDao;", "userPrefs", "Lcom/wilplayer/android/data/preferences/UserPreferences;", "(Lcom/wilplayer/android/data/api/YouTubeApiService;Lcom/wilplayer/android/data/local/dao/SongDao;Lcom/wilplayer/android/data/local/dao/PlaylistDao;Lcom/wilplayer/android/data/local/dao/HistoryDao;Lcom/wilplayer/android/data/preferences/UserPreferences;)V", "addSongToPlaylist", "", "playlistId", "", "song", "Lcom/wilplayer/android/domain/model/Song;", "position", "", "(Ljava/lang/String;Lcom/wilplayer/android/domain/model/Song;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cleanTitle", "title", "createPlaylist", "name", "description", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createPlaylistWithSong", "(Ljava/lang/String;Ljava/lang/String;Lcom/wilplayer/android/domain/model/Song;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePlaylist", "id", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "extractPlaylistId", "url", "getAllPlaylists", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/wilplayer/android/domain/model/Playlist;", "getAllSongs", "getApiKey", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getArtists", "getLikedSongs", "getPlaylistSongCount", "getPlaylistWithSongs", "getRegionCode", "getSmartShuffleQueue", "songs", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSongsByArtist", "artist", "getTrending", "Lcom/wilplayer/android/data/repository/Result;", "regionCode", "importYouTubePlaylist", "mapToSong", "videoId", "channelTitle", "thumbnailUrl", "duration", "viewCount", "recordPlay", "(Lcom/wilplayer/android/domain/model/Song;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recordSkip", "search", "Lcom/wilplayer/android/domain/model/SearchResult;", "query", "pageToken", "searchLocal", "toggleLike", "", "app_debug"})
public final class MusicRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.wilplayer.android.data.api.YouTubeApiService apiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.wilplayer.android.data.local.dao.SongDao songDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.wilplayer.android.data.local.dao.PlaylistDao playlistDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.wilplayer.android.data.local.dao.HistoryDao historyDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.wilplayer.android.data.preferences.UserPreferences userPrefs = null;
    
    @javax.inject.Inject()
    public MusicRepository(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.api.YouTubeApiService apiService, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.local.dao.SongDao songDao, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.local.dao.PlaylistDao playlistDao, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.local.dao.HistoryDao historyDao, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.preferences.UserPreferences userPrefs) {
        super();
    }
    
    private final java.lang.Object getApiKey(kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final java.lang.Object getRegionCode(kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object search(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.Nullable()
    java.lang.String pageToken, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.wilplayer.android.data.repository.Result<com.wilplayer.android.domain.model.SearchResult>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTrending(@org.jetbrains.annotations.Nullable()
    java.lang.String regionCode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.wilplayer.android.data.repository.Result<? extends java.util.List<com.wilplayer.android.domain.model.Song>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.wilplayer.android.domain.model.Song>> getAllSongs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getArtists() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.wilplayer.android.domain.model.Song>> getLikedSongs() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object toggleLike(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.Song song, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.wilplayer.android.domain.model.Playlist>> getAllPlaylists() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.wilplayer.android.domain.model.Playlist> getPlaylistWithSongs(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createPlaylist(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addSongToPlaylist(@org.jetbrains.annotations.NotNull()
    java.lang.String playlistId, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.Song song, int position, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getPlaylistSongCount(@org.jetbrains.annotations.NotNull()
    java.lang.String playlistId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createPlaylistWithSong(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.Song song, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object recordPlay(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.Song song, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object recordSkip(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.Song song, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSmartShuffleQueue(@org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.domain.model.Song> songs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.wilplayer.android.domain.model.Song>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchLocal(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.wilplayer.android.domain.model.Song>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSongsByArtist(@org.jetbrains.annotations.NotNull()
    java.lang.String artist, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.wilplayer.android.domain.model.Song>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object importYouTubePlaylist(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.wilplayer.android.data.repository.Result<com.wilplayer.android.domain.model.Playlist>> $completion) {
        return null;
    }
    
    private final java.lang.String extractPlaylistId(java.lang.String url) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deletePlaylist(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final com.wilplayer.android.domain.model.Song mapToSong(java.lang.String videoId, java.lang.String title, java.lang.String channelTitle, java.lang.String thumbnailUrl, java.lang.String duration, java.lang.String viewCount) {
        return null;
    }
    
    private final java.lang.String cleanTitle(java.lang.String title) {
        return null;
    }
}