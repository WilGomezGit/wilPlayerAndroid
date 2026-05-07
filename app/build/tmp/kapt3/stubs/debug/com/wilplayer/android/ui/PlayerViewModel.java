package com.wilplayer.android.ui;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import androidx.lifecycle.ViewModel;
import androidx.media3.common.*;
import androidx.media3.session.MediaController;
import androidx.media3.session.SessionToken;
import com.google.common.util.concurrent.MoreExecutors;
import com.wilplayer.android.data.extractor.YoutubeStreamExtractor;
import com.wilplayer.android.data.repository.MusicRepository;
import com.wilplayer.android.domain.model.*;
import com.wilplayer.android.service.MusicPlayerService;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.*;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u0007\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0010J\u0016\u0010\'\u001a\u00020#2\u0006\u0010(\u001a\u00020\f2\u0006\u0010&\u001a\u00020\u0010J\u000e\u0010)\u001a\u00020#2\u0006\u0010&\u001a\u00020\u0010J\u0006\u0010*\u001a\u00020#J\u0006\u0010+\u001a\u00020#J\b\u0010,\u001a\u00020#H\u0002J\u0014\u0010-\u001a\u0004\u0018\u00010\u00102\b\u0010.\u001a\u0004\u0018\u00010%H\u0002J\u0006\u0010/\u001a\u00020#J\u000e\u00100\u001a\u00020#2\u0006\u0010&\u001a\u00020\u0010J\b\u00101\u001a\u00020#H\u0014J\u0006\u00102\u001a\u00020#J\u0006\u00103\u001a\u00020#J\u001e\u00104\u001a\u00020#2\u0006\u0010&\u001a\u00020\u00102\u000e\b\u0002\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000bJ\u000e\u00105\u001a\u00020#2\u0006\u00106\u001a\u000207J\b\u00108\u001a\u00020#H\u0002J\u0006\u00109\u001a\u00020#J\u0006\u0010:\u001a\u00020#J\b\u0010;\u001a\u00020#H\u0002J\u000e\u0010<\u001a\u00020#2\u0006\u0010&\u001a\u00020\u0010J\u0006\u0010=\u001a\u00020#J\u0006\u0010>\u001a\u00020#J\b\u0010?\u001a\u00020#H\u0002R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000b\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000b0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015\u00a8\u0006@"}, d2 = {"Lcom/wilplayer/android/ui/PlayerViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "repository", "Lcom/wilplayer/android/data/repository/MusicRepository;", "extractor", "Lcom/wilplayer/android/data/extractor/YoutubeStreamExtractor;", "(Landroid/content/Context;Lcom/wilplayer/android/data/repository/MusicRepository;Lcom/wilplayer/android/data/extractor/YoutubeStreamExtractor;)V", "_allPlaylists", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/wilplayer/android/domain/model/Playlist;", "_playerState", "Lcom/wilplayer/android/domain/model/PlayerState;", "_queue", "Lcom/wilplayer/android/domain/model/Song;", "_showAddToPlaylistDialog", "allPlaylists", "Lkotlinx/coroutines/flow/StateFlow;", "getAllPlaylists", "()Lkotlinx/coroutines/flow/StateFlow;", "mediaController", "Landroidx/media3/session/MediaController;", "pendingPlay", "Lkotlin/Pair;", "playerState", "getPlayerState", "progressJob", "Lkotlinx/coroutines/Job;", "queue", "getQueue", "showAddToPlaylistDialog", "getShowAddToPlaylistDialog", "addToNewPlaylist", "", "playlistName", "", "song", "addToPlaylist", "playlist", "addToQueue", "clearError", "connectPlayer", "enableSmartShuffle", "findSongByMediaId", "mediaId", "loadAllPlaylists", "onAddToPlaylistRequest", "onCleared", "onDismissAddToPlaylistDialog", "playPause", "playSong", "seekTo", "fraction", "", "setupPlayerListener", "skipToNext", "skipToPrevious", "startProgressPolling", "toggleLike", "toggleRepeat", "toggleShuffle", "updateState", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class PlayerViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.wilplayer.android.data.repository.MusicRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.wilplayer.android.data.extractor.YoutubeStreamExtractor extractor = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.wilplayer.android.domain.model.PlayerState> _playerState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.wilplayer.android.domain.model.PlayerState> playerState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.wilplayer.android.domain.model.Song>> _queue = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.wilplayer.android.domain.model.Song>> queue = null;
    @org.jetbrains.annotations.Nullable()
    private androidx.media3.session.MediaController mediaController;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job progressJob;
    @org.jetbrains.annotations.Nullable()
    private kotlin.Pair<com.wilplayer.android.domain.model.Song, ? extends java.util.List<com.wilplayer.android.domain.model.Song>> pendingPlay;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.wilplayer.android.domain.model.Playlist>> _allPlaylists = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.wilplayer.android.domain.model.Playlist>> allPlaylists = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.wilplayer.android.domain.model.Song> _showAddToPlaylistDialog = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.wilplayer.android.domain.model.Song> showAddToPlaylistDialog = null;
    
    @javax.inject.Inject()
    public PlayerViewModel(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.repository.MusicRepository repository, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.extractor.YoutubeStreamExtractor extractor) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.wilplayer.android.domain.model.PlayerState> getPlayerState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.wilplayer.android.domain.model.Song>> getQueue() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.wilplayer.android.domain.model.Playlist>> getAllPlaylists() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.wilplayer.android.domain.model.Song> getShowAddToPlaylistDialog() {
        return null;
    }
    
    public final void connectPlayer() {
    }
    
    private final void setupPlayerListener() {
    }
    
    private final void updateState() {
    }
    
    private final void startProgressPolling() {
    }
    
    public final void playSong(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.Song song, @org.jetbrains.annotations.NotNull()
    java.util.List<com.wilplayer.android.domain.model.Song> queue) {
    }
    
    public final void playPause() {
    }
    
    public final void skipToNext() {
    }
    
    public final void skipToPrevious() {
    }
    
    public final void seekTo(float fraction) {
    }
    
    public final void toggleRepeat() {
    }
    
    public final void toggleShuffle() {
    }
    
    private final void enableSmartShuffle() {
    }
    
    public final void toggleLike(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.Song song) {
    }
    
    public final void addToQueue(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.Song song) {
    }
    
    public final void loadAllPlaylists() {
    }
    
    public final void onAddToPlaylistRequest(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.Song song) {
    }
    
    public final void onDismissAddToPlaylistDialog() {
    }
    
    public final void addToPlaylist(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.Playlist playlist, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.Song song) {
    }
    
    public final void addToNewPlaylist(@org.jetbrains.annotations.NotNull()
    java.lang.String playlistName, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.Song song) {
    }
    
    public final void clearError() {
    }
    
    private final com.wilplayer.android.domain.model.Song findSongByMediaId(java.lang.String mediaId) {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}