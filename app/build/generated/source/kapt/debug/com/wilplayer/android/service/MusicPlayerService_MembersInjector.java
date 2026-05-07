package com.wilplayer.android.service;

import com.wilplayer.android.data.extractor.YoutubeStreamExtractor;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MusicPlayerService_MembersInjector implements MembersInjector<MusicPlayerService> {
  private final Provider<YoutubeStreamExtractor> extractorProvider;

  public MusicPlayerService_MembersInjector(Provider<YoutubeStreamExtractor> extractorProvider) {
    this.extractorProvider = extractorProvider;
  }

  public static MembersInjector<MusicPlayerService> create(
      Provider<YoutubeStreamExtractor> extractorProvider) {
    return new MusicPlayerService_MembersInjector(extractorProvider);
  }

  @Override
  public void injectMembers(MusicPlayerService instance) {
    injectExtractor(instance, extractorProvider.get());
  }

  @InjectedFieldSignature("com.wilplayer.android.service.MusicPlayerService.extractor")
  public static void injectExtractor(MusicPlayerService instance,
      YoutubeStreamExtractor extractor) {
    instance.extractor = extractor;
  }
}
