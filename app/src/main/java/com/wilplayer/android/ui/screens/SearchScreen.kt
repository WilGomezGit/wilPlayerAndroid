package com.wilplayer.android.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wilplayer.android.domain.model.Song
import com.wilplayer.android.ui.PlayerViewModel
import com.wilplayer.android.ui.SearchViewModel
import com.wilplayer.android.ui.components.*
import com.wilplayer.android.ui.theme.*

@Composable
fun SearchScreen(
    playerVm: PlayerViewModel,
    onNavigateToPlayer: () -> Unit,
    modifier: Modifier = Modifier,
    vm: SearchViewModel = hiltViewModel(),
) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var optionsSong by remember { mutableStateOf<com.wilplayer.android.domain.model.Song?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Bg0)
    ) {
        // ── Title + Search Bar ─────────────────────────────────────────────────
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)) {
            Text(
                "Buscar",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = TextPrimary,
                modifier = Modifier.padding(bottom = 14.dp)
            )

            WilSearchBar(
                query = uiState.query,
                onQueryChange = vm::onQueryChange,
                onSearch = {
                    focusManager.clearFocus()
                    vm.search()
                },
                focusRequester = focusRequester,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // ── Content ────────────────────────────────────────────────────────────
        if (uiState.query.isBlank()) {
            LazyColumn {
                item {
                    TrendingPreview(
                        songs = uiState.trending,
                        isLoading = uiState.isLoading,
                        onSongClick = { song ->
                            playerVm.playSong(song, uiState.trending)
                            onNavigateToPlayer()
                        }
                    )
                }
                item {
                    GenreGrid(onGenreClick = { vm.onQueryChange(it); vm.search() })
                }
                item { Spacer(Modifier.height(16.dp)) }
            }
        } else {
            SearchResults(
                songs = uiState.results,
                isLoading = uiState.isLoading,
                error = uiState.error,
                onSongClick = { song ->
                    playerVm.playSong(song, uiState.results)
                    onNavigateToPlayer()
                },
                onMoreClick = { song -> optionsSong = song },
                onRetry = { vm.search() }
            )
        }
    }

    // ── Song options sheet ─────────────────────────────────────────────────────
    optionsSong?.let { song ->
        SongOptionsSheet(
            song = song,
            onDismiss = { optionsSong = null },
            onToggleLike = { playerVm.toggleLike(song) },
            onAddToQueue = { playerVm.addToQueue(song) },
            onAddToPlaylist = { playerVm.onAddToPlaylistRequest(song) }
        )
    }

    // ── Add to playlist dialog ────────────────────────────────────────────────
    val playlists by playerVm.allPlaylists.collectAsStateWithLifecycle()
    val songToAddToPlaylist by playerVm.showAddToPlaylistDialog.collectAsStateWithLifecycle()

    songToAddToPlaylist?.let { song ->
        var showNewPlaylistDialog by remember { mutableStateOf(false) }
        var newPlaylistName by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { playerVm.onDismissAddToPlaylistDialog() },
            title = { Text("Seleccionar Playlist") },
            text = {
                if (playlists.isEmpty()) {
                    Text("No tienes playlists. Crea una nueva.")
                } else {
                    LazyColumn {
                        items(playlists) { playlist ->
                            TextButton(onClick = { playerVm.addToPlaylist(playlist, song) }) {
                                Text(playlist.name)
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showNewPlaylistDialog = true }) {
                    Text("Nueva Playlist")
                }
            },
            dismissButton = {
                TextButton(onClick = { playerVm.onDismissAddToPlaylistDialog() }) {
                    Text("Cancelar")
                }
            }
        )

        if (showNewPlaylistDialog) {
            AlertDialog(
                onDismissRequest = { showNewPlaylistDialog = false },
                title = { Text("Nombre de la Playlist") },
                text = {
                    OutlinedTextField(
                        value = newPlaylistName,
                        onValueChange = { newPlaylistName = it },
                        label = { Text("Nombre") }
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        if (newPlaylistName.isNotBlank()) {
                            playerVm.addToNewPlaylist(newPlaylistName, song)
                            showNewPlaylistDialog = false
                        }
                    }) {
                        Text("Crear")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showNewPlaylistDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

// ── El resto de funciones (WilSearchBar, TrendingPreview, etc.) se quedan igual ──
// (Mantenlas sin cambios)
