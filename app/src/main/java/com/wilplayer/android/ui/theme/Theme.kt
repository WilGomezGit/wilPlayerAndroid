package com.wilplayer.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// ── Design Tokens — extraídos exactamente del diseño WilPlayer ──────────────

// Backgrounds
val Bg0 = Color(0xFF08080F)
val Bg1 = Color(0xFF0F0F1A)
val Bg2 = Color(0xFF161625)

// Surfaces
val Surface0 = Color(0xFF1A1A2E)
val Surface2 = Color(0xFF22223A)
val Surface3 = Color(0xFF2A2A48)

// Text
val TextPrimary   = Color(0xFFFFFFFF)
val TextSecondary = Color(0xFFA0A0C0)
val TextTertiary  = Color(0xFF5A5A7A)

// Borders
val Border1 = Color(0x12FFFFFF)  // rgba(255,255,255,.07)
val Border2 = Color(0x21FFFFFF)  // rgba(255,255,255,.13)

// Accent / Brand
val AccentPurple = Color(0xFF8B5CF6)
val AccentBlue   = Color(0xFF3B82F6)
val AccentMid    = Color(0xFF6D6AF8)

// Gradient brushes
val GradientBrand = Brush.linearGradient(
    colors = listOf(Color(0xFF8B5CF6), Color(0xFF6D6AF8), Color(0xFF3B82F6))
)
val GradientBtn = Brush.horizontalGradient(
    colors = listOf(Color(0xFF8B5CF6), Color(0xFF3B82F6))
)
val GradientHero = Brush.linearGradient(
    colors = listOf(Color(0xFFa78bfa), Color(0xFF818CF8), Color(0xFF60A5FA))
)

// Cover color palettes (matching COVERS array in design)
data class CoverPalette(val colorA: Color, val colorB: Color, val emoji: String)

val COVER_PALETTES = listOf(
    CoverPalette(Color(0xFF8B5CF6), Color(0xFF3B82F6), "🎵"),
    CoverPalette(Color(0xFFEC4899), Color(0xFFF97316), "🎸"),
    CoverPalette(Color(0xFF06B6D4), Color(0xFF6366F1), "🌊"),
    CoverPalette(Color(0xFF10B981), Color(0xFF3B82F6), "🎧"),
    CoverPalette(Color(0xFFF43F5E), Color(0xFF8B5CF6), "💫"),
    CoverPalette(Color(0xFFF59E0B), Color(0xFFEF4444), "🔥"),
    CoverPalette(Color(0xFF6366F1), Color(0xFFEC4899), "✨"),
    CoverPalette(Color(0xFF14B8A6), Color(0xFF6366F1), "🎼"),
)

private val WilColorScheme = darkColorScheme(
    primary          = AccentPurple,
    onPrimary        = Color.White,
    primaryContainer = Surface0,
    secondary        = AccentBlue,
    onSecondary      = Color.White,
    background       = Bg0,
    onBackground     = TextPrimary,
    surface          = Surface0,
    onSurface        = TextPrimary,
    surfaceVariant   = Surface2,
    onSurfaceVariant = TextSecondary,
    outline          = Border2,
)

@Composable
fun WilPlayerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = WilColorScheme,
        content = content
    )
}
