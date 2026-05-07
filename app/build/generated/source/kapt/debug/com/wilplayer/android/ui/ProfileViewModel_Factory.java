package com.wilplayer.android.ui;

import com.wilplayer.android.data.extractor.YoutubeStreamExtractor;
import com.wilplayer.android.data.preferences.UserPreferences;
import com.wilplayer.android.data.repository.MusicRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<UserPreferences> userPrefsProvider;

  private final Provider<MusicRepository> repositoryProvider;

  private final Provider<YoutubeStreamExtractor> extractorProvider;

  public ProfileViewModel_Factory(Provider<UserPreferences> userPrefsProvider,
      Provider<MusicRepository> repositoryProvider,
      Provider<YoutubeStreamExtractor> extractorProvider) {
    this.userPrefsProvider = userPrefsProvider;
    this.repositoryProvider = repositoryProvider;
    this.extractorProvider = extractorProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(userPrefsProvider.get(), repositoryProvider.get(), extractorProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<UserPreferences> userPrefsProvider,
      Provider<MusicRepository> repositoryProvider,
      Provider<YoutubeStreamExtractor> extractorProvider) {
    return new ProfileViewModel_Factory(userPrefsProvider, repositoryProvider, extractorProvider);
  }

  public static ProfileViewModel newInstance(UserPreferences userPrefs, MusicRepository repository,
      YoutubeStreamExtractor extractor) {
    return new ProfileViewModel(userPrefs, repository, extractor);
  }
}
