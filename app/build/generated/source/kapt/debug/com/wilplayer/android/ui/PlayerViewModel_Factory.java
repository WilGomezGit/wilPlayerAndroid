package com.wilplayer.android.ui;

import android.content.Context;
import com.wilplayer.android.data.extractor.YoutubeStreamExtractor;
import com.wilplayer.android.data.repository.MusicRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class PlayerViewModel_Factory implements Factory<PlayerViewModel> {
  private final Provider<Context> contextProvider;

  private final Provider<MusicRepository> repositoryProvider;

  private final Provider<YoutubeStreamExtractor> extractorProvider;

  public PlayerViewModel_Factory(Provider<Context> contextProvider,
      Provider<MusicRepository> repositoryProvider,
      Provider<YoutubeStreamExtractor> extractorProvider) {
    this.contextProvider = contextProvider;
    this.repositoryProvider = repositoryProvider;
    this.extractorProvider = extractorProvider;
  }

  @Override
  public PlayerViewModel get() {
    return newInstance(contextProvider.get(), repositoryProvider.get(), extractorProvider.get());
  }

  public static PlayerViewModel_Factory create(Provider<Context> contextProvider,
      Provider<MusicRepository> repositoryProvider,
      Provider<YoutubeStreamExtractor> extractorProvider) {
    return new PlayerViewModel_Factory(contextProvider, repositoryProvider, extractorProvider);
  }

  public static PlayerViewModel newInstance(Context context, MusicRepository repository,
      YoutubeStreamExtractor extractor) {
    return new PlayerViewModel(context, repository, extractor);
  }
}
