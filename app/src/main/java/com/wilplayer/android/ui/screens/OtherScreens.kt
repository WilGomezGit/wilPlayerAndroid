package com.wilplayer.android.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wilplayer.android.domain.model.Playlist
import com.wilplayer.android.domain.model.Song
import com.wilplayer.android.ui.PlaylistDetailViewModel
import com.wilplayer.android.ui.PlayerViewModel
import com.wilplayer.android.ui.components.*
import com.wilplayer.android.ui.theme.*

// ══════════════════════════════════════════
//   PLAYLIST DETAIL (matching design)
// ══════════════════════════════════════════

@Composable
fun PlaylistDetailScreen(
    playlistId: String,
    playerVm: PlayerViewModel,
    onNavigateToPlayer: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    vm: PlaylistDetailViewModel = hiltViewModel(),
) {
    LaunchedEffect(playlistId) { vm.loadPlaylist(playlistId) }
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    val playerState by playerVm.playerState.collectAsStateWithLifecycle()
    val playlist = uiState.playlist

    var isShuffleActive by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Bg0),
    ) {
        // ── Hero header ────────────────────────────────────────────────────────
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {
                // Background gradient
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(listOf(Color(0xFF2D1B69), Color(0xFF1E3A8A)))
                        )
                )
                // Fade to background
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                0.4f to Color.Transparent,
                                1.0f to Bg0,
                            )
                        )
                )

                // Back button
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Icon(ArrowBackIcon, contentDescription = "Atrás", tint = Color.White)
                }

                // Centered art
                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 24.dp)
                ) {
                    CoverArt(
                        song = null,
                        size = 100.dp,
                        radius = 14.dp,
                        paletteIndex = 0,
                        modifier = Modifier.shadow(elevation = 16.dp, shape = RoundedCornerShape(14.dp))
                    )
                }

                // Info
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        playlist?.name ?: "Playlist",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Text(
                        "${playlist?.songCount ?: 0} canciones · ${formatTotalDuration(playlist?.totalDuration ?: 0L)}",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }
            }
        }

        // ── Action bar ─────────────────────────────────────────────────────────
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Icon(HeartBorderIcon, contentDescription = "Me gusta", tint = TextTertiary, modifier = Modifier.size(22.dp))
                    Icon(ShareIcon, contentDescription = "Compartir", tint = TextTertiary, modifier = Modifier.size(22.dp))
                    Icon(MoreVertIcon, contentDescription = "Más", tint = TextTertiary, modifier = Modifier.size(22.dp))
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Shuffle button
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(18.dp))
                            .background(Surface2)
                            .border(1.dp, Border2, RoundedCornerShape(18.dp))
                            .clickable {
                                isShuffleActive = !isShuffleActive
                                playlist?.songs?.let { songs ->
                                    playerVm.playSong(songs.first(), songs)
                                    if (isShuffleActive) playerVm.toggleShuffle()
                                }
                                onNavigateToPlayer()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            ShuffleIcon,
                            contentDescription = "Aleatorio",
                            tint = if (isShuffleActive) AccentPurple else TextSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    // Play button
                    PlayPauseButton(
                        isPlaying = playerState.isPlaying && playlist?.songs?.any { it.id == playerState.currentSong?.id } == true,
                        onClick = {
                            playlist?.songs?.firstOrNull()?.let { first ->
                                playerVm.playSong(first, playlist.songs)
                                onNavigateToPlayer()
                            }
                        },
                        size = 52.dp
                    )
                }
            }
        }

        // ── Song list ──────────────────────────────────────────────────────────
        itemsIndexed(playlist?.songs ?: emptyList()) { idx, song ->
            val isCurrentlyPlaying = playerState.currentSong?.id == song.id
            SongListItem(
                song = song,
                isPlaying = isCurrentlyPlaying,
                paletteIndex = idx,
                onClick = {
                    playerVm.playSong(song, playlist?.songs ?: listOf(song))
                    onNavigateToPlayer()
                }
            )
        }

        item { Spacer(Modifier.height(8.dp)) }
    }
}

private fun formatTotalDuration(ms: Long): String {
    val totalMin = ms / 60000
    return "$totalMin min"
}

// ══════════════════════════════════════════
//   PROFILE SCREEN
// ══════════════════════════════════════════

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Bg0)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))

        // Avatar
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(40.dp))
                .background(GradientBtn),
            contentAlignment = Alignment.Center
        ) {
            Text("W", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
        }

        Spacer(Modifier.height(16.dp))
        GradientText("WilPlayer", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(Modifier.height(4.dp))
        Text("Conectado con YouTube", fontSize = 13.sp, color = TextSecondary)

        Spacer(Modifier.height(32.dp))

        // Stats row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            StatItem("Canciones", "0")
            StatItem("Playlists", "0")
            StatItem("Artistas", "0")
        }

        Spacer(Modifier.height(32.dp))

        // Settings items
        listOf(
            "🔑" to "API Key de YouTube",
            "🎨" to "Tema",
            "🔀" to "Calidad de Smart Shuffle",
            "📥" to "Descargas",
            "ℹ️" to "Acerca de WilPlayer",
        ).forEach { (emoji, label) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Surface0)
                    .clickable {}
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(emoji, fontSize = 18.sp)
                Text(label, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = TextPrimary, modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = null,
                    tint = TextTertiary,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        GradientText(value, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
        Text(label, fontSize = 12.sp, color = TextSecondary)
    }
}
