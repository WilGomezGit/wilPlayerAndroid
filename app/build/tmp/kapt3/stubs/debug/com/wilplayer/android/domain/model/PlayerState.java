package com.wilplayer.android.domain.model;

import android.os.Parcelable;
import kotlinx.parcelize.Parcelize;

/**
 * Player state snapshot for UI consumption.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\'\b\u0086\b\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u00a2\u0006\u0002\u0010\u0017J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0013H\u00c6\u0003J\t\u0010.\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0016H\u00c6\u0003J\t\u00100\u001a\u00020\u0005H\u00c6\u0003J\t\u00101\u001a\u00020\u0007H\u00c6\u0003J\t\u00102\u001a\u00020\u0007H\u00c6\u0003J\t\u00103\u001a\u00020\nH\u00c6\u0003J\t\u00104\u001a\u00020\fH\u00c6\u0003J\u000f\u00105\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eH\u00c6\u0003J\t\u00106\u001a\u00020\u0010H\u00c6\u0003J\t\u00107\u001a\u00020\u0005H\u00c6\u0003J\u008b\u0001\u00108\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u00c6\u0001J\u0013\u00109\u001a\u00020\u00052\b\u0010:\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010;\u001a\u00020\u0010H\u00d6\u0001J\t\u0010<\u001a\u00020\u0016H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0011\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u001eR\u0011\u0010\u0014\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u001eR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u0011\u0010 \u001a\u00020\u00138F\u00a2\u0006\u0006\u001a\u0004\b!\u0010\"R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\"\u00a8\u0006="}, d2 = {"Lcom/wilplayer/android/domain/model/PlayerState;", "", "currentSong", "Lcom/wilplayer/android/domain/model/Song;", "isPlaying", "", "progress", "", "duration", "shuffleMode", "Lcom/wilplayer/android/domain/model/ShuffleMode;", "repeatMode", "Lcom/wilplayer/android/domain/model/RepeatMode;", "queue", "", "queueIndex", "", "isBuffering", "volume", "", "isMuted", "errorMessage", "", "(Lcom/wilplayer/android/domain/model/Song;ZJJLcom/wilplayer/android/domain/model/ShuffleMode;Lcom/wilplayer/android/domain/model/RepeatMode;Ljava/util/List;IZFZLjava/lang/String;)V", "getCurrentSong", "()Lcom/wilplayer/android/domain/model/Song;", "getDuration", "()J", "getErrorMessage", "()Ljava/lang/String;", "()Z", "getProgress", "progressFraction", "getProgressFraction", "()F", "getQueue", "()Ljava/util/List;", "getQueueIndex", "()I", "getRepeatMode", "()Lcom/wilplayer/android/domain/model/RepeatMode;", "getShuffleMode", "()Lcom/wilplayer/android/domain/model/ShuffleMode;", "getVolume", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class PlayerState {
    @org.jetbrains.annotations.Nullable()
    private final com.wilplayer.android.domain.model.Song currentSong = null;
    private final boolean isPlaying = false;
    private final long progress = 0L;
    private final long duration = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.wilplayer.android.domain.model.ShuffleMode shuffleMode = null;
    @org.jetbrains.annotations.NotNull()
    private final com.wilplayer.android.domain.model.RepeatMode repeatMode = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.wilplayer.android.domain.model.Song> queue = null;
    private final int queueIndex = 0;
    private final boolean isBuffering = false;
    private final float volume = 0.0F;
    private final boolean isMuted = false;
    
    /**
     * Non-null when stream extraction or playback fails; shown as a Snackbar.
     */
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String errorMessage = null;
    
    public PlayerState(@org.jetbrains.annotations.Nullable()
    com.wilplayer.android.domain.model.Song currentSong, boolean isPlaying, long progress, long duration, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.ShuffleMode shuffleMode, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.RepeatMode repeatMode, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.domain.model.Song> queue, int queueIndex, boolean isBuffering, float volume, boolean isMuted, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.domain.model.Song getCurrentSong() {
        return null;
    }
    
    public final boolean isPlaying() {
        return false;
    }
    
    public final long getProgress() {
        return 0L;
    }
    
    public final long getDuration() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.domain.model.ShuffleMode getShuffleMode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.domain.model.RepeatMode getRepeatMode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.domain.model.Song> getQueue() {
        return null;
    }
    
    public final int getQueueIndex() {
        return 0;
    }
    
    public final boolean isBuffering() {
        return false;
    }
    
    public final float getVolume() {
        return 0.0F;
    }
    
    public final boolean isMuted() {
        return false;
    }
    
    /**
     * Non-null when stream extraction or playback fails; shown as a Snackbar.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getErrorMessage() {
        return null;
    }
    
    public final float getProgressFraction() {
        return 0.0F;
    }
    
    public PlayerState() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.domain.model.Song component1() {
        return null;
    }
    
    public final float component10() {
        return 0.0F;
    }
    
    public final boolean component11() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component12() {
        return null;
    }
    
    public final boolean component2() {
        return false;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final long component4() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.domain.model.ShuffleMode component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.domain.model.RepeatMode component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.wilplayer.android.domain.model.Song> component7() {
        return null;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final boolean component9() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.domain.model.PlayerState copy(@org.jetbrains.annotations.Nullable()
    com.wilplayer.android.domain.model.Song currentSong, boolean isPlaying, long progress, long duration, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.ShuffleMode shuffleMode, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.RepeatMode repeatMode, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.domain.model.Song> queue, int queueIndex, boolean isBuffering, float volume, boolean isMuted, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage) {
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