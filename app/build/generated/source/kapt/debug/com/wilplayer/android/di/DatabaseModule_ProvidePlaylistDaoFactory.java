package com.wilplayer.android.di;

import com.wilplayer.android.data.local.WilPlayerDatabase;
import com.wilplayer.android.data.local.dao.PlaylistDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvidePlaylistDaoFactory implements Factory<PlaylistDao> {
  private final Provider<WilPlayerDatabase> dbProvider;

  public DatabaseModule_ProvidePlaylistDaoFactory(Provider<WilPlayerDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public PlaylistDao get() {
    return providePlaylistDao(dbProvider.get());
  }

  public static DatabaseModule_ProvidePlaylistDaoFactory create(
      Provider<WilPlayerDatabase> dbProvider) {
    return new DatabaseModule_ProvidePlaylistDaoFactory(dbProvider);
  }

  public static PlaylistDao providePlaylistDao(WilPlayerDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.providePlaylistDao(db));
  }
}
