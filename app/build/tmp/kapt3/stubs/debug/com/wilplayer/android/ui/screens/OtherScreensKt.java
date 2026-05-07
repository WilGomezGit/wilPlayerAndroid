package com.wilplayer.android.ui.screens;

import android.content.Intent;
import androidx.compose.foundation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.text.font.FontWeight;
import com.wilplayer.android.domain.model.Playlist;
import com.wilplayer.android.domain.model.Song;
import com.wilplayer.android.ui.PlaylistDetailViewModel;
import com.wilplayer.android.ui.PlayerViewModel;
import com.wilplayer.android.ui.ProfileViewModel;
import com.wilplayer.android.ui.ProfileUiState;
import com.wilplayer.android.ui.components.*;
import com.wilplayer.android.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001a&\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001a\u001e\u0010\t\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001aV\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0007\u001a\u00020\u0014H\u0007\u001a\u001c\u0010\u0015\u001a\u00020\u00012\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a&\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\f2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001a&\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001a\u0018\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\fH\u0003\u001a\u0010\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002\u00a8\u0006 "}, d2 = {"AboutDialog", "", "onDismiss", "Lkotlin/Function0;", "ApiKeyDialog", "state", "Lcom/wilplayer/android/ui/ProfileUiState;", "vm", "Lcom/wilplayer/android/ui/ProfileViewModel;", "CacheDialog", "PlaylistDetailScreen", "playlistId", "", "playerVm", "Lcom/wilplayer/android/ui/PlayerViewModel;", "onNavigateToPlayer", "onNavigateToSearch", "onBack", "modifier", "Landroidx/compose/ui/Modifier;", "Lcom/wilplayer/android/ui/PlaylistDetailViewModel;", "ProfileScreen", "SettingsItem", "emoji", "label", "onClick", "ShuffleQualityDialog", "StatItem", "value", "formatTotalDuration", "ms", "", "app_debug"})
public final class OtherScreensKt {
    
    @androidx.compose.runtime.Composable()
    public static final void PlaylistDetailScreen(@org.jetbrains.annotations.NotNull()
    java.lang.String playlistId, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.ui.PlayerViewModel playerVm, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToPlayer, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToSearch, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.ui.PlaylistDetailViewModel vm) {
    }
    
    private static final java.lang.String formatTotalDuration(long ms) {
        return null;
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ProfileScreen(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.ui.ProfileViewModel vm) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SettingsItem(java.lang.String emoji, java.lang.String label, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ApiKeyDialog(com.wilplayer.android.ui.ProfileUiState state, com.wilplayer.android.ui.ProfileViewModel vm, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ShuffleQualityDialog(com.wilplayer.android.ui.ProfileUiState state, com.wilplayer.android.ui.ProfileViewModel vm, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CacheDialog(com.wilplayer.android.ui.ProfileViewModel vm, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AboutDialog(kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void StatItem(java.lang.String label, java.lang.String value) {
    }
}