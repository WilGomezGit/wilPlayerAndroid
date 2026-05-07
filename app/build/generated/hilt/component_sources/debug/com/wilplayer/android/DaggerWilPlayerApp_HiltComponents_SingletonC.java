package com.wilplayer.android;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.wilplayer.android.data.api.YouTubeApiService;
import com.wilplayer.android.data.extractor.YoutubeStreamExtractor;
import com.wilplayer.android.data.local.WilPlayerDatabase;
import com.wilplayer.android.data.local.dao.HistoryDao;
import com.wilplayer.android.data.local.dao.PlaylistDao;
import com.wilplayer.android.data.local.dao.SongDao;
import com.wilplayer.android.data.preferences.UserPreferences;
import com.wilplayer.android.data.repository.MusicRepository;
import com.wilplayer.android.di.DatabaseModule_ProvideDatabaseFactory;
import com.wilplayer.android.di.DatabaseModule_ProvideHistoryDaoFactory;
import com.wilplayer.android.di.DatabaseModule_ProvidePlaylistDaoFactory;
import com.wilplayer.android.di.DatabaseModule_ProvideSongDaoFactory;
import com.wilplayer.android.di.NetworkModule_ProvideOkHttpClientFactory;
import com.wilplayer.android.di.NetworkModule_ProvideRetrofitFactory;
import com.wilplayer.android.di.NetworkModule_ProvideYouTubeApiServiceFactory;
import com.wilplayer.android.di.PreferencesModule_ProvideUserPreferencesFactory;
import com.wilplayer.android.service.MusicPlayerService;
import com.wilplayer.android.service.MusicPlayerService_MembersInjector;
import com.wilplayer.android.ui.HomeViewModel;
import com.wilplayer.android.ui.HomeViewModel_HiltModules;
import com.wilplayer.android.ui.LibraryViewModel;
import com.wilplayer.android.ui.LibraryViewModel_HiltModules;
import com.wilplayer.android.ui.MainActivity;
import com.wilplayer.android.ui.PlayerViewModel;
import com.wilplayer.android.ui.PlayerViewModel_HiltModules;
import com.wilplayer.android.ui.PlaylistDetailViewModel;
import com.wilplayer.android.ui.PlaylistDetailViewModel_HiltModules;
import com.wilplayer.android.ui.ProfileViewModel;
import com.wilplayer.android.ui.ProfileViewModel_HiltModules;
import com.wilplayer.android.ui.SearchViewModel;
import com.wilplayer.android.ui.SearchViewModel_HiltModules;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
public final class DaggerWilPlayerApp_HiltComponents_SingletonC {
  private DaggerWilPlayerApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public WilPlayerApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements WilPlayerApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public WilPlayerApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements WilPlayerApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public WilPlayerApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements WilPlayerApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public WilPlayerApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements WilPlayerApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public WilPlayerApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements WilPlayerApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public WilPlayerApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements WilPlayerApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public WilPlayerApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements WilPlayerApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public WilPlayerApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends WilPlayerApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends WilPlayerApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends WilPlayerApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends WilPlayerApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(ImmutableMap.<String, Boolean>builderWithExpectedSize(6).put(LazyClassKeyProvider.com_wilplayer_android_ui_HomeViewModel, HomeViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_wilplayer_android_ui_LibraryViewModel, LibraryViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_wilplayer_android_ui_PlayerViewModel, PlayerViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_wilplayer_android_ui_PlaylistDetailViewModel, PlaylistDetailViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_wilplayer_android_ui_ProfileViewModel, ProfileViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_wilplayer_android_ui_SearchViewModel, SearchViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_wilplayer_android_ui_PlayerViewModel = "com.wilplayer.android.ui.PlayerViewModel";

      static String com_wilplayer_android_ui_HomeViewModel = "com.wilplayer.android.ui.HomeViewModel";

      static String com_wilplayer_android_ui_LibraryViewModel = "com.wilplayer.android.ui.LibraryViewModel";

      static String com_wilplayer_android_ui_ProfileViewModel = "com.wilplayer.android.ui.ProfileViewModel";

      static String com_wilplayer_android_ui_PlaylistDetailViewModel = "com.wilplayer.android.ui.PlaylistDetailViewModel";

      static String com_wilplayer_android_ui_SearchViewModel = "com.wilplayer.android.ui.SearchViewModel";

      @KeepFieldType
      PlayerViewModel com_wilplayer_android_ui_PlayerViewModel2;

      @KeepFieldType
      HomeViewModel com_wilplayer_android_ui_HomeViewModel2;

      @KeepFieldType
      LibraryViewModel com_wilplayer_android_ui_LibraryViewModel2;

      @KeepFieldType
      ProfileViewModel com_wilplayer_android_ui_ProfileViewModel2;

      @KeepFieldType
      PlaylistDetailViewModel com_wilplayer_android_ui_PlaylistDetailViewModel2;

      @KeepFieldType
      SearchViewModel com_wilplayer_android_ui_SearchViewModel2;
    }
  }

