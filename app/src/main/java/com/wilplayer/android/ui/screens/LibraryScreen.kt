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
            LibraryTab.ARTISTS -> ArtistList(artists = uiState.artists)
        }
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
private fun ArtistList(artists: List<String>) {
    if (artists.isEmpty()) {
        EmptyState("Sin artistas todavía.\nReproducir canciones los añade aquí.")
        return
    }
    LazyColumn {
        items(artists) { artist ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp),
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
                Text(artist, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
            }
        }
        item { Spacer(Modifier.height(16.dp)) }
    }
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
