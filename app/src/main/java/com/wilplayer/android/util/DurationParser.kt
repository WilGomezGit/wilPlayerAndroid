package com.wilplayer.android.util

object DurationParser {

    /** Parses PT3M42S → 222000 ms */
    fun parseDuration(duration: String): Long {
        if (duration.isBlank()) return 0L
        return try {
            val upper = duration.uppercase().removePrefix("PT")
            var totalSeconds = 0L
            totalSeconds += Regex("(\\d+)H").find(upper)?.groupValues?.get(1)?.toLong()?.times(3600) ?: 0L
            totalSeconds += Regex("(\\d+)M").find(upper)?.groupValues?.get(1)?.toLong()?.times(60) ?: 0L
            totalSeconds += Regex("(\\d+)S").find(upper)?.groupValues?.get(1)?.toLong() ?: 0L
            totalSeconds * 1_000L
        } catch (e: Exception) {
            0L
        }
    }

    /** 225000 ms → "3:45" */
    fun formatDuration(durationMs: Long): String {
        if (durationMs <= 0L) return "0:00"
        val totalSec = durationMs / 1_000L
        val hours   = totalSec / 3_600
        val minutes = (totalSec % 3_600) / 60
        val seconds = totalSec % 60
        return if (hours > 0) {
            "%d:%02d:%02d".format(hours, minutes, seconds)
        } else {
            "%d:%02d".format(minutes, seconds)
        }
    }

    /** Convierte "3400000" → "3.4M" (vistas) */
    fun formatViews(viewCount: String?): String {
        val count = viewCount?.toLongOrNull() ?: return ""
        return when {
            count >= 1_000_000_000 -> "%.1fB".format(count / 1_000_000_000.0)
            count >= 1_000_000     -> "%.1fM".format(count / 1_000_000.0)
            count >= 1_000         -> "%.1fK".format(count / 1_000.0)
            else                   -> count.toString()
        }
    }
}
