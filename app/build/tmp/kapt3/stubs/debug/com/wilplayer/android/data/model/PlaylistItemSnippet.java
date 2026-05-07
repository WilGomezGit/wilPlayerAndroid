package com.wilplayer.android.data.model;

import com.google.gson.annotations.SerializedName;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\bH\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\fH\u00c6\u0003J\t\u0010%\u001a\u00020\u000eH\u00c6\u0003Jc\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u00c6\u0001J\u0013\u0010\'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010*\u001a\u00020\fH\u00d6\u0001J\t\u0010+\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011\u00a8\u0006,"}, d2 = {"Lcom/wilplayer/android/data/model/PlaylistItemSnippet;", "", "publishedAt", "", "channelId", "title", "description", "thumbnails", "Lcom/wilplayer/android/data/model/Thumbnails;", "channelTitle", "playlistId", "position", "", "resourceId", "Lcom/wilplayer/android/data/model/ResourceId;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/wilplayer/android/data/model/Thumbnails;Ljava/lang/String;Ljava/lang/String;ILcom/wilplayer/android/data/model/ResourceId;)V", "getChannelId", "()Ljava/lang/String;", "getChannelTitle", "getDescription", "getPlaylistId", "getPosition", "()I", "getPublishedAt", "getResourceId", "()Lcom/wilplayer/android/data/model/ResourceId;", "getThumbnails", "()Lcom/wilplayer/android/data/model/Thumbnails;", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class PlaylistItemSnippet {
    @com.google.gson.annotations.SerializedName(value = "publishedAt")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String publishedAt = null;
    @com.google.gson.annotations.SerializedName(value = "channelId")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String channelId = null;
    @com.google.gson.annotations.SerializedName(value = "title")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String title = null;
    @com.google.gson.annotations.SerializedName(value = "description")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String description = null;
    @com.google.gson.annotations.SerializedName(value = "thumbnails")
    @org.jetbrains.annotations.NotNull()
    private final com.wilplayer.android.data.model.Thumbnails thumbnails = null;
    @com.google.gson.annotations.SerializedName(value = "channelTitle")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String channelTitle = null;
    @com.google.gson.annotations.SerializedName(value = "playlistId")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String playlistId = null;
    @com.google.gson.annotations.SerializedName(value = "position")
    private final int position = 0;
    @com.google.gson.annotations.SerializedName(value = "resourceId")
    @org.jetbrains.annotations.NotNull()
    private final com.wilplayer.android.data.model.ResourceId resourceId = null;
    
    public PlaylistItemSnippet(@org.jetbrains.annotations.NotNull()
    java.lang.String publishedAt, @org.jetbrains.annotations.NotNull()
    java.lang.String channelId, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.model.Thumbnails thumbnails, @org.jetbrains.annotations.NotNull()
    java.lang.String channelTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String playlistId, int position, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.model.ResourceId resourceId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPublishedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getChannelId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.model.Thumbnails getThumbnails() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getChannelTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPlaylistId() {
        return null;
    }
    
    public final int getPosition() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.model.ResourceId getResourceId() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.model.Thumbnails component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    public final int component8() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.model.ResourceId component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.model.PlaylistItemSnippet copy(@org.jetbrains.annotations.NotNull()
    java.lang.String publishedAt, @org.jetbrains.annotations.NotNull()
    java.lang.String channelId, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.model.Thumbnails thumbnails, @org.jetbrains.annotations.NotNull()
    java.lang.String channelTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String playlistId, int position, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.model.ResourceId resourceId) {
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