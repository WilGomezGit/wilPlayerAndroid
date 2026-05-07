package com.wilplayer.android.ui.screens;

import androidx.compose.foundation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.lazy.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import com.wilplayer.android.domain.model.Playlist;
import com.wilplayer.android.domain.model.Song;
import com.wilplayer.android.ui.LibraryViewModel;
import com.wilplayer.android.ui.PlayerViewModel;
import com.wilplayer.android.ui.components.*;
import com.wilplayer.android.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000T\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u001a*\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0006H\u0003\u001aT\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u00062\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0006H\u0003\u001a*\u0010\u000f\u001a\u00020\u00012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\fH\u0003\u001a\u0010\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0004H\u0003\u001a<\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\fH\u0003\u001aF\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00062\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\f2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0007\u001aF\u0010 \u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u0006\u0010!\u001a\u00020\u00152\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u00062\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0006H\u0003\u001a&\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00010\fH\u0003\u001a2\u0010(\u001a\u00020\u00012\f\u0010)\u001a\b\u0012\u0004\u0012\u00020$0\u00032\u0006\u0010!\u001a\u00020\u00152\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0006H\u0003\u00a8\u0006+"}, d2 = {"ArtistList", "", "artists", "", "", "onArtistClick", "Lkotlin/Function1;", "ArtistSongsSheet", "artist", "songs", "Lcom/wilplayer/android/domain/model/Song;", "onDismiss", "Lkotlin/Function0;", "onSongClick", "onMoreClick", "CreatePlaylistDialog", "onConfirm", "EmptyState", "message", "ImportYouTubeDialog", "isImporting", "", "error", "LibraryScreen", "playerVm", "Lcom/wilplayer/android/ui/PlayerViewModel;", "onNavigateToPlaylist", "onNavigateToPlayer", "modifier", "Landroidx/compose/ui/Modifier;", "vm", "Lcom/wilplayer/android/ui/LibraryViewModel;", "LikedSongsList", "isLoading", "PlaylistItem", "playlist", "Lcom/wilplayer/android/domain/model/Playlist;", "paletteIndex", "", "onClick", "PlaylistList", "playlists", "onPlaylistClick", "app_debug"})
public final class LibraryScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void LibraryScreen(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.ui.PlayerViewModel playerVm, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onNavigateToPlaylist, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToPlayer, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.ui.LibraryViewModel vm) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PlaylistList(java.util.List<com.wilplayer.android.domain.model.Playlist> playlists, boolean isLoading, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onPlaylistClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LikedSongsList(java.util.List<com.wilplayer.android.domain.model.Song> songs, boolean isLoading, kotlin.jvm.functions.Function1<? super com.wilplayer.android.domain.model.Song, kotlin.Unit> onSongClick, kotlin.jvm.functions.Function1<? super com.wilplayer.android.domain.model.Song, kotlin.Unit> onMoreClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PlaylistItem(com.wilplayer.android.domain.model.Playlist playlist, int paletteIndex, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ArtistList(java.util.List<java.lang.String> artists, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onArtistClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ArtistSongsSheet(java.lang.String artist, java.util.List<com.wilplayer.android.domain.model.Song> songs, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function1<? super com.wilplayer.android.domain.model.Song, kotlin.Unit> onSongClick, kotlin.jvm.functions.Function1<? super com.wilplayer.android.domain.model.Song, kotlin.Unit> onMoreClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ImportYouTubeDialog(boolean isImporting, java.lang.String error, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onConfirm, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EmptyState(java.lang.String message) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CreatePlaylistDialog(kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onConfirm, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
}