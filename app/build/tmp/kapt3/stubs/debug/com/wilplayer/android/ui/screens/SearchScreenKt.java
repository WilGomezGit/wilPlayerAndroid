package com.wilplayer.android.ui.screens;

import androidx.compose.foundation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.lazy.*;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.style.TextOverflow;
import com.wilplayer.android.domain.model.Song;
import com.wilplayer.android.ui.PlayerViewModel;
import com.wilplayer.android.ui.SearchViewModel;
import com.wilplayer.android.ui.components.*;
import com.wilplayer.android.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001a^\u0010\u0005\u001a\u00020\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000fH\u0003\u001a2\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u001a2\u0010\u0018\u001a\u00020\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001aD\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00042\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0003\u00a8\u0006\u001f"}, d2 = {"GenreGrid", "", "onGenreClick", "Lkotlin/Function1;", "", "SearchResults", "songs", "", "Lcom/wilplayer/android/domain/model/Song;", "isLoading", "", "error", "onSongClick", "onMoreClick", "onRetry", "Lkotlin/Function0;", "SearchScreen", "playerVm", "Lcom/wilplayer/android/ui/PlayerViewModel;", "onNavigateToPlayer", "modifier", "Landroidx/compose/ui/Modifier;", "vm", "Lcom/wilplayer/android/ui/SearchViewModel;", "TrendingPreview", "WilSearchBar", "query", "onQueryChange", "onSearch", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "app_debug"})
public final class SearchScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void SearchScreen(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.ui.PlayerViewModel playerVm, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToPlayer, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.ui.SearchViewModel vm) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void WilSearchBar(java.lang.String query, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onQueryChange, kotlin.jvm.functions.Function0<kotlin.Unit> onSearch, androidx.compose.ui.focus.FocusRequester focusRequester, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TrendingPreview(java.util.List<com.wilplayer.android.domain.model.Song> songs, boolean isLoading, kotlin.jvm.functions.Function1<? super com.wilplayer.android.domain.model.Song, kotlin.Unit> onSongClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void GenreGrid(kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onGenreClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SearchResults(java.util.List<com.wilplayer.android.domain.model.Song> songs, boolean isLoading, java.lang.String error, kotlin.jvm.functions.Function1<? super com.wilplayer.android.domain.model.Song, kotlin.Unit> onSongClick, kotlin.jvm.functions.Function1<? super com.wilplayer.android.domain.model.Song, kotlin.Unit> onMoreClick, kotlin.jvm.functions.Function0<kotlin.Unit> onRetry) {
    }
}