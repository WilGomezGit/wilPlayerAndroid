package com.wilplayer.android.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wilplayer.android.domain.model.Playlist
import com.wilplayer.android.domain.model.Song
import com.wilplayer.android.ui.LibraryViewModel
import com.wilplayer.android.ui.PlayerViewModel
import com.wilplayer.android.ui.components.*
import com.wilplayer.android.ui.theme.*

enum class LibraryTab(val label: String) {
    PLAYLISTS("Playlists"),
    LIKED("Me Gusta"),
    ARTISTS("Artistas"),
}

@Composable
fun LibraryScreen(
    playerVm: PlayerViewModel,
    onNavigateToPlaylist: (String) -> Unit,
    onNavigateToPlayer: () -> Unit,
    modifier: Modifier = Modifier,
    vm: LibraryViewModel = hiltViewModel(),
) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    var activeTab by remember { mutableStateOf(LibraryTab.PLAYLISTS) }
    var showCreateDialog by remember { mutableStateOf(false) }
    var showImportDialog by remember { mutableStateOf(false) }
    var optionsSong by remember { mutableStateOf<Song?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Bg0)
    ) {
        // ── Header ─────────────────────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Tu Biblioteca", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                // Import YouTube playlist button
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFEF4444).copy(alpha = 0.15f))
                        .clickable { showImportDialog = true },
                    contentAlignment = Alignment.Center
                ) {
                    Text("▶", fontSize = 14.sp, color = Color(0xFFEF4444))
                }
                // Create new playlist button
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(GradientBtn)
                        .clickable { showCreateDialog = true },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(AddIcon, contentDescription = "Nueva playlist", tint = Color.White, modifier = Modifier.size(18.dp))
                }
            }
        }

        // ── Tab row ────────────────────────────────────────────────────────────
        Row(
            modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LibraryTab.values().forEach { tab ->
                val isActive = tab == activeTab
                FilterChip(
                    selected = isActive,
                    onClick = { activeTab = tab },
                    label = { Text(tab.label, fontSize = 12.sp, fontWeight = FontWeight.SemiBold) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = AccentPurple.copy(alpha = 0.15f),
                        selectedLabelColor = AccentPurple,
                        labelColor = TextSecondary,
                        containerColor = Color.Transparent,
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        enabled = true,
                        selected = isActive,
                        selectedBorderColor = AccentPurple,
                        borderColor = Border2,
                        borderWidth = 1.dp,
                        selectedBorderWidth = 1.dp,
                    )
                )
            }
        }

        // ── Sort indicator ─────────────────────────────────────────────────────
        if (activeTab == LibraryTab.PLAYLISTS) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(FilterIcon, contentDescription = null, tint = TextTertiary, modifier = Modifier.size(16.dp))
                Text("Recientes", fontSize = 12.sp, color = TextTertiary)
            }
        }

        // ── Content ────────────────────────────────────────────────────────────
        when (activeTab) {
            LibraryTab.PLAYLISTS -> PlaylistList(
                playlists = uiState.playlists,
                isLoading = uiState.isLoading,
                onPlaylistClick = onNavigateToPlaylist
            )
            LibraryTab.LIKED -> LikedSongsList(
                songs = uiState.likedSongs,
                isLoading = uiState.isLoading,
                onSongClick = { song ->
                    playerVm.playSong(song, uiState.likedSongs)
                    onNavigateToPlayer()
                },
                onMoreClick = { song -> optionsSong = song }
            )
            LibraryTab.ARTISTS -> ArtistList(
                artists = uiState.artists,
                onArtistClick = { artist -> vm.selectArtist(artist) }
            )
        }
    }

    // ── Import YouTube Playlist dialog ─────────────────────────────────────────
    if (showImportDialog) {
        ImportYouTubeDialog(
            isImporting = uiState.isImporting,
            error = uiState.importError,
            onConfirm = { url ->
                vm.importYouTubePlaylist(url)
                showImportDialog = false
            },
            onDismiss = {
                showImportDialog = false
                vm.clearImportError()
            }
        )
    }

    // ── Create playlist dialog ─────────────────────────────────────────────────
    if (showCreateDialog) {
        CreatePlaylistDialog(
            onConfirm = { name ->
                vm.createPlaylist(name)
                showCreateDialog = false
            },
            onDismiss = { showCreateDialog = false }
        )
    }

    // ── Artist songs bottom sheet ──────────────────────────────────────────────
    val selectedArtist = uiState.selectedArtist
    if (selectedArtist != null) {
        ArtistSongsSheet(
            artist = selectedArtist,
            songs = uiState.artistSongs,
            onDismiss = { vm.clearSelectedArtist() },
            onSongClick = { song ->
                playerVm.playSong(song, uiState.artistSongs)
                vm.clearSelectedArtist()
                onNavigateToPlayer()
            },
            onMoreClick = { song -> optionsSong = song }
        )
    }

    // ── Song options sheet ─────────────────────────────────────────────────────
    optionsSong?.let { song ->
        SongOptionsSheet(
            song = song,
            onDismiss = { optionsSong = null },
            onToggleLike = { playerVm.toggleLike(song) },
            onAddToQueue = { playerVm.addToQueue(song) },
        )
    }
}

