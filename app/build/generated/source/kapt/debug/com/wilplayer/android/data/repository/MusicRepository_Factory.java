package com.wilplayer.android.data.repository;

import com.wilplayer.android.data.api.YouTubeApiService;
import com.wilplayer.android.data.local.dao.HistoryDao;
import com.wilplayer.android.data.local.dao.PlaylistDao;
import com.wilplayer.android.data.local.dao.SongDao;
import com.wilplayer.android.data.preferences.UserPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class MusicRepository_Factory implements Factory<MusicRepository> {
  private final Provider<YouTubeApiService> apiServiceProvider;

  private final Provider<SongDao> songDaoProvider;

  private final Provider<PlaylistDao> playlistDaoProvider;

  private final Provider<HistoryDao> historyDaoProvider;

  private final Provider<UserPreferences> userPrefsProvider;

  public MusicRepository_Factory(Provider<YouTubeApiService> apiServiceProvider,
      Provider<SongDao> songDaoProvider, Provider<PlaylistDao> playlistDaoProvider,
      Provider<HistoryDao> historyDaoProvider, Provider<UserPreferences> userPrefsProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.songDaoProvider = songDaoProvider;
    this.playlistDaoProvider = playlistDaoProvider;
    this.historyDaoProvider = historyDaoProvider;
    this.userPrefsProvider = userPrefsProvider;
  }

  @Override
  public MusicRepository get() {
    return newInstance(apiServiceProvider.get(), songDaoProvider.get(), playlistDaoProvider.get(), historyDaoProvider.get(), userPrefsProvider.get());
  }

  public static MusicRepository_Factory create(Provider<YouTubeApiService> apiServiceProvider,
      Provider<SongDao> songDaoProvider, Provider<PlaylistDao> playlistDaoProvider,
      Provider<HistoryDao> historyDaoProvider, Provider<UserPreferences> userPrefsProvider) {
    return new MusicRepository_Factory(apiServiceProvider, songDaoProvider, playlistDaoProvider, historyDaoProvider, userPrefsProvider);
  }

  public static MusicRepository newInstance(YouTubeApiService apiService, SongDao songDao,
      PlaylistDao playlistDao, HistoryDao historyDao, UserPreferences userPrefs) {
    return new MusicRepository(apiService, songDao, playlistDao, historyDao, userPrefs);
  }
}
