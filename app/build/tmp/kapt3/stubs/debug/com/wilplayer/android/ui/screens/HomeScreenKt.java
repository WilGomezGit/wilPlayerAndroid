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
import com.wilplayer.android.domain.model.Song;
import com.wilplayer.android.ui.HomeViewModel;
import com.wilplayer.android.ui.PlayerViewModel;
import com.wilplayer.android.ui.components.*;
import com.wilplayer.android.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0003\u001a\u0016\u0010\u0004\u001a\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0003\u001aT\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007\u001a*\u0010\u0011\u001a\u00020\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\rH\u0003\u001a*\u0010\u0016\u001a\u00020\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\rH\u0003\u001a\u001c\u0010\u0017\u001a\u00020\u00012\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\rH\u0003\u001a<\u0010\u0019\u001a\u00020\u00012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00132\u0006\u0010\u001c\u001a\u00020\u001d2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0003\u001a\b\u0010\u001f\u001a\u00020\u0001H\u0003\u001a\b\u0010 \u001a\u00020\u0001H\u0003\u00a8\u0006!"}, d2 = {"Greeting", "", "modifier", "Landroidx/compose/ui/Modifier;", "HomeHeader", "onSearchClick", "Lkotlin/Function0;", "HomeScreen", "playerVm", "Lcom/wilplayer/android/ui/PlayerViewModel;", "onNavigateToPlayer", "onNavigateToSearch", "onNavigateToPlaylist", "Lkotlin/Function1;", "", "vm", "Lcom/wilplayer/android/ui/HomeViewModel;", "HorizontalSongCards", "songs", "", "Lcom/wilplayer/android/domain/model/Song;", "onSongClick", "HorizontalSongRows", "MoodRow", "onMoodClick", "QuickAccessGrid", "items", "Lcom/wilplayer/android/domain/model/Playlist;", "isLoading", "", "onItemClick", "RecsShimmer", "TrendingShimmer", "app_debug"})
public final class HomeScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void HomeScreen(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.ui.PlayerViewModel playerVm, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToPlayer, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToSearch, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onNavigateToPlaylist, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.ui.HomeViewModel vm) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void HomeHeader(kotlin.jvm.functions.Function0<kotlin.Unit> onSearchClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void Greeting(androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void QuickAccessGrid(java.util.List<com.wilplayer.android.domain.model.Playlist> items, boolean isLoading, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onItemClick, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void HorizontalSongCards(java.util.List<com.wilplayer.android.domain.model.Song> songs, kotlin.jvm.functions.Function1<? super com.wilplayer.android.domain.model.Song, kotlin.Unit> onSongClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void HorizontalSongRows(java.util.List<com.wilplayer.android.domain.model.Song> songs, kotlin.jvm.functions.Function1<? super com.wilplayer.android.domain.model.Song, kotlin.Unit> onSongClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MoodRow(kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onMoodClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TrendingShimmer() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void RecsShimmer() {
    }
}