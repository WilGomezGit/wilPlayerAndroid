package com.wilplayer.android.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import androidx.activity.ComponentActivity;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.animation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Modifier;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavHostController;
import androidx.navigation.compose.*;
import com.wilplayer.android.ui.components.*;
import com.wilplayer.android.ui.screens.*;
import dagger.hilt.android.AndroidEntryPoint;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/wilplayer/android/ui/Routes;", "", "()V", "HOME", "", "LIBRARY", "PLAYER", "PLAYLIST", "PROFILE", "SEARCH", "playlist", "id", "app_debug"})
public final class Routes {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String HOME = "home";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SEARCH = "search";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LIBRARY = "library";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PROFILE = "profile";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PLAYER = "player";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PLAYLIST = "playlist/{playlistId}";
    @org.jetbrains.annotations.NotNull()
    public static final com.wilplayer.android.ui.Routes INSTANCE = null;
    
    private Routes() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String playlist(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
}