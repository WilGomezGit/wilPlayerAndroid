package com.wilplayer.android.di;

import com.wilplayer.android.data.local.WilPlayerDatabase;
import com.wilplayer.android.data.local.dao.HistoryDao;
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
public final class DatabaseModule_ProvideHistoryDaoFactory implements Factory<HistoryDao> {
  private final Provider<WilPlayerDatabase> dbProvider;

  public DatabaseModule_ProvideHistoryDaoFactory(Provider<WilPlayerDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public HistoryDao get() {
    return provideHistoryDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideHistoryDaoFactory create(
      Provider<WilPlayerDatabase> dbProvider) {
    return new DatabaseModule_ProvideHistoryDaoFactory(dbProvider);
  }

  public static HistoryDao provideHistoryDao(WilPlayerDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideHistoryDao(db));
  }
}
