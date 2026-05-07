package com.wilplayer.android.data.model;

import com.google.gson.annotations.SerializedName;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0007H\u00c6\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0003JC\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001J\t\u0010\u001f\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006 "}, d2 = {"Lcom/wilplayer/android/data/model/YouTubePlaylistItemsResponse;", "", "kind", "", "etag", "nextPageToken", "pageInfo", "Lcom/wilplayer/android/data/model/PageInfo;", "items", "", "Lcom/wilplayer/android/data/model/PlaylistItem;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/wilplayer/android/data/model/PageInfo;Ljava/util/List;)V", "getEtag", "()Ljava/lang/String;", "getItems", "()Ljava/util/List;", "getKind", "getNextPageToken", "getPageInfo", "()Lcom/wilplayer/android/data/model/PageInfo;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class YouTubePlaylistItemsResponse {
    @com.google.gson.annotations.SerializedName(value = "kind")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String kind = null;
    @com.google.gson.annotations.SerializedName(value = "etag")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String etag = null;
    @com.google.gson.annotations.SerializedName(value = "nextPageToken")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String nextPageToken = null;
    @com.google.gson.annotations.SerializedName(value = "pageInfo")
    @org.jetbrains.annotations.NotNull()
    private final com.wilplayer.android.data.model.PageInfo pageInfo = null;
    @com.google.gson.annotations.SerializedName(value = "items")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.wilplayer.android.data.model.PlaylistItem> items = null;
    
    public YouTubePlaylistItemsResponse(@org.jetbrains.annotations.NotNull()
    java.lang.String kind, @org.jetbrains.annotations.NotNull()
    java.lang.String etag, @org.jetbrains.annotations.Nullable()
    java.lang.String nextPageToken, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.model.PageInfo pageInfo, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.data.model.PlaylistItem> items) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getKind() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEtag() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getNextPageToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.model.PageInfo getPageInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.data.model.PlaylistItem> getItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.model.PageInfo component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.data.model.PlaylistItem> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.model.YouTubePlaylistItemsResponse copy(@org.jetbrains.annotations.NotNull()
    java.lang.String kind, @org.jetbrains.annotations.NotNull()
    java.lang.String etag, @org.jetbrains.annotations.Nullable()
    java.lang.String nextPageToken, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.model.PageInfo pageInfo, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.data.model.PlaylistItem> items) {
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