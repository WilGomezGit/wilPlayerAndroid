package com.wilplayer.android.data.api;

import com.wilplayer.android.data.model.*;
import retrofit2.http.GET;
import retrofit2.http.Query;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001JB\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\b2\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00052\b\b\u0001\u0010\n\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ@\u0010\f\u001a\u00020\r2\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u000e\u001a\u00020\u00052\b\b\u0003\u0010\u000f\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\n\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0010JV\u0010\u0011\u001a\u00020\u00122\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0013\u001a\u00020\u00052\b\b\u0003\u0010\u0014\u001a\u00020\u00052\b\b\u0003\u0010\u0015\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\b2\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00052\b\b\u0001\u0010\n\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0016J,\u0010\u0017\u001a\u00020\u00182\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0019\u001a\u00020\u00052\b\b\u0001\u0010\n\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u001aJV\u0010\u001b\u001a\u00020\r2\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u001c\u001a\u00020\u00052\b\b\u0003\u0010\u000f\u001a\u00020\u00052\b\b\u0003\u0010\u0014\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\b2\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00052\b\b\u0001\u0010\n\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0016\u00a8\u0006\u001d"}, d2 = {"Lcom/wilplayer/android/data/api/YouTubeApiService;", "", "getPlaylistItems", "Lcom/wilplayer/android/data/model/YouTubePlaylistItemsResponse;", "part", "", "playlistId", "maxResults", "", "pageToken", "apiKey", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRelated", "Lcom/wilplayer/android/data/model/YouTubeSearchResponse;", "videoId", "type", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTrending", "Lcom/wilplayer/android/data/model/YouTubeChartResponse;", "chart", "categoryId", "regionCode", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getVideoDetails", "Lcom/wilplayer/android/data/model/YouTubeVideoResponse;", "videoIds", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "search", "query", "app_debug"})
public abstract interface YouTubeApiService {
    
    /**
     * Search for videos / playlists
     */
    @retrofit2.http.GET(value = "search")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object search(@retrofit2.http.Query(value = "part")
    @org.jetbrains.annotations.NotNull()
    java.lang.String part, @retrofit2.http.Query(value = "q")
    @org.jetbrains.annotations.NotNull()
    java.lang.String query, @retrofit2.http.Query(value = "type")
    @org.jetbrains.annotations.NotNull()
    java.lang.String type, @retrofit2.http.Query(value = "videoCategoryId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String categoryId, @retrofit2.http.Query(value = "maxResults")
    int maxResults, @retrofit2.http.Query(value = "pageToken")
    @org.jetbrains.annotations.Nullable()
    java.lang.String pageToken, @retrofit2.http.Query(value = "key")
    @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.wilplayer.android.data.model.YouTubeSearchResponse> $completion);
    
    /**
     * Get video details (duration, statistics)
     */
    @retrofit2.http.GET(value = "videos")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getVideoDetails(@retrofit2.http.Query(value = "part")
    @org.jetbrains.annotations.NotNull()
    java.lang.String part, @retrofit2.http.Query(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String videoIds, @retrofit2.http.Query(value = "key")
    @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.wilplayer.android.data.model.YouTubeVideoResponse> $completion);
    
    /**
     * Get trending music
     */
    @retrofit2.http.GET(value = "videos")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTrending(@retrofit2.http.Query(value = "part")
    @org.jetbrains.annotations.NotNull()
    java.lang.String part, @retrofit2.http.Query(value = "chart")
    @org.jetbrains.annotations.NotNull()
    java.lang.String chart, @retrofit2.http.Query(value = "videoCategoryId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String categoryId, @retrofit2.http.Query(value = "regionCode")
    @org.jetbrains.annotations.NotNull()
    java.lang.String regionCode, @retrofit2.http.Query(value = "maxResults")
    int maxResults, @retrofit2.http.Query(value = "pageToken")
    @org.jetbrains.annotations.Nullable()
    java.lang.String pageToken, @retrofit2.http.Query(value = "key")
    @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.wilplayer.android.data.model.YouTubeChartResponse> $completion);
    
    /**
     * Get playlist items
     */
    @retrofit2.http.GET(value = "playlistItems")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPlaylistItems(@retrofit2.http.Query(value = "part")
    @org.jetbrains.annotations.NotNull()
    java.lang.String part, @retrofit2.http.Query(value = "playlistId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String playlistId, @retrofit2.http.Query(value = "maxResults")
    int maxResults, @retrofit2.http.Query(value = "pageToken")
    @org.jetbrains.annotations.Nullable()
    java.lang.String pageToken, @retrofit2.http.Query(value = "key")
    @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.wilplayer.android.data.model.YouTubePlaylistItemsResponse> $completion);
    
    /**
     * Related videos
     */
    @retrofit2.http.GET(value = "search")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRelated(@retrofit2.http.Query(value = "part")
    @org.jetbrains.annotations.NotNull()
    java.lang.String part, @retrofit2.http.Query(value = "relatedToVideoId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String videoId, @retrofit2.http.Query(value = "type")
    @org.jetbrains.annotations.NotNull()
    java.lang.String type, @retrofit2.http.Query(value = "maxResults")
    int maxResults, @retrofit2.http.Query(value = "key")
    @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.wilplayer.android.data.model.YouTubeSearchResponse> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}