package com.wilplayer.android.data.extractor;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class YoutubeStreamExtractor_Factory implements Factory<YoutubeStreamExtractor> {
  private final Provider<Context> contextProvider;

  public YoutubeStreamExtractor_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public YoutubeStreamExtractor get() {
    return newInstance(contextProvider.get());
  }

  public static YoutubeStreamExtractor_Factory create(Provider<Context> contextProvider) {
    return new YoutubeStreamExtractor_Factory(contextProvider);
  }

  public static YoutubeStreamExtractor newInstance(Context context) {
    return new YoutubeStreamExtractor(context);
  }
}
