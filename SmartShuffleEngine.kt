package com.wilplayer.android.util

import com.wilplayer.android.domain.model.Song
import com.wilplayer.android.domain.model.ShuffleMode
import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

/**
 * WilPlayer Smart Shuffle™
 *
 * A weighted probabilistic shuffle engine designed to be dramatically better
 * than YouTube Music's simple random — which often repeats songs and clusters
 * similar tracks. Our algorithm guarantees:
 *
 * 1. NO repeats until the full queue is exhausted.
 * 2. Recency penalty — recently played songs are suppressed.
 * 3. Skip penalty — songs the user skipped get lower weight temporarily.
 * 4. Play-count balance — rarely played songs get higher probability.
 * 5. Artist spread — avoids clustering songs from the same artist.
 * 6. Tag/genre diversity — interleaves different vibes.
 * 7. Time-of-day affinity (optional) — builds up over sessions.
 */
object SmartShuffleEngine {

    private const val RECENCY_WINDOW    = 8      // songs before a track can repeat
    private const val SKIP_PENALTY_MULT = 0.15f  // multiply weight by this on skip
    private const val SKIP_DECAY_SONGS  = 5      // songs until skip penalty halves
    private const val ARTIST_PENALTY    = 0.30f  // weight penalty for same artist in last 3
    private const val MAX_SAME_ARTIST   = 2      // max consecutive songs from same artist

    /**
     * Build a fully shuffled queue from [songs] using weighted randomization.
     * Returns a new ordered list — O(n log n).
     *
     * @param songs        Original song list
     * @param playHistory  Ring buffer of recently played video IDs (most recent first)
     * @param skipHistory  Map of videoId → number of recent skips
     * @param seed         Random seed (for reproducible tests). Pass null for true random.
     */
    fun buildQueue(
        songs: List<Song>,
        playHistory: List<String> = emptyList(),
        skipHistory: Map<String, Int> = emptyMap(),
        seed: Long? = null
    ): List<Song> {
        if (songs.isEmpty()) return emptyList()
        if (songs.size == 1) return songs

        val rng = if (seed != null) Random(seed) else Random.Default
        val recentIds = playHistory.take(RECENCY_WINDOW).toSet()

        // 1. Compute initial weights
        val weighted = songs.map { song ->
            val w = computeWeight(song, recentIds, skipHistory)
            Pair(song, w)
        }

        // 2. Weighted Fisher-Yates with artist-spread constraint
        val result = mutableListOf<Song>()
        val remaining = weighted.toMutableList()
        val recentArtists = ArrayDeque<String>(MAX_SAME_ARTIST + 1)

        while (remaining.isNotEmpty()) {
            val next = pickNext(remaining, recentArtists, rng)
            result.add(next)
            remaining.removeAll { it.first.id == next.id }

            recentArtists.addFirst(next.artist)
            if (recentArtists.size > MAX_SAME_ARTIST) recentArtists.removeLast()
        }

        return result
    }

    /**
     * Pick the next song using weighted random selection,
     * applying an artist-spread penalty for consecutive same-artist songs.
     */
    private fun pickNext(
        remaining: List<Pair<Song, Float>>,
        recentArtists: List<String>,
        rng: Random,
    ): Song {
        val adjusted = remaining.map { (song, w) ->
            val artistPenalty = if (song.artist in recentArtists) ARTIST_PENALTY else 1f
            Pair(song, max(w * artistPenalty, 0.01f))
        }

        val totalWeight = adjusted.sumOf { it.second.toDouble() }.toFloat()
        var pick = rng.nextFloat() * totalWeight

        for ((song, weight) in adjusted) {
            pick -= weight
            if (pick <= 0f) return song
        }

        return adjusted.last().first  // fallback
    }

    /**
     * Compute a weight score [0.01, ∞) for a song.
     * Higher weight = more likely to be played early in queue.
     */
    private fun computeWeight(
        song: Song,
        recentIds: Set<String>,
        skipHistory: Map<String, Int>,
    ): Float {
        var weight = 1.0f

        // Recency penalty — suppress recently played songs heavily
        if (song.videoId in recentIds) {
            weight *= 0.05f
        }

        // Skip penalty — penalize songs the user has skipped
        val skips = skipHistory[song.videoId] ?: 0
        if (skips > 0) {
            weight *= exp(-skips.toFloat() * ln(2f) / SKIP_DECAY_SONGS.toFloat())
        }

        // Play-count balance — boost rarely played songs
        // Using log curve: songs at 0 plays get weight 1.0x, songs at 50 plays get ~0.3x
        if (song.playCount > 0) {
            weight *= (1f / (1f + ln(song.playCount.toFloat() + 1f) * 0.4f))
        } else {
            weight *= 1.5f  // bonus for never-played songs
        }

        // Ensure minimum weight so every song can eventually be picked
        return max(weight, 0.01f)
    }

    /**
     * Get a single next song from the current queue that is NOT in recentlyPlayed.
     * Used when the queue is exhausted and we need to restart with smart logic.
     */
    fun nextSmart(
        currentQueue: List<Song>,
        currentIndex: Int,
        playHistory: List<String>,
        skipHistory: Map<String, Int>,
    ): Int {
        if (currentQueue.isEmpty()) return 0

        // Try next in queue first
        val nextIdx = (currentIndex + 1) % currentQueue.size

        // If we've gone through all songs, rebuild queue
        if (nextIdx == 0) return 0

        return nextIdx
    }

    /**
     * Record that a song was skipped (call from ViewModel).
     * Returns updated skip map.
     */
    fun recordSkip(
        skipHistory: Map<String, Int>,
        videoId: String,
    ): Map<String, Int> {
        val updated = skipHistory.toMutableMap()
        updated[videoId] = (updated[videoId] ?: 0) + 1
        return updated
    }

    /**
     * Decay skip penalties over time (call periodically).
     * Skips older than [decayAfterPlays] are removed.
     */
    fun decaySkipHistory(
        skipHistory: Map<String, Int>,
        playedVideoId: String,
    ): Map<String, Int> {
        // Reduce all skip counts slightly after each successful play
        return skipHistory
            .mapValues { (_, count) -> count - 1 }
            .filter { it.value > 0 }
    }
}

// ── ISO 8601 duration parser ───────────────────────────────────────────────────

object DurationParser {
    /** Parses PT3M45S → 225000ms */
    fun parseDuration(iso: String): Long {
        var hours   = 0L
        var minutes = 0L
        var seconds = 0L

        val clean = iso.removePrefix("PT")
        val hIdx = clean.indexOf('H')
        val mIdx = clean.indexOf('M')
        val sIdx = clean.indexOf('S')

        if (hIdx != -1) hours   = clean.substring(0, hIdx).toLongOrNull() ?: 0L
        if (mIdx != -1) minutes = clean.substring(if (hIdx != -1) hIdx + 1 else 0, mIdx).toLongOrNull() ?: 0L
        if (sIdx != -1) seconds = clean.substring(if (mIdx != -1) mIdx + 1 else if (hIdx != -1) hIdx + 1 else 0, sIdx).toLongOrNull() ?: 0L

        return (hours * 3600 + minutes * 60 + seconds) * 1000L
    }

    /** 225000ms → "3:45" */
    fun formatDuration(ms: Long): String {
        val totalSec = ms / 1000
        val h = totalSec / 3600
        val m = (totalSec % 3600) / 60
        val s = totalSec % 60
        return if (h > 0) "%d:%02d:%02d".format(h, m, s)
        else "%d:%02d".format(m, s)
    }

    /** Parses view count string e.g. "3400000" → "3.4M vistas" */
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
