package com.wilplayer.android.di;

import android.content.Context;
import androidx.room.Room;
import com.wilplayer.android.BuildConfig;
import com.wilplayer.android.data.api.YouTubeApiService;
import com.wilplayer.android.data.local.WilPlayerDatabase;
import com.wilplayer.android.data.local.dao.*;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0004H\u0007\u00a8\u0006\u000e"}, d2 = {"Lcom/wilplayer/android/di/DatabaseModule;", "", "()V", "provideDatabase", "Lcom/wilplayer/android/data/local/WilPlayerDatabase;", "context", "Landroid/content/Context;", "provideHistoryDao", "Lcom/wilplayer/android/data/local/dao/HistoryDao;", "db", "providePlaylistDao", "Lcom/wilplayer/android/data/local/dao/PlaylistDao;", "provideSongDao", "Lcom/wilplayer/android/data/local/dao/SongDao;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DatabaseModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.wilplayer.android.di.DatabaseModule INSTANCE = null;
    
    private DatabaseModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.local.WilPlayerDatabase provideDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.local.dao.SongDao provideSongDao(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.local.WilPlayerDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.local.dao.PlaylistDao providePlaylistDao(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.local.WilPlayerDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.wilplayer.android.data.local.dao.HistoryDao provideHistoryDao(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.local.WilPlayerDatabase db) {
        return null;
    }
}