package com.wilplayer.android.domain.model;

import android.os.Parcelable;
import kotlinx.parcelize.Parcelize;

/**
 * Core domain model representing a playable track.
 * Decoupled from YouTube API specifics.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b.\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 M2\u00020\u0001:\u0001MB\u0097\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\t\u0012\b\b\u0002\u0010\u0011\u001a\u00020\t\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u00a2\u0006\u0002\u0010\u0017J\t\u00102\u001a\u00020\u0003H\u00c6\u0003J\t\u00103\u001a\u00020\u000fH\u00c6\u0003J\t\u00104\u001a\u00020\tH\u00c6\u0003J\t\u00105\u001a\u00020\tH\u00c6\u0003J\u000f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00030\u0013H\u00c6\u0003J\t\u00107\u001a\u00020\u0003H\u00c6\u0003J\t\u00108\u001a\u00020\u0016H\u00c6\u0003J\t\u00109\u001a\u00020\u0003H\u00c6\u0003J\t\u0010:\u001a\u00020\u0003H\u00c6\u0003J\t\u0010;\u001a\u00020\u0003H\u00c6\u0003J\t\u0010<\u001a\u00020\u0003H\u00c6\u0003J\t\u0010=\u001a\u00020\tH\u00c6\u0003J\t\u0010>\u001a\u00020\u0003H\u00c6\u0003J\t\u0010?\u001a\u00020\tH\u00c6\u0003J\t\u0010@\u001a\u00020\rH\u00c6\u0003J\u00a5\u0001\u0010A\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\t2\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u00c6\u0001J\t\u0010B\u001a\u00020\u000fH\u00d6\u0001J\u0013\u0010C\u001a\u00020\r2\b\u0010D\u001a\u0004\u0018\u00010EH\u00d6\u0003J\t\u0010F\u001a\u00020\u000fH\u00d6\u0001J\t\u0010G\u001a\u00020\u0003H\u00d6\u0001J\u0019\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0011\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0014\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001bR\u0011\u0010\u001f\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b \u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\"R\u0011\u0010\u0010\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0011\u0010$\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b%\u0010\u001bR\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0011\u0010\u0015\u001a\u00020\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001bR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0019R\u0011\u00100\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b1\u0010\u001b\u00a8\u0006N"}, d2 = {"Lcom/wilplayer/android/domain/model/Song;", "Landroid/os/Parcelable;", "id", "", "videoId", "title", "artist", "thumbnailUrl", "duration", "", "durationFormatted", "viewCount", "isLiked", "", "playCount", "", "lastPlayedAt", "addedAt", "tags", "", "categoryId", "shuffleWeight", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;JZIJJLjava/util/List;Ljava/lang/String;F)V", "getAddedAt", "()J", "getArtist", "()Ljava/lang/String;", "getCategoryId", "getDuration", "getDurationFormatted", "highResThumbnail", "getHighResThumbnail", "getId", "()Z", "getLastPlayedAt", "mediumThumbnail", "getMediumThumbnail", "getPlayCount", "()I", "getShuffleWeight", "()F", "getTags", "()Ljava/util/List;", "getThumbnailUrl", "getTitle", "getVideoId", "getViewCount", "youtubeUrl", "getYoutubeUrl", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "app_debug"})
@kotlinx.parcelize.Parcelize()
public final class Song implements android.os.Parcelable {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String videoId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String title = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String artist = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String thumbnailUrl = null;
    private final long duration = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String durationFormatted = null;
    private final long viewCount = 0L;
    private final boolean isLiked = false;
    private final int playCount = 0;
    private final long lastPlayedAt = 0L;
    private final long addedAt = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> tags = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String categoryId = null;
    
    /**
     * Score used by the smart shuffle algorithm
     */
    private final float shuffleWeight = 0.0F;
    @org.jetbrains.annotations.NotNull()
    public static final com.wilplayer.android.domain.model.Song.Companion Companion = null;
    
    public Song(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String videoId, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String artist, @org.jetbrains.annotations.NotNull()
    java.lang.String thumbnailUrl, long duration, @org.jetbrains.annotations.NotNull()
    java.lang.String durationFormatted, long viewCount, boolean isLiked, int playCount, long lastPlayedAt, long addedAt, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> tags, @org.jetbrains.annotations.NotNull()
    java.lang.String categoryId, float shuffleWeight) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVideoId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getArtist() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getThumbnailUrl() {
        return null;
    }
    
    public final long getDuration() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDurationFormatted() {
        return null;
    }
    
    public final long getViewCount() {
        return 0L;
    }
    
    public final boolean isLiked() {
        return false;
    }
    
    public final int getPlayCount() {
        return 0;
    }
    
    public final long getLastPlayedAt() {
        return 0L;
    }
    
    public final long getAddedAt() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getTags() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCategoryId() {
        return null;
    }
    
    /**
     * Score used by the smart shuffle algorithm
     */
    public final float getShuffleWeight() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getYoutubeUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHighResThumbnail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMediumThumbnail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    public final int component10() {
        return 0;
    }
    
    public final long component11() {
        return 0L;
    }
    
    public final long component12() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component14() {
        return null;
    }
    
    public final float component15() {
        return 0.0F;
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
    public final java.lang.String component5() {
        return null;
    }
    
    public final long component6() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    public final long component8() {
        return 0L;
    }
    
    public final boolean component9() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.domain.model.Song copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String videoId, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String artist, @org.jetbrains.annotations.NotNull()
    java.lang.String thumbnailUrl, long duration, @org.jetbrains.annotations.NotNull()
    java.lang.String durationFormatted, long viewCount, boolean isLiked, int playCount, long lastPlayedAt, long addedAt, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> tags, @org.jetbrains.annotations.NotNull()
    java.lang.String categoryId, float shuffleWeight) {
        return null;
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
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
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/wilplayer/android/domain/model/Song$Companion;", "", "()V", "empty", "Lcom/wilplayer/android/domain/model/Song;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.wilplayer.android.domain.model.Song empty() {
            return null;
        }
    }
}