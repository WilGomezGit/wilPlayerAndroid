package com.wilplayer.android.ui;

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
public final class PlaylistDetailViewModel_Factory implements Factory<PlaylistDetailViewModel> {
  private final Provider<MusicRepository> repositoryProvider;

  public PlaylistDetailViewModel_Factory(Provider<MusicRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public PlaylistDetailViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static PlaylistDetailViewModel_Factory create(
      Provider<MusicRepository> repositoryProvider) {
    return new PlaylistDetailViewModel_Factory(repositoryProvider);
  }

  public static PlaylistDetailViewModel newInstance(MusicRepository repository) {
    return new PlaylistDetailViewModel(repository);
  }
}
