package com.wilplayer.android.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wilplayer.android.domain.model.*
import com.wilplayer.android.ui.PlayerViewModel
import com.wilplayer.android.ui.components.*
import com.wilplayer.android.ui.theme.*
import android.media.AudioManager
import android.content.Intent
import android.database.ContentObserver
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch

enum class PlayerTab { COVER, LYRICS, RELATED }

@Composable
fun PlayerScreen(
    playerVm: PlayerViewModel,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val playerState by playerVm.playerState.collectAsStateWithLifecycle()
    val queue by playerVm.queue.collectAsStateWithLifecycle()
    var activeTab by remember { mutableStateOf(PlayerTab.COVER) }
    var showQueue by remember { mutableStateOf(false) }
    var showOptions by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    val song = playerState.currentSong

    // Show error messages (stream extraction failures, etc.) as a Snackbar
    LaunchedEffect(playerState.errorMessage) {
        playerState.errorMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            playerVm.clearError()
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = Surface2,
                    contentColor = TextPrimary,
                )
            }
        }
    ) { _ ->
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    0f to Color(0xFF2D1B69),
                    0.55f to Bg0,
                )
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // ── Drag handle ───────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(36.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color.White.copy(alpha = 0.2f))
                )
            }

            // ── Header ────────────────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onDismiss) {
                    Icon(ArrowDownIcon, contentDescription = "Cerrar", tint = TextSecondary, modifier = Modifier.size(22.dp))
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Reproduciendo ahora",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextSecondary,
                        letterSpacing = 1.sp
                    )
                    Text(
                        song?.artist ?: "—",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                }
                IconButton(onClick = { if (song != null) showOptions = true }) {
                    Icon(MoreVertIcon, contentDescription = "Opciones", tint = TextSecondary, modifier = Modifier.size(22.dp))
                }
            }

            // ── Tab bar (only shown in lyrics/related mode) ───────────────────
            if (activeTab != PlayerTab.COVER) {
                PlayerTabBar(activeTab = activeTab, onTabSelect = { activeTab = it })
            }

            when (activeTab) {
                PlayerTab.COVER -> ClassicPlayerContent(
                    song = song,
                    playerState = playerState,
                    onPlayPause = playerVm::playPause,
                    onSkipPrev = playerVm::skipToPrevious,
                    onSkipNext = playerVm::skipToNext,
                    onSeek = playerVm::seekTo,
                    onToggleLike = { song?.let { playerVm.toggleLike(it) } },
                    onToggleShuffle = playerVm::toggleShuffle,
                    onToggleRepeat = playerVm::toggleRepeat,
                    onShowLyrics = { activeTab = PlayerTab.LYRICS },
                    onShowQueue = { showQueue = true },
                    onShare = {
                        song?.let {
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, "Escuchando ${it.title} en WilPlayer: ${it.youtubeUrl}")
                            }
                            context.startActivity(Intent.createChooser(intent, "Compartir canción"))
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                PlayerTab.LYRICS -> LyricsContent(
                    song = song,
                    playerState = playerState,
                    onPlayPause = playerVm::playPause,
                    onSkipPrev = playerVm::skipToPrevious,
                    onSkipNext = playerVm::skipToNext,
                    onSeek = playerVm::seekTo,
                    onToggleLike = { song?.let { playerVm.toggleLike(it) } },
                    modifier = Modifier.weight(1f)
                )
                PlayerTab.RELATED -> Text("Related", color = TextPrimary) // placeholder
            }
        }

        // ── Queue Bottom Sheet ────────────────────────────────────────────────
        if (showQueue) {
            ModalBottomSheet(
                onDismissRequest = { showQueue = false },
                containerColor = Surface2,
                dragHandle = { BottomSheetDefaults.DragHandle(color = TextTertiary) }
            ) {
                Column(modifier = Modifier.fillMaxHeight(0.7f).padding(bottom = 16.dp)) {
                    Text("Cola de reproducción", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary, modifier = Modifier.padding(16.dp))
                    LazyColumn {
                        itemsIndexed(queue) { idx, songItem ->
                            val isCurrent = playerState.currentSong?.id == songItem.id
                            SongListItem(
                                song = songItem,
                                isPlaying = isCurrent,
                                paletteIndex = idx,
                                onClick = { playerVm.playSong(songItem, queue) },
                                onMoreClick = {
                                    showQueue = false
                                    showOptions = true
                                }
                            )
                        }
                    }
                }
            }
        }

        // ── Song options sheet for current song ───────────────────────────────
        if (showOptions && song != null) {
            SongOptionsSheet(
                song = song,
                onDismiss = { showOptions = false },
                onToggleLike = { playerVm.toggleLike(song) },
                onAddToQueue = { /* already in queue */ },
            )
        }
    }
    } // end Scaffold content
}

// ── Classic Player Content ────────────────────────────────────────────────────

