package com.wilplayer.android.data.model;

import com.google.gson.annotations.SerializedName;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\bH\u00c6\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003JO\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010 \u001a\u00020!H\u00d6\u0001J\t\u0010\"\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e\u00a8\u0006#"}, d2 = {"Lcom/wilplayer/android/data/model/YouTubeSearchResponse;", "", "kind", "", "etag", "nextPageToken", "prevPageToken", "pageInfo", "Lcom/wilplayer/android/data/model/PageInfo;", "items", "", "Lcom/wilplayer/android/data/model/SearchItem;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/wilplayer/android/data/model/PageInfo;Ljava/util/List;)V", "getEtag", "()Ljava/lang/String;", "getItems", "()Ljava/util/List;", "getKind", "getNextPageToken", "getPageInfo", "()Lcom/wilplayer/android/data/model/PageInfo;", "getPrevPageToken", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class YouTubeSearchResponse {
    @com.google.gson.annotations.SerializedName(value = "kind")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String kind = null;
    @com.google.gson.annotations.SerializedName(value = "etag")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String etag = null;
    @com.google.gson.annotations.SerializedName(value = "nextPageToken")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String nextPageToken = null;
    @com.google.gson.annotations.SerializedName(value = "prevPageToken")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String prevPageToken = null;
    @com.google.gson.annotations.SerializedName(value = "pageInfo")
    @org.jetbrains.annotations.NotNull()
    private final com.wilplayer.android.data.model.PageInfo pageInfo = null;
    @com.google.gson.annotations.SerializedName(value = "items")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.wilplayer.android.data.model.SearchItem> items = null;
    
    public YouTubeSearchResponse(@org.jetbrains.annotations.NotNull()
    java.lang.String kind, @org.jetbrains.annotations.NotNull()
    java.lang.String etag, @org.jetbrains.annotations.Nullable()
    java.lang.String nextPageToken, @org.jetbrains.annotations.Nullable()
    java.lang.String prevPageToken, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.model.PageInfo pageInfo, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.data.model.SearchItem> items) {
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPrevPageToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.model.PageInfo getPageInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.data.model.SearchItem> getItems() {
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.model.PageInfo component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.data.model.SearchItem> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.model.YouTubeSearchResponse copy(@org.jetbrains.annotations.NotNull()
    java.lang.String kind, @org.jetbrains.annotations.NotNull()
    java.lang.String etag, @org.jetbrains.annotations.Nullable()
    java.lang.String nextPageToken, @org.jetbrains.annotations.Nullable()
    java.lang.String prevPageToken, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.model.PageInfo pageInfo, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.data.model.SearchItem> items) {
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