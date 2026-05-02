package com.wilplayer.android.ui

import android.media.AudioManager
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.wilplayer.android.ui.components.*
import com.wilplayer.android.ui.screens.*
import com.wilplayer.android.ui.theme.WilPlayerTheme
import dagger.hilt.android.AndroidEntryPoint

// ── Navigation Routes ──────────────────────────────────────────────────────────────────────────────

object Routes {
    const val HOME     = "home"
    const val SEARCH   = "search"
    const val LIBRARY  = "library"
    const val PROFILE  = "profile"
    const val PLAYER   = "player"
    const val PLAYLIST = "playlist/{playlistId}"
    fun playlist(id: String) = "playlist/$id"
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Ensure physical volume buttons control media volume
        volumeControlStream = AudioManager.STREAM_MUSIC

        setContent {
            WilPlayerTheme {
                WilPlayerApp()
            }
        }
    }

    // ── Volume key support ─────────────────────────────────────────────────────────────────
    // Forward volume-key events to the system AudioManager so the phone's
    // physical buttons always control media volume (works over Bluetooth too).
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        return when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> {
                audioManager.adjustStreamVolume(
                    AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_RAISE,
                    AudioManager.FLAG_SHOW_UI
                )
                true
            }
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                audioManager.adjustStreamVolume(
                    AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_LOWER,
                    AudioManager.FLAG_SHOW_UI
                )
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }
}

@Composable
fun WilPlayerApp() {
    val navController = rememberNavController()
    val playerVm: PlayerViewModel = hiltViewModel()
    val playerState by playerVm.playerState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { playerVm.connectPlayer() }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute != Routes.PLAYER
    val showMiniPlayer = showBottomBar && playerState.currentSong != null

    val activeTab = when (currentRoute) {
        Routes.HOME -> NavTab.HOME
        Routes.SEARCH -> NavTab.SEARCH
        Routes.LIBRARY, Routes.PLAYLIST -> NavTab.LIBRARY
        Routes.PROFILE -> NavTab.PROFILE
        else -> NavTab.HOME
    }

    Scaffold(
        containerColor = com.wilplayer.android.ui.theme.Bg0,
        bottomBar = {
            if (showBottomBar) {
                Column {
                    AnimatedVisibility(
                        visible = showMiniPlayer,
                        enter = slideInVertically { it } + fadeIn(),
                        exit = slideOutVertically { it } + fadeOut(),
                    ) {
                        MiniPlayer(
                            song = playerState.currentSong,
                            isPlaying = playerState.isPlaying,
                            progress = playerState.progressFraction,
                            paletteIndex = 0,
                            onPlayPause = playerVm::playPause,
                            onSkipNext = playerVm::skipToNext,
                            onExpand = { navController.navigate(Routes.PLAYER) },
                        )
                    }
                    WilBottomNav(
                        activeTab = activeTab,
                        onTabSelected = { tab ->
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(paddingValues),
            enterTransition = { fadeIn(tween(220)) },
            exitTransition = { fadeOut(tween(220)) },
        ) {
            composable(Routes.HOME) {
                HomeScreen(
                    playerVm = playerVm,
                    onNavigateToPlayer = { navController.navigate(Routes.PLAYER) },
                    onNavigateToSearch = { navController.navigate(Routes.SEARCH) },
                    onNavigateToPlaylist = { id -> navController.navigate(Routes.playlist(id)) },
                )
            }

            composable(Routes.SEARCH) {
                SearchScreen(
                    playerVm = playerVm,
                    onNavigateToPlayer = { navController.navigate(Routes.PLAYER) },
                )
            }

            composable(Routes.LIBRARY) {
                LibraryScreen(
                    playerVm = playerVm,
                    onNavigateToPlaylist = { id -> navController.navigate(Routes.playlist(id)) },
                    onNavigateToPlayer = { navController.navigate(Routes.PLAYER) },
                )
            }

            composable(Routes.PROFILE) {
                ProfileScreen()
            }

            composable(
                route = Routes.PLAYER,
                enterTransition = { slideInVertically { it } + fadeIn(tween(300)) },
                exitTransition = { slideOutVertically { it } + fadeOut(tween(300)) },
            ) {
                PlayerScreen(
                    playerVm = playerVm,
                    onDismiss = { navController.popBackStack() },
                )
            }

            composable(Routes.PLAYLIST) { backStackEntry ->
                val playlistId = backStackEntry.arguments?.getString("playlistId") ?: return@composable
                PlaylistDetailScreen(
                    playlistId = playlistId,
                    playerVm = playerVm,
                    onNavigateToPlayer = { navController.navigate(Routes.PLAYER) },
                    onBack = { navController.popBackStack() },
                )
            }
        }
    }
}
