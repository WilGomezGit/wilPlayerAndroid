package com.wilplayer.android.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0004\u00a8\u0006\u000b"}, d2 = {"Lcom/wilplayer/android/util/DurationParser;", "", "()V", "formatDuration", "", "durationMs", "", "formatViews", "viewCount", "parseDuration", "duration", "app_debug"})
public final class DurationParser {
    @org.jetbrains.annotations.NotNull()
    public static final com.wilplayer.android.util.DurationParser INSTANCE = null;
    
    private DurationParser() {
        super();
    }
    
    /**
     * Parses PT3M42S → 222000 ms
     */
    public final long parseDuration(@org.jetbrains.annotations.NotNull()
    java.lang.String duration) {
        return 0L;
    }
    
    /**
     * 225000 ms → "3:45"
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatDuration(long durationMs) {
        return null;
    }
    
    /**
     * Convierte "3400000" → "3.4M" (vistas)
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatViews(@org.jetbrains.annotations.Nullable()
    java.lang.String viewCount) {
        return null;
    }
}