package com.wilplayer.android.di;

import com.wilplayer.android.data.api.YouTubeApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvideYouTubeApiServiceFactory implements Factory<YouTubeApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideYouTubeApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public YouTubeApiService get() {
    return provideYouTubeApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideYouTubeApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideYouTubeApiServiceFactory(retrofitProvider);
  }

  public static YouTubeApiService provideYouTubeApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideYouTubeApiService(retrofit));
  }
}