@Composable
private fun ClassicPlayerContent(
    song: Song?,
    playerState: PlayerState,
    onPlayPause: () -> Unit,
    onSkipPrev: () -> Unit,
    onSkipNext: () -> Unit,
    onSeek: (Float) -> Unit,
    onToggleLike: () -> Unit,
    onToggleShuffle: () -> Unit,
    onToggleRepeat: () -> Unit,
    onShowLyrics: () -> Unit,
    onShowQueue: () -> Unit,
    onShare: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        // ── Album art (large) ─────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            CoverArt(
                song = song,
                size = 260.dp,
                radius = 20.dp,
                paletteIndex = 0,
                modifier = Modifier
                    .shadow(
                        elevation = 32.dp,
                        shape = RoundedCornerShape(20.dp),
                        ambientColor = AccentPurple.copy(alpha = 0.35f),
                        spotColor = AccentPurple.copy(alpha = 0.35f),
                    )
            )
        }

        // ── Song info + like ──────────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    song?.title ?: "—",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    song?.artist ?: "—",
                    fontSize = 15.sp,
                    color = TextSecondary
                )
            }
            IconButton(onClick = onToggleLike) {
                Icon(
                    imageVector = if (playerState.currentSong?.isLiked == true) HeartIcon else HeartBorderIcon,
                    contentDescription = "Me gusta",
                    tint = if (playerState.currentSong?.isLiked == true) AccentPurple else TextTertiary,
                    modifier = Modifier.size(26.dp)
                )
            }
        }

        // ── Progress ──────────────────────────────────────────────────────────
        WilProgressSlider(
            progress = playerState.progressFraction,
            onSeek = onSeek,
            elapsedLabel = formatMs(playerState.progress),
            totalLabel = formatMs(playerState.duration),
            modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 8.dp)
        )

        // ── Main controls ─────────────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Shuffle button with mode indicator
            ShuffleButton(mode = playerState.shuffleMode, onClick = onToggleShuffle)

            ControlButton(
                imageVector = SkipPrevIcon,
                contentDescription = "Anterior",
                onClick = onSkipPrev,
                tint = TextPrimary,
                size = 32.dp
            )

            PlayPauseButton(
                isPlaying = playerState.isPlaying,
                onClick = onPlayPause,
                size = 60.dp
            )

            ControlButton(
                imageVector = SkipNextIcon,
                contentDescription = "Siguiente",
                onClick = onSkipNext,
                tint = TextPrimary,
                size = 32.dp
            )

            RepeatButton(mode = playerState.repeatMode, onClick = onToggleRepeat)
        }

        // ── Extra controls ────────────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onShowQueue) {
                Icon(QueueIcon, contentDescription = "Cola", tint = TextTertiary, modifier = Modifier.size(20.dp))
            }

            // Volume slider — synced with system AudioManager via ContentObserver
            val context = LocalContext.current
            val audioManager = remember { context.getSystemService(android.content.Context.AUDIO_SERVICE) as AudioManager }
            val maxVol = remember { audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC).toFloat() }
            var currentVol by remember {
                mutableFloatStateOf(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC).toFloat())
            }

            // Keep slider in sync when physical buttons change volume
            DisposableEffect(Unit) {
                val observer = object : android.database.ContentObserver(android.os.Handler(android.os.Looper.getMainLooper())) {
                    override fun onChange(selfChange: Boolean) {
                        currentVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC).toFloat()
                    }
                }
                context.contentResolver.registerContentObserver(
                    android.provider.Settings.System.CONTENT_URI, true, observer
                )
                onDispose { context.contentResolver.unregisterContentObserver(observer) }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.width(140.dp)
            ) {
                Icon(VolumeIcon, contentDescription = null, tint = TextTertiary, modifier = Modifier.size(16.dp))
                Slider(
                    value = currentVol,
                    onValueChange = { newVol ->
                        currentVol = newVol
                        audioManager.setStreamVolume(
                            AudioManager.STREAM_MUSIC,
                            newVol.toInt(),
                            0
                        )
                    },
                    valueRange = 0f..maxVol,
                    modifier = Modifier.weight(1f).height(20.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = TextSecondary,
                        activeTrackColor = TextSecondary,
                        inactiveTrackColor = Surface3
                    )
                )
            }
            IconButton(onClick = onShare) {
                Icon(ShareIcon, contentDescription = "Compartir", tint = TextTertiary, modifier = Modifier.size(20.dp))
            }
        }
    }
}

// ── Lyrics Content ────────────────────────────────────────────────────────────

