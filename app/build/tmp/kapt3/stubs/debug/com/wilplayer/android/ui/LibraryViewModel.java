package com.wilplayer.android.ui;

import androidx.lifecycle.ViewModel;
import com.wilplayer.android.data.repository.MusicRepository;
import com.wilplayer.android.data.repository.Result;
import com.wilplayer.android.domain.model.Playlist;
import com.wilplayer.android.domain.model.Song;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0011J\u000e\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0011R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lcom/wilplayer/android/ui/LibraryViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/wilplayer/android/data/repository/MusicRepository;", "(Lcom/wilplayer/android/data/repository/MusicRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/wilplayer/android/ui/LibraryUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearImportError", "", "clearSelectedArtist", "createPlaylist", "name", "", "importYouTubePlaylist", "url", "selectArtist", "artist", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LibraryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.wilplayer.android.data.repository.MusicRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.wilplayer.android.ui.LibraryUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.wilplayer.android.ui.LibraryUiState> uiState = null;
    
    @javax.inject.Inject()
    public LibraryViewModel(@org.jetbrains.annotations.NotNull()
    com.wilplayer.android.data.repository.MusicRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.wilplayer.android.ui.LibraryUiState> getUiState() {
        return null;
    }
    
    public final void createPlaylist(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void selectArtist(@org.jetbrains.annotations.NotNull()
    java.lang.String artist) {
    }
    
    public final void clearSelectedArtist() {
    }
    
    public final void importYouTubePlaylist(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    public final void clearImportError() {
    }
}