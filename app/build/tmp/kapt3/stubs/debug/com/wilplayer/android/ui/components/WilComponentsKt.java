package com.wilplayer.android.ui.components;

import android.content.Intent;
import androidx.compose.animation.core.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import coil.request.ImageRequest;
import com.wilplayer.android.domain.model.Song;
import com.wilplayer.android.ui.theme.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material.icons.outlined.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b3\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\u001aN\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u00012\u0006\u00107\u001a\u0002082\f\u00109\u001a\b\u0012\u0004\u0012\u0002050:2\b\b\u0002\u0010;\u001a\u00020<2\b\b\u0002\u0010=\u001a\u00020>2\b\b\u0002\u0010?\u001a\u00020@H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\bA\u0010B\u001aB\u0010C\u001a\u0002052\b\u0010D\u001a\u0004\u0018\u00010E2\u0006\u0010?\u001a\u00020@2\b\b\u0002\u0010;\u001a\u00020<2\b\b\u0002\u0010F\u001a\u00020@2\b\b\u0002\u0010G\u001a\u00020HH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\bI\u0010J\u001a,\u0010K\u001a\u0002052\u0006\u0010L\u001a\u0002082\u0010\b\u0002\u0010M\u001a\n\u0012\u0004\u0012\u000205\u0018\u00010:2\b\b\u0002\u0010;\u001a\u00020<H\u0007\u001a@\u0010N\u001a\u0002052\u0006\u0010O\u001a\u0002082\u0006\u0010P\u001a\u00020Q2\b\b\u0002\u0010R\u001a\u00020S2\b\b\u0002\u0010;\u001a\u00020<2\b\b\u0002\u0010T\u001a\u00020UH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\bV\u0010W\u001a:\u0010X\u001a\u0002052\u0006\u0010Y\u001a\u00020\u00012\u0006\u0010Z\u001a\u0002082\b\b\u0002\u0010=\u001a\u00020>2\f\u00109\u001a\b\u0012\u0004\u0012\u0002050:H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b[\u0010\\\u001aF\u0010]\u001a\u0002052\u0006\u0010^\u001a\u00020_2\f\u00109\u001a\b\u0012\u0004\u0012\u0002050:2\b\b\u0002\u0010;\u001a\u00020<2\b\b\u0002\u0010?\u001a\u00020@2\b\b\u0002\u0010`\u001a\u00020@H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\ba\u0010b\u001a\u001c\u0010c\u001a\u0002052\b\b\u0002\u0010;\u001a\u00020<2\b\b\u0002\u0010d\u001a\u00020eH\u0007\u001aL\u0010f\u001a\u0002052\u0006\u0010D\u001a\u00020E2\b\b\u0002\u0010^\u001a\u00020_2\b\b\u0002\u0010G\u001a\u00020H2\f\u00109\u001a\b\u0012\u0004\u0012\u0002050:2\u000e\b\u0002\u0010g\u001a\b\u0012\u0004\u0012\u0002050:2\b\b\u0002\u0010;\u001a\u00020<H\u0007\u001aJ\u0010h\u001a\u0002052\u0006\u0010D\u001a\u00020E2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002050:2\f\u0010j\u001a\b\u0012\u0004\u0012\u0002050:2\f\u0010k\u001a\b\u0012\u0004\u0012\u0002050:2\u000e\b\u0002\u0010l\u001a\b\u0012\u0004\u0012\u0002050:H\u0007\u001aH\u0010m\u001a\u0002052\f\u00109\u001a\b\u0012\u0004\u0012\u0002050:2\b\b\u0002\u0010;\u001a\u00020<2\b\b\u0002\u0010n\u001a\u00020_2\u001c\u0010o\u001a\u0018\u0012\u0004\u0012\u00020q\u0012\u0004\u0012\u0002050p\u00a2\u0006\u0002\br\u00a2\u0006\u0002\bsH\u0007\u001aB\u0010t\u001a\u0002052\u0006\u0010u\u001a\u00020v2\u0012\u0010w\u001a\u000e\u0012\u0004\u0012\u00020v\u0012\u0004\u0012\u0002050p2\b\b\u0002\u0010;\u001a\u00020<2\b\b\u0002\u0010x\u001a\u0002082\b\b\u0002\u0010y\u001a\u000208H\u0007\"\u0011\u0010\u0000\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003\"\u0011\u0010\u0006\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\u0003\"\u0011\u0010\b\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\u0003\"\u0011\u0010\n\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\u0003\"\u0011\u0010\f\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u0003\"\u0011\u0010\u000e\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0003\"\u0011\u0010\u0010\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0003\"\u0011\u0010\u0012\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0003\"\u0011\u0010\u0014\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0003\"\u0011\u0010\u0016\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0003\"\u0011\u0010\u0018\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u0003\"\u0011\u0010\u001a\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u0003\"\u0011\u0010\u001c\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u0003\"\u0011\u0010\u001e\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010\u0003\"\u0011\u0010 \u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b!\u0010\u0003\"\u0011\u0010\"\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b#\u0010\u0003\"\u0011\u0010$\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b%\u0010\u0003\"\u0011\u0010&\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\'\u0010\u0003\"\u0011\u0010(\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b)\u0010\u0003\"\u0011\u0010*\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b+\u0010\u0003\"\u0011\u0010,\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b-\u0010\u0003\"\u0011\u0010.\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b/\u0010\u0003\"\u0011\u00100\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b1\u0010\u0003\"\u0011\u00102\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b3\u0010\u0003\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006z"}, d2 = {"AddIcon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "getAddIcon", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "ArrowBackIcon", "getArrowBackIcon", "ArrowDownIcon", "getArrowDownIcon", "CheckIcon", "getCheckIcon", "ChevronRightIcon", "getChevronRightIcon", "DownloadIcon", "getDownloadIcon", "FilterIcon", "getFilterIcon", "HeartBorderIcon", "getHeartBorderIcon", "HeartIcon", "getHeartIcon", "HomeIcon", "getHomeIcon", "LibraryIcon", "getLibraryIcon", "MicIcon", "getMicIcon", "MoreVertIcon", "getMoreVertIcon", "PauseIcon", "getPauseIcon", "PersonIcon", "getPersonIcon", "PlayIcon", "getPlayIcon", "QueueIcon", "getQueueIcon", "RepeatIcon", "getRepeatIcon", "RepeatOneIcon", "getRepeatOneIcon", "SearchIcon", "getSearchIcon", "ShareIcon", "getShareIcon", "ShuffleIcon", "getShuffleIcon", "SkipNextIcon", "getSkipNextIcon", "SkipPrevIcon", "getSkipPrevIcon", "VolumeIcon", "getVolumeIcon", "ControlButton", "", "imageVector", "contentDescription", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "tint", "Landroidx/compose/ui/graphics/Color;", "size", "Landroidx/compose/ui/unit/Dp;", "ControlButton-S-bAXkg", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JF)V", "CoverArt", "song", "Lcom/wilplayer/android/domain/model/Song;", "radius", "paletteIndex", "", "CoverArt-SW5qh9g", "(Lcom/wilplayer/android/domain/model/Song;FLandroidx/compose/ui/Modifier;FI)V", "ErrorState", "message", "onRetry", "GradientText", "text", "fontSize", "Landroidx/compose/ui/unit/TextUnit;", "fontWeight", "Landroidx/compose/ui/text/font/FontWeight;", "brush", "Landroidx/compose/ui/graphics/Brush;", "GradientText-KmRG4DE", "(Ljava/lang/String;JLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Brush;)V", "OptionsRow", "icon", "label", "OptionsRow-9LQNqLg", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;JLkotlin/jvm/functions/Function0;)V", "PlayPauseButton", "isPlaying", "", "iconSize", "PlayPauseButton-oZzcvok", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;FF)V", "ShimmerBox", "shape", "Landroidx/compose/foundation/shape/RoundedCornerShape;", "SongListItem", "onMoreClick", "SongOptionsSheet", "onDismiss", "onToggleLike", "onAddToQueue", "onAddToPlaylist", "WilGradientButton", "enabled", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "WilProgressSlider", "progress", "", "onSeek", "elapsedLabel", "totalLabel", "app_debug"})
public final class WilComponentsKt {
    
    @androidx.compose.runtime.Composable()
    public static final void WilGradientButton(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super androidx.compose.foundation.layout.RowScope, kotlin.Unit> content) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void WilProgressSlider(float progress, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> onSeek, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    java.lang.String elapsedLabel, @org.jetbrains.annotations.NotNull()
    java.lang.String totalLabel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SongListItem(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.Song song, boolean isPlaying, int paletteIndex, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onMoreClick, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ShimmerBox(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    androidx.compose.foundation.shape.RoundedCornerShape shape) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ErrorState(@org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRetry, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Reusable bottom sheet with Like, Add-to-queue, Add-to-playlist, and Share actions.
     */
    @androidx.compose.runtime.Composable()
    public static final void SongOptionsSheet(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.domain.model.Song song, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onToggleLike, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddToQueue, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddToPlaylist) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getPlayIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getPauseIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getSkipNextIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getSkipPrevIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getShuffleIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getRepeatIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getRepeatOneIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getMoreVertIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getHeartIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getHeartBorderIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getShareIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getAddIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getSearchIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getHomeIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getLibraryIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getPersonIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getArrowBackIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getArrowDownIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getVolumeIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getQueueIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getDownloadIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getMicIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getFilterIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getCheckIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.ui.graphics.vector.ImageVector getChevronRightIcon() {
        return null;
    }
}