package com.wilplayer.android.ui.screens;

import androidx.compose.animation.*;
import androidx.compose.foundation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextOverflow;
import com.wilplayer.android.domain.model.*;
import com.wilplayer.android.ui.PlayerViewModel;
import com.wilplayer.android.ui.components.*;
import com.wilplayer.android.ui.theme.*;
import android.media.AudioManager;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/wilplayer/android/ui/screens/PlayerTab;", "", "(Ljava/lang/String;I)V", "COVER", "LYRICS", "RELATED", "app_debug"})
public enum PlayerTab {
    /*public static final*/ COVER /* = new COVER() */,
    /*public static final*/ LYRICS /* = new LYRICS() */,
    /*public static final*/ RELATED /* = new RELATED() */;
    
    PlayerTab() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.wilplayer.android.ui.screens.PlayerTab> getEntries() {
        return null;
    }
}