@Composable
private fun LyricsContent(
    song: Song?,
    playerState: PlayerState,
    onPlayPause: () -> Unit,
    onSkipPrev: () -> Unit,
    onSkipNext: () -> Unit,
    onSeek: (Float) -> Unit,
    onToggleLike: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Placeholder lyrics (in real app, fetch from lyrics API)
    val lyrics = listOf(
        "Drifting through the midnight air",
        "Stars are painting what we share",
        "Every note a memory",
        "Every beat sets us free",
        "Neon lights they guide the way",
        "In this city where we stay",
        "Close your eyes and feel the sound",
        "Let your spirit touch the ground",
        "Midnight drift, we fade away",
        "Into colors, into the grey",
    )

    val currentLineIdx = (playerState.progress / 4000L).toInt().coerceIn(0, lyrics.lastIndex)

    Column(modifier = modifier.fillMaxSize()) {
        // Mini info bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.2f))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CoverArt(song = song, size = 48.dp, radius = 10.dp, paletteIndex = 0)
            Column(modifier = Modifier.weight(1f)) {
                Text(song?.title ?: "—", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text(song?.artist ?: "—", fontSize = 12.sp, color = TextSecondary)
            }
            IconButton(onClick = onToggleLike) {
                Icon(
                    imageVector = if (playerState.currentSong?.isLiked == true) HeartIcon else HeartBorderIcon,
                    contentDescription = null,
                    tint = if (playerState.currentSong?.isLiked == true) AccentPurple else TextTertiary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // Scrollable lyrics
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            lyrics.forEachIndexed { idx, line ->
                val isActive = idx == currentLineIdx
                Text(
                    text = line,
                    fontSize = if (isActive) 20.sp else 16.sp,
                    fontWeight = if (isActive) FontWeight.Bold else FontWeight.Medium,
                    style = if (isActive) LocalTextStyle.current.copy(brush = GradientBtn)
                    else LocalTextStyle.current,
                    color = if (isActive) Color.Unspecified else Color.White.copy(alpha = 0.3f),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }

        // Mini controls at bottom
        Column(modifier = Modifier.background(Color.Black.copy(alpha = 0.3f)).padding(horizontal = 16.dp, vertical = 8.dp)) {
            WilProgressSlider(
                progress = playerState.progressFraction,
                onSeek = onSeek,
                elapsedLabel = formatMs(playerState.progress),
                totalLabel = formatMs(playerState.duration),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(ShuffleIcon, null, tint = TextTertiary, modifier = Modifier.size(20.dp))
                ControlButton(SkipPrevIcon, "Anterior", onSkipPrev, tint = TextPrimary, size = 28.dp)
                PlayPauseButton(isPlaying = playerState.isPlaying, onClick = onPlayPause, size = 52.dp)
                ControlButton(SkipNextIcon, "Siguiente", onSkipNext, tint = TextPrimary, size = 28.dp)
                Icon(RepeatIcon, null, tint = TextTertiary, modifier = Modifier.size(20.dp))
            }
        }
    }
}

// ── Tab Bar ───────────────────────────────────────────────────────────────────

@Composable
private fun PlayerTabBar(
    activeTab: PlayerTab,
    onTabSelect: (PlayerTab) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .padding(bottom = 12.dp)
            .border(
                width = 1.dp,
                color = Border1,
                shape = RoundedCornerShape(0.dp)
            ),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        val tabs = listOf(PlayerTab.COVER to "Portada", PlayerTab.LYRICS to "Letra", PlayerTab.RELATED to "Relacionado")
        tabs.forEach { (tab, label) ->
            val isActive = tab == activeTab
            Column(
                modifier = Modifier.clickable { onTabSelect(tab) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    label,
                    fontSize = 13.sp,
                    fontWeight = if (isActive) FontWeight.Bold else FontWeight.Medium,
                    color = if (isActive) TextPrimary else TextTertiary
                )
                Spacer(Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .width(if (isActive) 24.dp else 0.dp)
                        .clip(RoundedCornerShape(1.dp))
                        .background(if (isActive) AccentPurple else Color.Transparent)
                )
            }
        }
    }
}

// ── Shuffle & Repeat buttons ──────────────────────────────────────────────────

@Composable
private fun ShuffleButton(mode: ShuffleMode, onClick: () -> Unit) {
    Box(contentAlignment = Alignment.TopEnd) {
        IconButton(onClick = onClick, modifier = Modifier.size(40.dp)) {
            Icon(
                ShuffleIcon,
                contentDescription = "Aleatorio",
                tint = if (mode != ShuffleMode.OFF) AccentPurple else TextTertiary,
                modifier = Modifier.size(22.dp)
            )
        }
        if (mode == ShuffleMode.SMART) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(AccentPurple)
                    .offset(x = 2.dp, y = (-2).dp)
            )
        }
    }
}

@Composable
private fun RepeatButton(mode: RepeatMode, onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = Modifier.size(40.dp)) {
        Icon(
            imageVector = if (mode == RepeatMode.REPEAT_ONE) RepeatOneIcon else RepeatIcon,
            contentDescription = "Repetir",
            tint = if (mode != RepeatMode.OFF) AccentPurple else TextTertiary,
            modifier = Modifier.size(22.dp)
        )
    }
}

// ── Helpers ───────────────────────────────────────────────────────────────────

private fun formatMs(ms: Long): String {
    val totalSec = (ms / 1000).coerceAtLeast(0)
    val m = totalSec / 60
    val s = totalSec % 60
    return "%d:%02d".format(m, s)
}
