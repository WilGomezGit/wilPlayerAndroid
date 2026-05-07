package com.wilplayer.android.ui.components;

import androidx.compose.animation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import com.wilplayer.android.domain.model.Song;
import com.wilplayer.android.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000N\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a^\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007\u001a,\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007\u001a.\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u00182\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\u0012\u0010\u0019\u001a\u00020\u00012\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0016H\u0003\u00a8\u0006\u001d"}, d2 = {"MiniPlayer", "", "song", "Lcom/wilplayer/android/domain/model/Song;", "isPlaying", "", "progress", "", "paletteIndex", "", "onPlayPause", "Lkotlin/Function0;", "onSkipNext", "onExpand", "modifier", "Landroidx/compose/ui/Modifier;", "SectionHeader", "title", "", "onSeeAll", "WilBottomNav", "activeTab", "Lcom/wilplayer/android/ui/components/NavTab;", "onTabSelected", "Lkotlin/Function1;", "WilDivider", "tabIcon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "tab", "app_debug"})
public final class NavigationKt {
    
    @androidx.compose.runtime.Composable()
    public static final void WilBottomNav(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.ui.components.NavTab activeTab, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.wilplayer.android.ui.components.NavTab, kotlin.Unit> onTabSelected, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final androidx.compose.ui.graphics.vector.ImageVector tabIcon(com.wilplayer.android.ui.components.NavTab tab) {
        return null;
    }
    
    @androidx.compose.runtime.Composable()
    public static final void MiniPlayer(@org.jetbrains.annotations.Nullable()
    com.wilplayer.android.domain.model.Song song, boolean isPlaying, float progress, int paletteIndex, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPlayPause, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSkipNext, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onExpand, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SectionHeader(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSeeAll, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void WilDivider(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
}