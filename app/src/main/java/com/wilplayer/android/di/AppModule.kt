package com.wilplayer.android.di

import android.content.Context
import androidx.room.Room
import com.wilplayer.android.BuildConfig
import com.wilplayer.android.data.api.YouTubeApiService
import com.wilplayer.android.data.local.WilPlayerDatabase
import com.wilplayer.android.data.local.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .also { builder ->
                if (BuildConfig.DEBUG) {
                    builder.addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                }
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.YOUTUBE_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideYouTubeApiService(retrofit: Retrofit): YouTubeApiService =
        retrofit.create(YouTubeApiService::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WilPlayerDatabase =
        Room.databaseBuilder(context, WilPlayerDatabase::class.java, "wilplayer.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides fun provideSongDao(db: WilPlayerDatabase): SongDao = db.songDao()
    @Provides fun providePlaylistDao(db: WilPlayerDatabase): PlaylistDao = db.playlistDao()
    @Provides fun provideHistoryDao(db: WilPlayerDatabase): HistoryDao = db.historyDao()
}
