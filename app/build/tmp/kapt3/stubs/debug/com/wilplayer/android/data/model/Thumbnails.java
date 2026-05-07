package com.wilplayer.android.data.model;

import com.google.gson.annotations.SerializedName;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003JE\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n\u00a8\u0006\u001c"}, d2 = {"Lcom/wilplayer/android/data/model/Thumbnails;", "", "default", "Lcom/wilplayer/android/data/model/ThumbnailInfo;", "medium", "high", "standard", "maxres", "(Lcom/wilplayer/android/data/model/ThumbnailInfo;Lcom/wilplayer/android/data/model/ThumbnailInfo;Lcom/wilplayer/android/data/model/ThumbnailInfo;Lcom/wilplayer/android/data/model/ThumbnailInfo;Lcom/wilplayer/android/data/model/ThumbnailInfo;)V", "getDefault", "()Lcom/wilplayer/android/data/model/ThumbnailInfo;", "getHigh", "getMaxres", "getMedium", "getStandard", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
public final class Thumbnails {
    @com.google.gson.annotations.SerializedName(value = "medium")
    @org.jetbrains.annotations.Nullable()
    private final com.wilplayer.android.data.model.ThumbnailInfo medium = null;
    @com.google.gson.annotations.SerializedName(value = "high")
    @org.jetbrains.annotations.Nullable()
    private final com.wilplayer.android.data.model.ThumbnailInfo high = null;
    @com.google.gson.annotations.SerializedName(value = "standard")
    @org.jetbrains.annotations.Nullable()
    private final com.wilplayer.android.data.model.ThumbnailInfo standard = null;
    @com.google.gson.annotations.SerializedName(value = "maxres")
    @org.jetbrains.annotations.Nullable()
    private final com.wilplayer.android.data.model.ThumbnailInfo maxres = null;
    
    public Thumbnails(@org.jetbrains.annotations.Nullable()
    com.wilplayer.android.data.model.ThumbnailInfo p0_772401952, @org.jetbrains.annotations.Nullable()
    com.wilplayer.android.data.model.ThumbnailInfo medium, @org.jetbrains.annotations.Nullable()
    com.wilplayer.android.data.model.ThumbnailInfo high, @org.jetbrains.annotations.Nullable()
    com.wilplayer.android.data.model.ThumbnailInfo standard, @org.jetbrains.annotations.Nullable()
    com.wilplayer.android.data.model.ThumbnailInfo maxres) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.data.model.ThumbnailInfo getDefault() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.data.model.ThumbnailInfo getMedium() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.data.model.ThumbnailInfo getHigh() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.data.model.ThumbnailInfo getStandard() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.data.model.ThumbnailInfo getMaxres() {
        return null;
    }
    
    public Thumbnails() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.data.model.ThumbnailInfo component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.data.model.ThumbnailInfo component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.data.model.ThumbnailInfo component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.data.model.ThumbnailInfo component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wilplayer.android.data.model.ThumbnailInfo component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.model.Thumbnails copy(@org.jetbrains.annotations.Nullable()
    com.wilplayer.android.data.model.ThumbnailInfo p0_772401952, @org.jetbrains.annotations.Nullable()
    com.wilplayer.android.data.model.ThumbnailInfo medium, @org.jetbrains.annotations.Nullable()
    com.wilplayer.android.data.model.ThumbnailInfo high, @org.jetbrains.annotations.Nullable()
    com.wilplayer.android.data.model.ThumbnailInfo standard, @org.jetbrains.annotations.Nullable()
    com.wilplayer.android.data.model.ThumbnailInfo maxres) {
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