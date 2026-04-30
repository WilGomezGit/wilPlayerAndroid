package com.wilplayer.android.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wilplayer.android.domain.model.Song
import com.wilplayer.android.ui.theme.*

// ── Bottom Navigation ──────────────────────────────────────────────────────────

enum class NavTab(val label: String, val route: String) {
    HOME("Inicio", "home"),
    SEARCH("Buscar", "search"),
    LIBRARY("Biblioteca", "library"),
    PROFILE("Perfil", "profile"),
}

@Composable
fun WilBottomNav(
    activeTab: NavTab,
    onTabSelected: (NavTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Bg1,
        tonalElevation = 0.dp,
    ) {
        NavTab.values().forEach { tab ->
            val isActive = tab == activeTab
            NavigationBarItem(
                selected = isActive,
                onClick = { onTabSelected(tab) },
                icon = {
                    Icon(
                        imageVector = tabIcon(tab),
                        contentDescription = tab.label,
                        modifier = Modifier.size(22.dp)
                    )
                },
                label = {
                    Text(
                        text = tab.label,
                        fontSize = 10.sp,
                        fontWeight = if (isActive) FontWeight.Bold else FontWeight.Medium
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AccentPurple,
                    selectedTextColor = AccentPurple,
                    unselectedIconColor = TextTertiary,
                    unselectedTextColor = TextTertiary,
                    indicatorColor = AccentPurple.copy(alpha = 0.12f),
                )
            )
        }
    }
}

@Composable
private fun tabIcon(tab: NavTab): ImageVector = when (tab) {
    NavTab.HOME    -> HomeIcon
    NavTab.SEARCH  -> SearchIcon
    NavTab.LIBRARY -> LibraryIcon
    NavTab.PROFILE -> PersonIcon
}

// ── Mini Player ────────────────────────────────────────────────────────────────

@Composable
fun MiniPlayer(
    song: Song?,
    isPlaying: Boolean,
    progress: Float,
    paletteIndex: Int,
    onPlayPause: () -> Unit,
    onSkipNext: () -> Unit,
    onExpand: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (song == null) return

    Box(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Surface2)
            .clickable(onClick = onExpand)
    ) {
        // Content
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CoverArt(song = song, size = 40.dp, radius = 8.dp, paletteIndex = paletteIndex)

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = song.title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = song.artist,
                    fontSize = 11.sp,
                    color = TextSecondary,
                )
            }

            IconButton(
                onClick = { onPlayPause(); },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = if (isPlaying) PauseIcon else PlayIcon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            IconButton(
                onClick = { onSkipNext() },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = SkipNextIcon,
                    contentDescription = "Siguiente",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // Progress bar at bottom
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .align(Alignment.BottomCenter)
                .background(Surface3)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress.coerceIn(0f, 1f))
                    .fillMaxHeight()
                    .background(GradientBtn)
            )
        }
    }
}

// ── Section Header ─────────────────────────────────────────────────────────────

@Composable
fun SectionHeader(
    title: String,
    onSeeAll: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        if (onSeeAll != null) {
            Text(
                text = "Ver todo",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = AccentPurple,
                modifier = Modifier.clickable(onClick = onSeeAll)
            )
        }
    }
}

// ── Divider ────────────────────────────────────────────────────────────────────

@Composable
fun WilDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier,
        color = Border1,
        thickness = 1.dp
    )
}