@Composable
private fun PlaylistList(
    playlists: List<Playlist>,
    isLoading: Boolean,
    onPlaylistClick: (String) -> Unit,
) {
    LazyColumn {
        if (isLoading) {
            items(5) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ShimmerBox(modifier = Modifier.size(52.dp), shape = RoundedCornerShape(10.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        ShimmerBox(modifier = Modifier.width(150.dp).height(13.dp))
                        ShimmerBox(modifier = Modifier.width(100.dp).height(11.dp))
                    }
                }
            }
        } else if (playlists.isEmpty()) {
            item { EmptyState("Sin playlists aún.\nCrea una con el botón +") }
        } else {
            itemsIndexed(playlists) { idx, playlist ->
                PlaylistItem(
                    playlist = playlist,
                    paletteIndex = idx,
                    onClick = { onPlaylistClick(playlist.id) }
                )
            }
        }
        item { Spacer(Modifier.height(16.dp)) }
    }
}

@Composable
private fun LikedSongsList(
    songs: List<Song>,
    isLoading: Boolean,
    onSongClick: (Song) -> Unit,
    onMoreClick: (Song) -> Unit,
) {
    when {
        isLoading -> LazyColumn {
            items(5) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
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
        songs.isEmpty() -> EmptyState("Sin canciones favoritas.\nPulsa ♡ en cualquier canción.")
        else -> LazyColumn {
            itemsIndexed(songs) { idx, song ->
                SongListItem(
                    song = song,
                    isPlaying = false,
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

@Composable
private fun PlaylistItem(
    playlist: Playlist,
    paletteIndex: Int,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val palette = COVER_PALETTES[paletteIndex % COVER_PALETTES.size]
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Brush.linearGradient(listOf(palette.colorA, palette.colorB))),
            contentAlignment = Alignment.Center
        ) {
            Text(palette.emoji, fontSize = 22.sp)
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(playlist.name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(
                "Playlist · ${playlist.songCount} canciones",
                fontSize = 12.sp,
                color = TextSecondary
            )
        }

        Icon(MoreVertIcon, contentDescription = null, tint = TextTertiary, modifier = Modifier.size(18.dp))
    }
}

@Composable
private fun ArtistList(
    artists: List<String>,
    onArtistClick: (String) -> Unit,
) {
    if (artists.isEmpty()) {
        EmptyState("Sin artistas todavía.\nReproducir canciones los añade aquí.")
        return
    }
    LazyColumn {
        items(artists) { artist ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onArtistClick(artist) }
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(26.dp))
                        .background(Surface2),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(PersonIcon, contentDescription = null, tint = TextTertiary, modifier = Modifier.size(26.dp))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(artist, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                    Text("Artista", fontSize = 12.sp, color = TextSecondary)
                }
                Icon(ChevronRightIcon, contentDescription = null, tint = TextTertiary, modifier = Modifier.size(18.dp))
            }
        }
        item { Spacer(Modifier.height(16.dp)) }
    }
}

@Composable
private fun ArtistSongsSheet(
    artist: String,
    songs: List<Song>,
    onDismiss: () -> Unit,
    onSongClick: (Song) -> Unit,
    onMoreClick: (Song) -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Surface2,
        dragHandle = { BottomSheetDefaults.DragHandle(color = TextTertiary) }
    ) {
        Column(modifier = Modifier.fillMaxHeight(0.75f)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier.size(48.dp).clip(RoundedCornerShape(24.dp)).background(Surface3),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(PersonIcon, contentDescription = null, tint = TextTertiary, modifier = Modifier.size(26.dp))
                }
                Column {
                    Text(artist, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text("${songs.size} canciones", fontSize = 12.sp, color = TextSecondary)
                }
            }
            if (songs.isEmpty()) {
                Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                    Text("Sin canciones guardadas", color = TextSecondary)
                }
            } else {
                LazyColumn(modifier = Modifier.padding(bottom = 24.dp)) {
                    itemsIndexed(songs) { idx, song ->
                        SongListItem(
                            song = song,
                            isPlaying = false,
                            paletteIndex = idx,
                            onClick = { onSongClick(song) },
                            onMoreClick = { onMoreClick(song) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ImportYouTubeDialog(
    isImporting: Boolean,
    error: String?,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    var url by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = { if (!isImporting) onDismiss() },
        containerColor = Surface2,
        title = { Text("Importar Playlist de YouTube", color = TextPrimary, fontWeight = FontWeight.Bold) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "Pega el enlace de tu playlist de YouTube.\nEjemplo: youtube.com/playlist?list=PL...",
                    fontSize = 12.sp, color = TextSecondary
                )
                OutlinedTextField(
                    value = url,
                    onValueChange = { url = it },
                    placeholder = { Text("https://youtube.com/playlist?list=...", color = TextTertiary, fontSize = 12.sp) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Surface3,
                        unfocusedContainerColor = Surface3,
                        focusedBorderColor = AccentPurple,
                        unfocusedBorderColor = Border2,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                    )
                )
                if (isImporting) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        CircularProgressIndicator(modifier = Modifier.size(18.dp), color = AccentPurple, strokeWidth = 2.dp)
                        Text("Importando canciones...", fontSize = 12.sp, color = TextSecondary)
                    }
                }
                if (error != null) {
                    Text("❌ $error", color = Color(0xFFEF4444), fontSize = 12.sp)
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = { if (url.isNotBlank() && !isImporting) onConfirm(url.trim()) },
                enabled = url.isNotBlank() && !isImporting
            ) {
                GradientText("Importar", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = { if (!isImporting) onDismiss() }) {
                Text("Cancelar", color = TextSecondary)
            }
        }
    )
}

@Composable
private fun EmptyState(message: String) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(top = 80.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("🎵", fontSize = 48.sp)
            Text(message, fontSize = 14.sp, color = TextSecondary, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        }
    }
}

@Composable
private fun CreatePlaylistDialog(onConfirm: (String) -> Unit, onDismiss: () -> Unit) {
    var name by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Surface2,
        title = { Text("Nueva Playlist", color = TextPrimary, fontWeight = FontWeight.Bold) },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Nombre de la playlist", color = TextTertiary) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Surface3,
                    unfocusedContainerColor = Surface3,
                    focusedBorderColor = AccentPurple,
                    unfocusedBorderColor = Border2,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                )
            )
        },
        confirmButton = {
            TextButton(onClick = { if (name.isNotBlank()) onConfirm(name.trim()) }) {
                GradientText("Crear", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", color = TextSecondary)
            }
        }
    )
}
