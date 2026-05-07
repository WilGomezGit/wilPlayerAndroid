package com.wilplayer.android.ui.components

import android.content.Intent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.wilplayer.android.domain.model.Song
import com.wilplayer.android.ui.theme.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*

// ── CoverArt ─────────────────────────────────────────────────────────────────

@Composable
fun CoverArt(
    song: Song?,
    size: Dp,
    modifier: Modifier = Modifier,
    radius: Dp = 10.dp,
    paletteIndex: Int = 0,
) {
    val palette = COVER_PALETTES[paletteIndex % COVER_PALETTES.size]
    val shape = RoundedCornerShape(radius)

    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
            .background(
                Brush.linearGradient(listOf(palette.colorA, palette.colorB))
            ),
        contentAlignment = Alignment.Center
    ) {
        if (song != null && song.thumbnailUrl.isNotBlank()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(song.highResThumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = song.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Text(
                text = palette.emoji,
                fontSize = (size.value * 0.35f).sp
            )
        }
    }
}

// ── GradientText ──────────────────────────────────────────────────────────────

@Composable
fun GradientText(
    text: String,
    fontSize: androidx.compose.ui.unit.TextUnit,
    fontWeight: FontWeight = FontWeight.Bold,
    modifier: Modifier = Modifier,
    brush: Brush = GradientBtn,
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        style = LocalTextStyle.current.copy(
            brush = brush
        ),
        modifier = modifier
    )
}

// ── WilGradientButton ─────────────────────────────────────────────────────────

@Composable
fun WilGradientButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(if (enabled) GradientBtn else Brush.horizontalGradient(listOf(Surface2, Surface3)))
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            content = content
        )
    }
}

// ── PlayPauseButton ───────────────────────────────────────────────────────────

@Composable
fun PlayPauseButton(
    isPlaying: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 60.dp,
    iconSize: Dp = 28.dp,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(GradientBtn)
            .shadow(elevation = 12.dp, shape = CircleShape, ambientColor = AccentPurple.copy(alpha = 0.45f))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = if (isPlaying) PauseIcon else PlayIcon,
            contentDescription = if (isPlaying) "Pausar" else "Reproducir",
            tint = Color.White,
            modifier = Modifier.size(iconSize)
        )
    }
}

// ── ControlButton (small icon buttons) ─────────────────────────────────────

@Composable
fun ControlButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = TextSecondary,
    size: Dp = 22.dp,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(40.dp)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(size)
        )
    }
}

// ── ProgressSlider ────────────────────────────────────────────────────────────

@Composable
fun WilProgressSlider(
    progress: Float,            // 0f..1f
    onSeek: (Float) -> Unit,
    modifier: Modifier = Modifier,
    elapsedLabel: String = "0:00",
    totalLabel: String = "0:00",
) {
    Column(modifier = modifier) {
        Slider(
            value = progress.coerceIn(0f, 1f),
            onValueChange = onSeek,
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = AccentPurple,
                inactiveTrackColor = Surface3,
                activeTickColor = Color.Transparent,
                inactiveTickColor = Color.Transparent,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(elapsedLabel, fontSize = 11.sp, color = TextTertiary)
            Text(totalLabel, fontSize = 11.sp, color = TextTertiary)
        }
    }
}

// ── SongListItem ──────────────────────────────────────────────────────────────

@Composable
fun SongListItem(
    song: Song,
    isPlaying: Boolean = false,
    paletteIndex: Int = 0,
    onClick: () -> Unit,
    onMoreClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                if (isPlaying) AccentPurple.copy(alpha = 0.08f) else Color.Transparent
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CoverArt(song = song, size = 44.dp, radius = 8.dp, paletteIndex = paletteIndex)

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = song.title,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (isPlaying) AccentPurple else TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${song.artist} · ${song.durationFormatted}",
                fontSize = 11.sp,
                color = TextSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        IconButton(onClick = onMoreClick, modifier = Modifier.size(36.dp)) {
            Icon(
                imageVector = MoreVertIcon,
                contentDescription = "Más opciones",
                tint = TextTertiary,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

// ── ShimmerBox (loading skeleton) ─────────────────────────────────────────────

@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
) {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val alpha by transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmer_alpha"
    )
    Box(
        modifier = modifier
            .clip(shape)
            .background(Surface3.copy(alpha = alpha))
    )
}

// ── Error State ───────────────────────────────────────────────────────────────

@Composable
fun ErrorState(
    message: String,
    onRetry: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "⚠️", fontSize = 48.sp)
        Text(
            text = message,
            fontSize = 14.sp,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )
        if (onRetry != null) {
            WilGradientButton(onClick = onRetry) {
                Text("Reintentar", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// ── Song Options Bottom Sheet ─────────────────────────────────────────────────

/**
 * Reusable bottom sheet with Like, Add-to-queue, Add-to-playlist, and Share actions.
 */
@Composable
fun SongOptionsSheet(
    song: Song,
    onDismiss: () -> Unit,
    onToggleLike: () -> Unit,
    onAddToQueue: () -> Unit,
    onAddToPlaylist: () -> Unit = {},   // ← nuevo callback
) {
    val context = LocalContext.current
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Surface2,
        dragHandle = { BottomSheetDefaults.DragHandle(color = TextTertiary) },
    ) {
        Column(modifier = Modifier.padding(bottom = 32.dp)) {
            // Song header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CoverArt(song = song, size = 48.dp, radius = 8.dp)
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        song.title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                        color = TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
                    Text(song.artist, fontSize = 12.sp, color = TextSecondary, maxLines = 1)
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = Border1, thickness = 1.dp
            )
            // Like / Unlike
            OptionsRow(
                icon = if (song.isLiked) HeartIcon else HeartBorderIcon,
                label = if (song.isLiked) "Quitar de Me Gusta" else "Me Gusta",
                tint = if (song.isLiked) AccentPurple else TextPrimary,
                onClick = { onToggleLike(); onDismiss() }
            )
            // Add to queue
            OptionsRow(
                icon = QueueIcon,
                label = "Agregar a la cola",
                onClick = { onAddToQueue(); onDismiss() }
            )
            // Add to playlist
            OptionsRow(
                icon = LibraryIcon,
                label = "Agregar a playlist",
                onClick = { onAddToPlaylist(); onDismiss() }
            )
            // Share
            OptionsRow(
                icon = ShareIcon,
                label = "Compartir",
                onClick = {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "Escuchando «${song.title}» en WilPlayer: ${song.youtubeUrl}"
                        )
                    }
                    context.startActivity(Intent.createChooser(intent, "Compartir canción"))
                    onDismiss()
                }
            )
        }
    }
}

