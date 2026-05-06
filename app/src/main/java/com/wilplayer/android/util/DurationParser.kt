package com.wilplayer.android.util

/**
 * Utility for parsing YouTube ISO 8601 durations and formatting milliseconds.
 *
 * Examples:
 *   parseDuration("PT3M42S")    → 222_000L  (3 min 42 sec)
 *   parseDuration("PT1H5M30S")  → 3_930_000L
 *   formatDuration(222_000L)    → "3:42"
 *   formatDuration(3_930_000L)  → "1:05:30"
 */
object DurationParser {

    /**
     * Parses an ISO 8601 duration string into milliseconds.
     * Returns 0L if the input is blank or unparseable.
     */
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

    /**
     * Formats a duration in milliseconds to a human-readable string.
     * Output: "M:SS", "MM:SS" or "H:MM:SS".
     */
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
}
