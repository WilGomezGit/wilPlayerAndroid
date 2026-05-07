package com.wilplayer.android.data.model;

import com.google.gson.annotations.SerializedName;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003JK\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010!\u001a\u00020\"H\u00d6\u0001J\t\u0010#\u001a\u00020\u0003H\u00d6\u0001R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006$"}, d2 = {"Lcom/wilplayer/android/data/model/VideoItem;", "", "kind", "", "etag", "id", "snippet", "Lcom/wilplayer/android/data/model/VideoSnippet;", "contentDetails", "Lcom/wilplayer/android/data/model/ContentDetails;", "statistics", "Lcom/wilplayer/android/data/model/VideoStatistics;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/wilplayer/android/data/model/VideoSnippet;Lcom/wilplayer/android/data/model/ContentDetails;Lcom/wilplayer/android/data/model/VideoStatistics;)V", "getContentDetails", "()Lcom/wilplayer/android/data/model/ContentDetails;", "getEtag", "()Ljava/lang/String;", "getId", "getKind", "getSnippet", "()Lcom/wilplayer/android/data/model/VideoSnippet;", "getStatistics", "()Lcom/wilplayer/android/data/model/VideoStatistics;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class VideoItem {
    @com.google.gson.annotations.SerializedName(value = "kind")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String kind = null;
    @com.google.gson.annotations.SerializedName(value = "etag")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String etag = null;
    @com.google.gson.annotations.SerializedName(value = "id")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @com.google.gson.annotations.SerializedName(value = "snippet")
    @org.jetbrains.annotations.Nullable()
    private final com.wilplayer.android.data.model.VideoSnippet snippet = null;
    @com.google.gson.annotations.SerializedName(value = "contentDetails")
    @org.jetbrains.annotations.Nullable()
    private final com.wilplayer.android.data.model.ContentDetails contentDetails = null;
    @com.google.gson.annotations.SerializedName(value = "statistics")
    @org.jetbrains.annotations.Nullable()
    private final com.wilplayer.android.data.model.VideoStatistics statistics = null;
    
    public VideoItem(@org.jetbrains.annotations.NotNull()
    java.lang.String kind, @org.jetbrains.annotations.NotNull()
    java.lang.String etag, @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.Nullable()
    com.wilplayer.android.data.model.VideoSnippet snippet, @org.jetbrains.annotations.Nullable()
    com.wilplayer.android.data.model.ContentDetails contentDetails, @org.jetbrains.annotations.Nullable()
    com.wilplayer.android.data.model.VideoStatistics statistics) {
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.data.model.VideoSnippet getSnippet() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.data.model.ContentDetails getContentDetails() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.data.model.VideoStatistics getStatistics() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.data.model.VideoSnippet component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.data.model.ContentDetails component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.data.model.VideoStatistics component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.model.VideoItem copy(@org.jetbrains.annotations.NotNull()
    java.lang.String kind, @org.jetbrains.annotations.NotNull()
    java.lang.String etag, @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.Nullable()
    com.wilplayer.android.data.model.VideoSnippet snippet, @org.jetbrains.annotations.Nullable()
    com.wilplayer.android.data.model.ContentDetails contentDetails, @org.jetbrains.annotations.Nullable()
    com.wilplayer.android.data.model.VideoStatistics statistics) {
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