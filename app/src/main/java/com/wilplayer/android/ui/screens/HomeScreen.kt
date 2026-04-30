package com.wilplayer.android.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
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
import com.wilplayer.android.domain.model.Song
import com.wilplayer.android.ui.HomeViewModel
import com.wilplayer.android.ui.PlayerViewModel
import com.wilplayer.android.ui.components.*
import com.wilplayer.android.ui.theme.*

@Composable
fun HomeScreen(
    playerVm: PlayerViewModel,
    onNavigateToPlayer: () -> Unit,
    onNavigateToSearch: () -> Unit,
    modifier: Modifier = Modifier,
    vm: HomeViewModel = hiltViewModel(),
) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    val playerState by playerVm.playerState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Bg0),
        contentPadding = PaddingValues(bottom = 8.dp)
    ) {
        // ── Header ────────────────────────────────────────────────────────────
        item {
            HomeHeader(onSearchClick = onNavigateToSearch)
        }

        // ── Greeting ──────────────────────────────────────────────────────────
        item {
            Greeting(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp))
        }

        // ── Quick access grid (recent playlists) ──────────────────────────────
        if (uiState.recentPlaylists.isNotEmpty() || uiState.isLoading) {
            item {
                QuickAccessGrid(
                    items = uiState.recentPlaylists.take(4),
                    isLoading = uiState.isLoading,
                    onItemClick = { /* navigate to playlist */ },
                    modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 20.dp)
                )
            }
        }

        // ── Continue listening ─────────────────────────────────────────────────
        if (uiState.recentSongs.isNotEmpty()) {
            item {
                SectionHeader(
                    title = "Continuar escuchando",
                    onSeeAll = {},
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
            item {
                HorizontalSongCards(
                    songs = uiState.recentSongs,
                    onSongClick = { song ->
                        playerVm.playSong(song, uiState.recentSongs)
                        onNavigateToPlayer()
                    }
                )
                Spacer(Modifier.height(20.dp))
            }
        }

        // ── Trending ──────────────────────────────────────────────────────────
        if (uiState.trending.isNotEmpty() || uiState.isLoading) {
            item {
                SectionHeader(
                    title = "Tendencias en Colombia",
                    onSeeAll = {},
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
            if (uiState.isLoading) {
                item { TrendingShimmer() }
            } else {
                item {
                    HorizontalSongRows(
                        songs = uiState.trending,
                        onSongClick = { song ->
                            playerVm.playSong(song, uiState.trending)
                            onNavigateToPlayer()
                        }
                    )
                    Spacer(Modifier.height(20.dp))
                }
            }
        }

        // ── Mood selector ─────────────────────────────────────────────────────
        item {
            SectionHeader(
                title = "Según tu estado de ánimo",
                modifier = Modifier.padding(bottom = 12.dp)
            )
            MoodRow(onMoodClick = { mood -> vm.searchByMood(mood) })
            Spacer(Modifier.height(8.dp))
        }

        // ── Error state ───────────────────────────────────────────────────────
        if (uiState.error != null) {
            item {
                ErrorState(
                    message = uiState.error!!,
                    onRetry = { vm.refresh() },
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

// ── Header ────────────────────────────────────────────────────────────────────

@Composable
private fun HomeHeader(onSearchClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // WilPlayer logo text
        GradientText(
            text = "WilPlayer",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = SearchIcon,
                    contentDescription = "Buscar",
                    tint = TextSecondary,
                    modifier = Modifier.size(22.dp)
                )
            }
            // Avatar
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(GradientBtn),
                contentAlignment = Alignment.Center
            ) {
                Text("W", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}

// ── Greeting ──────────────────────────────────────────────────────────────────

@Composable
private fun Greeting(modifier: Modifier = Modifier) {
    val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
    val greeting = when {
        hour < 12 -> "Buenos días ☀️"
        hour < 18 -> "Buenas tardes 👋"
        else      -> "Buenas noches 🌙"
    }
    Column(modifier = modifier) {
        Text(greeting, fontSize = 13.sp, color = TextSecondary)
        Text("¿Qué quieres escuchar?", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
    }
}

// ── Quick Access Grid (2×2) ───────────────────────────────────────────────────

@Composable
private fun QuickAccessGrid(
    items: List<com.wilplayer.android.domain.model.Playlist>,
    isLoading: Boolean,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val mockItems = listOf("Chill Vibes 2026", "Late Night Coding", "Workout Boost", "Deep Focus")

    Column(modifier = modifier) {
        repeat(2) { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = if (row == 0) 8.dp else 0.dp)
            ) {
                repeat(2) { col ->
                    val idx = row * 2 + col
                    if (isLoading) {
                        ShimmerBox(
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp),
                            shape = RoundedCornerShape(10.dp)
                        )
                    } else {
                        val palette = COVER_PALETTES[idx % COVER_PALETTES.size]
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Surface0)
                                .clickable { if (idx < items.size) onItemClick(items[idx].id) }
                                .height(50.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .background(
                                        Brush.linearGradient(listOf(palette.colorA, palette.colorB))
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(palette.emoji, fontSize = 18.sp)
                            }
                            Text(
                                text = if (idx < items.size) items[idx].name else mockItems.getOrElse(idx) { "" },
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

// ── Horizontal Song Cards (large, scrollable) ─────────────────────────────────

@Composable
private fun HorizontalSongCards(
    songs: List<Song>,
    onSongClick: (Song) -> Unit,
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(songs) { idx, song ->
            Column(
                modifier = Modifier
                    .width(130.dp)
                    .clickable { onSongClick(song) }
            ) {
                CoverArt(song = song, size = 130.dp, radius = 12.dp, paletteIndex = idx)
                Spacer(Modifier.height(6.dp))
                Text(
                    song.title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(song.artist, fontSize = 11.sp, color = TextSecondary, maxLines = 1)
            }
        }
    }
}

// ── Horizontal Song Rows (compact) ────────────────────────────────────────────

@Composable
private fun HorizontalSongRows(
    songs: List<Song>,
    onSongClick: (Song) -> Unit,
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(songs) { idx, song ->
            Row(
                modifier = Modifier
                    .width(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Surface0)
                    .clickable { onSongClick(song) }
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                CoverArt(song = song, size = 42.dp, radius = 8.dp, paletteIndex = idx)
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        song.title, fontSize = 12.sp, fontWeight = FontWeight.SemiBold,
                        color = TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
                    Text(song.artist, fontSize = 11.sp, color = TextSecondary, maxLines = 1)
                }
            }
        }
    }
}

// ── Mood Row ──────────────────────────────────────────────────────────────────

@Composable
private fun MoodRow(onMoodClick: (String) -> Unit) {
    val moods = listOf(
        Triple("Chill", 0, "Chill"),
        Triple("Enfocado", 3, "focus lo-fi"),
        Triple("Energía", 5, "energetic workout"),
        Triple("Melancólico", 4, "sad songs"),
        Triple("Feliz", 6, "happy pop"),
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(moods) { (label, paletteIdx, query) ->
            val palette = COVER_PALETTES[paletteIdx % COVER_PALETTES.size]
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Brush.linearGradient(listOf(palette.colorA, palette.colorB)))
                    .clickable { onMoodClick(query) },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(palette.emoji, fontSize = 28.sp)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        label, fontSize = 11.sp, fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

// ── Shimmer states ────────────────────────────────────────────────────────────

@Composable
private fun TrendingShimmer() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(4) {
            ShimmerBox(modifier = Modifier.width(200.dp).height(64.dp), shape = RoundedCornerShape(12.dp))
        }
    }
}
