package com.wilplayer.android.ui.screens

import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wilplayer.android.domain.model.Playlist
import com.wilplayer.android.domain.model.Song
import com.wilplayer.android.ui.PlaylistDetailViewModel
import com.wilplayer.android.ui.PlayerViewModel
import com.wilplayer.android.ui.ProfileViewModel
import com.wilplayer.android.ui.ProfileUiState
import com.wilplayer.android.ui.components.*
import com.wilplayer.android.ui.theme.*

// ══════════════════════════════════════════
//   PLAYLIST DETAIL
// ══════════════════════════════════════════

@Composable
fun PlaylistDetailScreen(
    playlistId: String,
    playerVm: PlayerViewModel,
    onNavigateToPlayer: () -> Unit,
    onNavigateToSearch: () -> Unit,  // <-- NUEVO
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    vm: PlaylistDetailViewModel = hiltViewModel(),
) {
    LaunchedEffect(playlistId) { vm.loadPlaylist(playlistId) }
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    val playerState by playerVm.playerState.collectAsStateWithLifecycle()
    val playlist = uiState.playlist
    val context = LocalContext.current
    var optionsSong by remember { mutableStateOf<Song?>(null) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Bg0),
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Brush.linearGradient(listOf(Color(0xFF2D1B69), Color(0xFF1E3A8A))))
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Brush.verticalGradient(0.4f to Color.Transparent, 1.0f to Bg0))
                )

                IconButton(onClick = onBack, modifier = Modifier.padding(12.dp)) {
                    Icon(ArrowBackIcon, contentDescription = "Atrás", tint = Color.White)
                }

                Box(modifier = Modifier.align(Alignment.TopCenter).padding(top = 24.dp)) {
                    val firstSong = playlist?.songs?.firstOrNull()
                    CoverArt(
                        song = firstSong,
                        size = 100.dp,
                        radius = 14.dp,
                        paletteIndex = 0,
                        modifier = Modifier.shadow(elevation = 16.dp, shape = RoundedCornerShape(14.dp))
                    )
                }

                Column(
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(playlist?.name ?: "Playlist", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                    Text(
                        "${playlist?.songCount ?: 0} canciones · ${formatTotalDuration(playlist?.totalDuration ?: 0L)}",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }
            }
        }

        item {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Grupo izquierdo: compartir + añadir canciones
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // Share playlist
            IconButton(onClick = {
                val playlistUrl = playlist?.let {
                    if (it.youtubePlaylistId != null)
                        "https://www.youtube.com/playlist?list=${it.youtubePlaylistId}"
                    else it.name
                } ?: ""
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "Escucha «${playlist?.name}» en WilPlayer: $playlistUrl")
                }
                context.startActivity(Intent.createChooser(intent, "Compartir playlist"))
            }) {
                Icon(
                    ShareIcon,
                    contentDescription = "Compartir",
                    tint = TextTertiary,
                    modifier = Modifier.size(22.dp)
                )
            }

            // Add songs button
            TextButton(onClick = onNavigateToSearch) {
                Icon(
                    imageVector = AddIcon,
                    contentDescription = "Añadir canciones",
                    tint = AccentPurple,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Añadir canciones",
                    color = AccentPurple,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Grupo derecho: shuffle + play
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
                        playlist?.songs?.takeIf { it.isNotEmpty() }?.let { songs ->
                            playerVm.playSong(songs.random(), songs)
                            playerVm.toggleShuffle()
                            onNavigateToPlayer()
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    ShuffleIcon,
                    contentDescription = "Aleatorio",
                    tint = if (playerState.shuffleMode != com.wilplayer.android.domain.model.ShuffleMode.OFF) AccentPurple else TextSecondary,
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

        itemsIndexed(playlist?.songs ?: emptyList()) { idx, song ->
            val isCurrentlyPlaying = playerState.currentSong?.id == song.id
            SongListItem(
                song = song,
                isPlaying = isCurrentlyPlaying,
                paletteIndex = idx,
                onClick = {
                    playerVm.playSong(song, playlist?.songs ?: listOf(song))
                    onNavigateToPlayer()
                },
                onMoreClick = { optionsSong = song }
            )
        }
        item { Spacer(Modifier.height(80.dp)) }
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

private fun formatTotalDuration(ms: Long): String = "${ms / 60000} min"

// ══════════════════════════════════════════
//   PROFILE SCREEN
// ══════════════════════════════════════════

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    vm: ProfileViewModel = hiltViewModel()
) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    var selectedSetting by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier.fillMaxSize().background(Bg0).padding(16.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))
        Box(modifier = Modifier.size(80.dp).clip(RoundedCornerShape(40.dp)).background(GradientBtn), contentAlignment = Alignment.Center) {
            Text("W", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
        }
        Spacer(Modifier.height(16.dp))
        GradientText("WilPlayer Pro", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
        Text("YouTube Music Player", fontSize = 13.sp, color = TextSecondary)

        Spacer(Modifier.height(32.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            StatItem("Canciones", uiState.songCount.toString())
            StatItem("Playlists", uiState.playlistCount.toString())
            StatItem("Calidad", uiState.shuffleQuality)
        }

        Spacer(Modifier.height(32.dp))
        val settings = listOf(
            "🔑" to "API Key de YouTube",
            "🔀" to "Calidad de Smart Shuffle",
            "📥" to "Descargas y Caché",
            "ℹ️" to "Acerca de WilPlayer"
        )

        settings.forEach { (emoji, label) ->
            SettingsItem(emoji, label) { selectedSetting = label }
            Spacer(Modifier.height(8.dp))
        }

        Spacer(Modifier.height(32.dp))
    }

    when (selectedSetting) {
        "API Key de YouTube" -> ApiKeyDialog(uiState, vm) { selectedSetting = null }
        "Calidad de Smart Shuffle" -> ShuffleQualityDialog(uiState, vm) { selectedSetting = null }
        "Descargas y Caché" -> CacheDialog(vm) { selectedSetting = null }
        "Acerca de WilPlayer" -> AboutDialog { selectedSetting = null }
    }
}

@Composable
private fun SettingsItem(emoji: String, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Surface0).clickable(onClick = onClick).padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(emoji, fontSize = 18.sp)
        Text(label, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = TextPrimary, modifier = Modifier.weight(1f))
        Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = TextTertiary, modifier = Modifier.size(18.dp))
    }
}