@Composable
fun PlaylistSelectionDialog(
    playlists: List<Playlist>,
    onPlaylistSelected: (Playlist) -> Unit,
    onCreateNewPlaylist: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    var showCreateDialog by remember { mutableStateOf(false) }
    var newName by remember { mutableStateOf("") }

    if (showCreateDialog) {
        AlertDialog(
            onDismissRequest = { showCreateDialog = false },
            containerColor = Surface2,
            title = { Text("Nueva Playlist", color = TextPrimary) },
            text = {
                OutlinedTextField(
                    value = newName,
                    onValueChange = { newName = it },
                    placeholder = { Text("Nombre de la lista", color = TextTertiary) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        focusedBorderColor = AccentPurple,
                        unfocusedBorderColor = Border2,
                    )
                )
            },
            confirmButton = {
                TextButton(onClick = { 
                    if (newName.isNotBlank()) {
                        onCreateNewPlaylist(newName)
                        showCreateDialog = false
                    }
                }) {
                    Text("Crear y añadir", color = AccentPurple)
                }
            },
            dismissButton = {
                TextButton(onClick = { showCreateDialog = false }) {
                    Text("Cancelar", color = TextSecondary)
                }
            }
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Surface2,
        title = { Text("Añadir a playlist", color = TextPrimary) },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Option to create new
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showCreateDialog = true }
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier.size(40.dp).clip(RoundedCornerShape(8.dp)).background(Surface3),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(AddIcon, null, tint = AccentPurple)
                    }
                    Text("Crear nueva playlist", color = TextPrimary, fontWeight = FontWeight.Bold)
                }
                
                HorizontalDivider(color = Border1)
                
                // List of existing
                LazyColumn(modifier = Modifier.heightIn(max = 300.dp)) {
                    items(playlists) { playlist ->
                        PlaylistRow(playlist = playlist, onClick = { onPlaylistSelected(playlist) })
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cerrar", color = TextSecondary)
            }
        }
    )
}

@Composable
private fun PlaylistRow(playlist: Playlist, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier.size(40.dp).clip(RoundedCornerShape(8.dp)).background(Surface3),
            contentAlignment = Alignment.Center
        ) {
            Icon(LibraryIcon, null, tint = TextSecondary, modifier = Modifier.size(20.dp))
        }
        Column {
            Text(playlist.name, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Text("${playlist.songCount} canciones", color = TextSecondary, fontSize = 11.sp)
        }
    }
}

@Composable
private fun OptionsRow(
    icon: ImageVector,
    label: String,
    tint: Color = TextPrimary,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(22.dp))
        Text(label, fontSize = 15.sp, color = TextPrimary)
    }
}

// ── Icon aliases (centralized) ─────────────────────────────────────────────────

val PlayIcon      get() = Icons.Filled.PlayArrow
val PauseIcon     get() = Icons.Filled.Pause
val SkipNextIcon  get() = Icons.Filled.SkipNext
val SkipPrevIcon  get() = Icons.Filled.SkipPrevious
val ShuffleIcon   get() = Icons.Filled.Shuffle
val RepeatIcon    get() = Icons.Filled.Repeat
val RepeatOneIcon get() = Icons.Filled.RepeatOne
val MoreVertIcon  get() = Icons.Filled.MoreVert
val HeartIcon     get() = Icons.Filled.Favorite
val HeartBorderIcon get() = Icons.Outlined.FavoriteBorder
val ShareIcon     get() = Icons.Filled.Share
val AddIcon       get() = Icons.Filled.Add
val SearchIcon    get() = Icons.Filled.Search
val HomeIcon      get() = Icons.Filled.Home
val LibraryIcon   get() = Icons.Filled.LibraryMusic
val PersonIcon    get() = Icons.Filled.Person
val ArrowBackIcon get() = Icons.Filled.ArrowBack
val ArrowDownIcon get() = Icons.Filled.KeyboardArrowDown
val VolumeIcon    get() = Icons.Filled.VolumeUp
val QueueIcon     get() = Icons.Filled.QueueMusic
val DownloadIcon  get() = Icons.Filled.Download
val MicIcon       get() = Icons.Filled.Mic
val FilterIcon    get() = Icons.Filled.FilterList
val CheckIcon     get() = Icons.Filled.CheckCircle
val ChevronRightIcon get() = Icons.Filled.ChevronRight
