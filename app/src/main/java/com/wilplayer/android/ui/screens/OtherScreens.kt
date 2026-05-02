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
import com.wilplayer.android.ui.ProfileViewModel
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
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    IconButton(onClick = { /* Toggle library like */ }) {
                        Icon(HeartBorderIcon, contentDescription = "Me gusta", tint = TextTertiary, modifier = Modifier.size(22.dp))
                    }
                    IconButton(onClick = { /* Share playlist */ }) {
                        Icon(ShareIcon, contentDescription = "Compartir", tint = TextTertiary, modifier = Modifier.size(22.dp))
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(18.dp))
                            .background(Surface2)
                            .border(1.dp, Border2, RoundedCornerShape(18.dp))
                            .clickable {
                                playlist?.songs?.let { songs ->
                                    playerVm.playSong(songs.random(), songs)
                                    playerVm.toggleShuffle()
                                    onNavigateToPlayer()
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(ShuffleIcon, contentDescription = "Aleatorio", tint = if (playerState.shuffleMode != com.wilplayer.android.domain.model.ShuffleMode.OFF) AccentPurple else TextSecondary, modifier = Modifier.size(18.dp))
                    }

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
                }
            )
        }
        item { Spacer(Modifier.height(80.dp)) }
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
            StatItem("Cache", "12MB")
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

    // Settings Dialogs
    when (selectedSetting) {
        "API Key de YouTube" -> ApiKeyDialog(uiState, vm) { selectedSetting = null }
        "Calidad de Smart Shuffle" -> ShuffleQualityDialog(uiState, vm) { selectedSetting = null }
        "Descargas y Caché" -> CacheDialog { selectedSetting = null }
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
                Text("Obtén una clave en Google Cloud Console para habilitar búsquedas y tendencias.", fontSize = 12.sp, color = TextSecondary)
                OutlinedTextField(
                    value = key,
                    onValueChange = { key = it },
                    placeholder = { Text("AIza...", color = TextTertiary) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = TextPrimary, unfocusedTextColor = TextPrimary)
                )
                if (state.isCheckingKey == true) CircularProgressIndicator(modifier = Modifier.size(24.dp))
                else if (state.isApiKeyValid == true) Text("✅ Conexión exitosa", color = Color(0xFF10B981), fontSize = 12.sp)
                else if (state.isApiKeyValid == false) Text("❌ Clave inválida o sin cuota", color = Color(0xFFEF4444), fontSize = 12.sp)
            }
        },
        confirmButton = {
            Row {
                TextButton(onClick = { vm.testApiKey() }) { Text("Probar") }
                TextButton(onClick = { vm.saveApiKey(key); onDismiss() }) { Text("Guardar") }
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}

@Composable
private fun ShuffleQualityDialog(state: ProfileUiState, vm: ProfileViewModel, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Surface2,
        title = { Text("Calidad de Smart Shuffle") },
        text = {
            Column {
                listOf("HIGH" to "Alta (Análisis profundo)", "MEDIUM" to "Media (Equilibrado)", "LOW" to "Baja (Aleatorio)").forEach { (valStr, label) ->
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().clickable { vm.saveShuffleQuality(valStr) }) {
                        RadioButton(selected = state.shuffleQuality == valStr, onClick = { vm.saveShuffleQuality(valStr) })
                        Text(label, color = TextPrimary)
                    }
                }
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text("Cerrar") } }
    )
}

@Composable
private fun CacheDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Surface2,
        title = { Text("Descargas y Caché") },
        text = { Text("Caché actual: 12.4 MB\nLos streams se guardan temporalmente para ahorrar datos.") },
        confirmButton = {
            TextButton(onClick = { /* Clean cache */ onDismiss() }) { Text("Limpiar Caché") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Aceptar") } }
    )
}

@Composable
private fun AboutDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Surface2,
        title = { Text("Acerca de WilPlayer") },
        text = {
            Column {
                Text("Versión 1.0.0-PRO", fontWeight = FontWeight.Bold, color = AccentPurple)
                Spacer(Modifier.height(8.dp))
                Text("Desarrollado por Wil Gomez\nUn reproductor premium impulsado por NewPipe Extractor y Media3.", fontSize = 13.sp, color = TextSecondary)
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text("Aceptar") } }
    )
}

@Composable
private fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        GradientText(value, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
        Text(label, fontSize = 12.sp, color = TextSecondary)
    }
}
