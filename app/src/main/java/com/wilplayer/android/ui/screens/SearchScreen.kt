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

// ── Search Bar ─────────────────────────────────────────────────────────────────

@Composable
private fun WilSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = {
            Text("Artistas, canciones, podcasts", color = TextTertiary, fontSize = 14.sp)
        },
        leadingIcon = {
            Icon(SearchIcon, contentDescription = null, tint = TextTertiary, modifier = Modifier.size(18.dp))
        },
        trailingIcon = if (query.isNotBlank()) {
            {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Limpiar",
                        tint = TextTertiary
                    )
                }
            }
        } else null,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Surface0,
            unfocusedContainerColor = Surface0,
            focusedBorderColor = Border2,
            unfocusedBorderColor = Border1,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary,
            cursorColor = AccentPurple,
        ),
        modifier = modifier
            .focusRequester(focusRequester)
            .height(52.dp)
    )
}

// ── Trending Preview ───────────────────────────────────────────────────────────

@Composable
private fun TrendingPreview(
    songs: List<Song>,
    isLoading: Boolean,
    onSongClick: (Song) -> Unit,
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
        Text(
            "Tendencias ahora",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        if (isLoading) {
            repeat(3) {
                ShimmerBox(
                    modifier = Modifier.fillMaxWidth().height(60.dp).padding(bottom = 1.dp),
                    shape = RoundedCornerShape(8.dp)
                )
            }
        } else {
            songs.take(3).forEachIndexed { idx, song ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSongClick(song) }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        "${idx + 1}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextTertiary,
                        modifier = Modifier.width(18.dp)
                    )
                    CoverArt(song = song, size = 44.dp, radius = 8.dp, paletteIndex = idx)
                    Column(modifier = Modifier.weight(1f)) {
                        Text(song.title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(song.artist, fontSize = 11.sp, color = TextSecondary, maxLines = 1)
                    }
                    Icon(MoreVertIcon, contentDescription = null, tint = TextTertiary, modifier = Modifier.size(18.dp))
                }
                if (idx < 2) WilDivider()
            }
        }
    }
}

// ── Genre Grid ─────────────────────────────────────────────────────────────────

@Composable
private fun GenreGrid(onGenreClick: (String) -> Unit) {
    val genres = listOf(
        Triple("Rock en Español", 1, "#374151"),
        Triple("Heavy Metal",     0, "#1f2937"),
        Triple("Metal",           5, "#374151"),
        Triple("Rock",            2, "#4B5563"),
        Triple("Clásicos Rock",   3, "#6366f1"),
        Triple("Electrónica",     3, "#06b6d4"),
        Triple("Hip-Hop",         4, "#f59e0b"),
        Triple("R&B",             5, "#10b981"),
        Triple("Baladas",         6, "#ec4899"),
        Triple("Indie",           7, "#6366f1"),
    )

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            "Explorar géneros",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        genres.chunked(2).forEach { rowItems ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                rowItems.forEach { (genre, paletteIdx, _) ->
                    val palette = COVER_PALETTES[paletteIdx % COVER_PALETTES.size]
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(72.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Brush.linearGradient(listOf(palette.colorA, palette.colorB)))
                            .clickable { onGenreClick(genre) }
                    ) {
                        Text(
                            palette.emoji,
                            fontSize = 42.sp,
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(end = 4.dp, bottom = 2.dp)
                                .offset(x = 8.dp, y = 8.dp)
                        )
                        Text(
                            genre,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
                if (rowItems.size == 1) Spacer(Modifier.weight(1f))
            }
        }
    }
}

// ── Search Results ─────────────────────────────────────────────────────────────

@Composable
private fun SearchResults(
    songs: List<Song>,
    isLoading: Boolean,
    error: String?,
    onSongClick: (Song) -> Unit,
    onMoreClick: (Song) -> Unit,
    onRetry: () -> Unit,
) {
    when {
        isLoading -> LazyColumn {
            items(8) { idx ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ShimmerBox(modifier = Modifier.size(44.dp), shape = RoundedCornerShape(8.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        ShimmerBox(modifier = Modifier.width(160.dp).height(12.dp))
                        ShimmerBox(modifier = Modifier.width(100.dp).height(10.dp))
                    }
                }
            }
        }
        error != null -> ErrorState(message = error, onRetry = onRetry)
        songs.isEmpty() -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("🎵", fontSize = 48.sp)
                Spacer(Modifier.height(16.dp))
                Text("Sin resultados", fontSize = 16.sp, color = TextSecondary, fontWeight = FontWeight.SemiBold)
            }
        }
        else -> LazyColumn {
            itemsIndexed(songs) { idx, song ->
                SongListItem(
                    song = song,
                    paletteIndex = idx,
                    onClick = { onSongClick(song) },
                    onMoreClick = { onMoreClick(song) }
                )
                if (idx < songs.lastIndex) WilDivider(modifier = Modifier.padding(start = 72.dp))
            }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