@Composable
private fun ApiKeyDialog(state: ProfileUiState, vm: ProfileViewModel, onDismiss: () -> Unit) {
    var key by remember { mutableStateOf(state.apiKey) }
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Surface2,
        title = { Text("API Key de YouTube", color = TextPrimary) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Obtén una clave en Google Cloud Console → YouTube Data API v3.", fontSize = 12.sp, color = TextSecondary)
                OutlinedTextField(
                    value = key,
                    onValueChange = { key = it },
                    placeholder = { Text("AIza...", color = TextTertiary) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        focusedBorderColor = AccentPurple,
                        unfocusedBorderColor = Border2,
                    )
                )
                when {
                    state.isCheckingKey == true ->
                        CircularProgressIndicator(modifier = Modifier.size(24.dp), color = AccentPurple)
                    state.isApiKeyValid == true ->
                        Text("✅ Conexión exitosa", color = Color(0xFF10B981), fontSize = 12.sp)
                    state.isApiKeyValid == false ->
                        Text("❌ Clave inválida o sin cuota", color = Color(0xFFEF4444), fontSize = 12.sp)
                }
            }
        },
        confirmButton = {
            Row {
                TextButton(onClick = { vm.saveApiKey(key.trim()); vm.testApiKey() }) {
                    Text("Guardar y probar", color = AccentPurple)
                }
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar", color = TextSecondary) } }
    )
}

@Composable
private fun ShuffleQualityDialog(state: ProfileUiState, vm: ProfileViewModel, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Surface2,
        title = { Text("Calidad de Smart Shuffle", color = TextPrimary, fontWeight = FontWeight.Bold) },
        text = {
            Column {
                listOf(
                    "HIGH" to "Alta — Análisis profundo del historial",
                    "MEDIUM" to "Media — Equilibrado (recomendado)",
                    "LOW" to "Baja — Más aleatorio"
                ).forEach { (value, label) ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().clickable { vm.saveShuffleQuality(value) }
                    ) {
                        RadioButton(
                            selected = state.shuffleQuality == value,
                            onClick = { vm.saveShuffleQuality(value) },
                            colors = RadioButtonDefaults.colors(selectedColor = AccentPurple)
                        )
                        Text(label, color = TextPrimary, fontSize = 14.sp)
                    }
                }
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text("Cerrar", color = AccentPurple) } }
    )
}

@Composable
private fun CacheDialog(vm: ProfileViewModel, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Surface2,
        title = { Text("Caché de streams", color = TextPrimary, fontWeight = FontWeight.Bold) },
        text = {
            Text(
                "Las URLs de audio se cachean 45 minutos para ahorrar ancho de banda.\n\n" +
                "Limpiar fuerza re-extracción en la próxima reproducción.",
                color = TextSecondary,
                fontSize = 13.sp
            )
        },
        confirmButton = {
            TextButton(onClick = {
                vm.clearStreamCache()
                onDismiss()
            }) {
                Text("Limpiar Caché", color = Color(0xFFEF4444))
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cerrar", color = TextSecondary) } }
    )
}

@Composable
private fun AboutDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Surface2,
        title = { Text("Acerca de WilPlayer", color = TextPrimary, fontWeight = FontWeight.Bold) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                GradientText("Versión 1.0.0", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(
                    "Desarrollado por Wil Gomez\n\n" +
                    "Motor: NewPipe Extractor + Media3 ExoPlayer\n" +
                    "Reproduce audio de YouTube sin publicidad.",
                    fontSize = 13.sp,
                    color = TextSecondary
                )
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text("Aceptar", color = AccentPurple) } }
    )
}

@Composable
private fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        GradientText(value, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
        Text(label, fontSize = 12.sp, color = TextSecondary)
    }
}
