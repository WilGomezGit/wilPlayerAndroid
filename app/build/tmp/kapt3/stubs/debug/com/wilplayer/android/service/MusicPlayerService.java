package com.wilplayer.android.service;

import android.content.Intent;
import android.net.Uri;
import androidx.media3.common.*;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.session.DefaultMediaNotificationProvider;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionService;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.wilplayer.android.R;
import com.wilplayer.android.data.extractor.YoutubeStreamExtractor;
import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.*;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001dB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010\u0019\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0014H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/wilplayer/android/service/MusicPlayerService;", "Landroidx/media3/session/MediaSessionService;", "()V", "extractor", "Lcom/wilplayer/android/data/extractor/YoutubeStreamExtractor;", "getExtractor", "()Lcom/wilplayer/android/data/extractor/YoutubeStreamExtractor;", "setExtractor", "(Lcom/wilplayer/android/data/extractor/YoutubeStreamExtractor;)V", "mediaSession", "Landroidx/media3/session/MediaSession;", "player", "Landroidx/media3/exoplayer/ExoPlayer;", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "extractYouTubeVideoId", "", "uri", "Landroid/net/Uri;", "onCreate", "", "onDestroy", "onGetSession", "controllerInfo", "Landroidx/media3/session/MediaSession$ControllerInfo;", "onTaskRemoved", "rootIntent", "Landroid/content/Intent;", "preResolveNextItem", "WilMediaSessionCallback", "app_debug"})
public final class MusicPlayerService extends androidx.media3.session.MediaSessionService {
    @javax.inject.Inject()
    public com.wilplayer.android.data.extractor.YoutubeStreamExtractor extractor;
    @org.jetbrains.annotations.Nullable()
    private androidx.media3.session.MediaSession mediaSession;
    private androidx.media3.exoplayer.ExoPlayer player;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    
    public MusicPlayerService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.extractor.YoutubeStreamExtractor getExtractor() {
        return null;
    }
    
    public final void setExtractor(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.extractor.YoutubeStreamExtractor p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public androidx.media3.session.MediaSession onGetSession(@org.jetbrains.annotations.NotNull()
    androidx.media3.session.MediaSession.ControllerInfo controllerInfo) {
        return null;
    }
    
    @java.lang.Override()
    public void onTaskRemoved(@org.jetbrains.annotations.Nullable()
    android.content.Intent rootIntent) {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    /**
     * While the current song plays, resolve the next 3 songs' audio stream URLs
     * in parallel so ExoPlayer can load them without buffering gaps.
     */
    private final void preResolveNextItem() {
    }
    
    /**
     * Returns the YouTube video ID if [uri] is a standard YouTube watch URL,
     * or null when [uri] is already a direct audio stream (no extraction needed).
     */
    private final java.lang.String extractYouTubeVideoId(android.net.Uri uri) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J2\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016\u00a8\u0006\f"}, d2 = {"Lcom/wilplayer/android/service/MusicPlayerService$WilMediaSessionCallback;", "Landroidx/media3/session/MediaSession$Callback;", "(Lcom/wilplayer/android/service/MusicPlayerService;)V", "onAddMediaItems", "Lcom/google/common/util/concurrent/ListenableFuture;", "", "Landroidx/media3/common/MediaItem;", "mediaSession", "Landroidx/media3/session/MediaSession;", "controller", "Landroidx/media3/session/MediaSession$ControllerInfo;", "mediaItems", "app_debug"})
    final class WilMediaSessionCallback implements androidx.media3.session.MediaSession.Callback {
        
        public WilMediaSessionCallback() {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.google.common.util.concurrent.ListenableFuture<java.util.List<androidx.media3.common.MediaItem>> onAddMediaItems(@org.jetbrains.annotations.NotNull()
        androidx.media3.session.MediaSession mediaSession, @org.jetbrains.annotations.NotNull()
        androidx.media3.session.MediaSession.ControllerInfo controller, @org.jetbrains.annotations.NotNull()
        java.util.List<androidx.media3.common.MediaItem> mediaItems) {
            return null;
        }
    }
}