  private static final class ViewModelCImpl extends WilPlayerApp_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<HomeViewModel> homeViewModelProvider;

    private Provider<LibraryViewModel> libraryViewModelProvider;

    private Provider<PlayerViewModel> playerViewModelProvider;

    private Provider<PlaylistDetailViewModel> playlistDetailViewModelProvider;

    private Provider<ProfileViewModel> profileViewModelProvider;

    private Provider<SearchViewModel> searchViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.libraryViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.playerViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.playlistDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.profileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.searchViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(ImmutableMap.<String, javax.inject.Provider<ViewModel>>builderWithExpectedSize(6).put(LazyClassKeyProvider.com_wilplayer_android_ui_HomeViewModel, ((Provider) homeViewModelProvider)).put(LazyClassKeyProvider.com_wilplayer_android_ui_LibraryViewModel, ((Provider) libraryViewModelProvider)).put(LazyClassKeyProvider.com_wilplayer_android_ui_PlayerViewModel, ((Provider) playerViewModelProvider)).put(LazyClassKeyProvider.com_wilplayer_android_ui_PlaylistDetailViewModel, ((Provider) playlistDetailViewModelProvider)).put(LazyClassKeyProvider.com_wilplayer_android_ui_ProfileViewModel, ((Provider) profileViewModelProvider)).put(LazyClassKeyProvider.com_wilplayer_android_ui_SearchViewModel, ((Provider) searchViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<Class<?>, Object>of();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_wilplayer_android_ui_PlaylistDetailViewModel = "com.wilplayer.android.ui.PlaylistDetailViewModel";

      static String com_wilplayer_android_ui_SearchViewModel = "com.wilplayer.android.ui.SearchViewModel";

      static String com_wilplayer_android_ui_PlayerViewModel = "com.wilplayer.android.ui.PlayerViewModel";

      static String com_wilplayer_android_ui_HomeViewModel = "com.wilplayer.android.ui.HomeViewModel";

      static String com_wilplayer_android_ui_LibraryViewModel = "com.wilplayer.android.ui.LibraryViewModel";

      static String com_wilplayer_android_ui_ProfileViewModel = "com.wilplayer.android.ui.ProfileViewModel";

      @KeepFieldType
      PlaylistDetailViewModel com_wilplayer_android_ui_PlaylistDetailViewModel2;

      @KeepFieldType
      SearchViewModel com_wilplayer_android_ui_SearchViewModel2;

      @KeepFieldType
      PlayerViewModel com_wilplayer_android_ui_PlayerViewModel2;

      @KeepFieldType
      HomeViewModel com_wilplayer_android_ui_HomeViewModel2;

      @KeepFieldType
      LibraryViewModel com_wilplayer_android_ui_LibraryViewModel2;

      @KeepFieldType
      ProfileViewModel com_wilplayer_android_ui_ProfileViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.wilplayer.android.ui.HomeViewModel 
          return (T) new HomeViewModel(singletonCImpl.musicRepositoryProvider.get());

          case 1: // com.wilplayer.android.ui.LibraryViewModel 
          return (T) new LibraryViewModel(singletonCImpl.musicRepositoryProvider.get());

          case 2: // com.wilplayer.android.ui.PlayerViewModel 
          return (T) new PlayerViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.musicRepositoryProvider.get(), singletonCImpl.youtubeStreamExtractorProvider.get());

          case 3: // com.wilplayer.android.ui.PlaylistDetailViewModel 
          return (T) new PlaylistDetailViewModel(singletonCImpl.musicRepositoryProvider.get());

          case 4: // com.wilplayer.android.ui.ProfileViewModel 
          return (T) new ProfileViewModel(singletonCImpl.provideUserPreferencesProvider.get(), singletonCImpl.musicRepositoryProvider.get(), singletonCImpl.youtubeStreamExtractorProvider.get());

          case 5: // com.wilplayer.android.ui.SearchViewModel 
          return (T) new SearchViewModel(singletonCImpl.musicRepositoryProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends WilPlayerApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends WilPlayerApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }

    @Override
    public void injectMusicPlayerService(MusicPlayerService musicPlayerService) {
      injectMusicPlayerService2(musicPlayerService);
    }

    @CanIgnoreReturnValue
    private MusicPlayerService injectMusicPlayerService2(MusicPlayerService instance) {
      MusicPlayerService_MembersInjector.injectExtractor(instance, singletonCImpl.youtubeStreamExtractorProvider.get());
      return instance;
    }
  }

  private static final class SingletonCImpl extends WilPlayerApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<YouTubeApiService> provideYouTubeApiServiceProvider;

    private Provider<WilPlayerDatabase> provideDatabaseProvider;

    private Provider<UserPreferences> provideUserPreferencesProvider;

    private Provider<MusicRepository> musicRepositoryProvider;

    private Provider<YoutubeStreamExtractor> youtubeStreamExtractorProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private SongDao songDao() {
      return DatabaseModule_ProvideSongDaoFactory.provideSongDao(provideDatabaseProvider.get());
    }

    private PlaylistDao playlistDao() {
      return DatabaseModule_ProvidePlaylistDaoFactory.providePlaylistDao(provideDatabaseProvider.get());
    }

    private HistoryDao historyDao() {
      return DatabaseModule_ProvideHistoryDaoFactory.provideHistoryDao(provideDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 3));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 2));
      this.provideYouTubeApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<YouTubeApiService>(singletonCImpl, 1));
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<WilPlayerDatabase>(singletonCImpl, 4));
      this.provideUserPreferencesProvider = DoubleCheck.provider(new SwitchingProvider<UserPreferences>(singletonCImpl, 5));
      this.musicRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<MusicRepository>(singletonCImpl, 0));
      this.youtubeStreamExtractorProvider = DoubleCheck.provider(new SwitchingProvider<YoutubeStreamExtractor>(singletonCImpl, 6));
    }

    @Override
    public void injectWilPlayerApp(WilPlayerApp wilPlayerApp) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.wilplayer.android.data.repository.MusicRepository 
          return (T) new MusicRepository(singletonCImpl.provideYouTubeApiServiceProvider.get(), singletonCImpl.songDao(), singletonCImpl.playlistDao(), singletonCImpl.historyDao(), singletonCImpl.provideUserPreferencesProvider.get());

          case 1: // com.wilplayer.android.data.api.YouTubeApiService 
          return (T) NetworkModule_ProvideYouTubeApiServiceFactory.provideYouTubeApiService(singletonCImpl.provideRetrofitProvider.get());

          case 2: // retrofit2.Retrofit 
          return (T) NetworkModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.provideOkHttpClientProvider.get());

          case 3: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient();

          case 4: // com.wilplayer.android.data.local.WilPlayerDatabase 
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 5: // com.wilplayer.android.data.preferences.UserPreferences 
          return (T) PreferencesModule_ProvideUserPreferencesFactory.provideUserPreferences(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 6: // com.wilplayer.android.data.extractor.YoutubeStreamExtractor 
          return (T) new YoutubeStreamExtractor(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
