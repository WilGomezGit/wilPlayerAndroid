package com.wilplayer.android.ui;

import androidx.lifecycle.ViewModel;
import com.wilplayer.android.data.extractor.YoutubeStreamExtractor;
import com.wilplayer.android.data.preferences.UserPreferences;
import com.wilplayer.android.data.repository.MusicRepository;
import com.wilplayer.android.data.repository.Result;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0015J\u0006\u0010\u0019\u001a\u00020\u0011R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/wilplayer/android/ui/ProfileViewModel;", "Landroidx/lifecycle/ViewModel;", "userPrefs", "Lcom/wilplayer/android/data/preferences/UserPreferences;", "repository", "Lcom/wilplayer/android/data/repository/MusicRepository;", "extractor", "Lcom/wilplayer/android/data/extractor/YoutubeStreamExtractor;", "(Lcom/wilplayer/android/data/preferences/UserPreferences;Lcom/wilplayer/android/data/repository/MusicRepository;Lcom/wilplayer/android/data/extractor/YoutubeStreamExtractor;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/wilplayer/android/ui/ProfileUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearStreamCache", "", "isValidApiKeyFormat", "", "key", "", "saveApiKey", "saveShuffleQuality", "quality", "testApiKey", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ProfileViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.wilplayer.android.data.preferences.UserPreferences userPrefs = null;
    @org.jetbrains.annotations.NotNull()
    private final com.wilplayer.android.data.repository.MusicRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.wilplayer.android.data.extractor.YoutubeStreamExtractor extractor = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.wilplayer.android.ui.ProfileUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.wilplayer.android.ui.ProfileUiState> uiState = null;
    
    @javax.inject.Inject()
    public ProfileViewModel(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.preferences.UserPreferences userPrefs, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.repository.MusicRepository repository, @org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.extractor.YoutubeStreamExtractor extractor) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.wilplayer.android.ui.ProfileUiState> getUiState() {
        return null;
    }
    
    public final boolean isValidApiKeyFormat(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return false;
    }
    
    public final void saveApiKey(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
    }
    
    public final void testApiKey() {
    }
    
    public final void saveShuffleQuality(@org.jetbrains.annotations.NotNull()
    java.lang.String quality) {
    }
    
    /**
     * Clears the in-memory stream URL cache so the next play re-extracts fresh URLs.
     */
    public final void clearStreamCache() {
    }
}