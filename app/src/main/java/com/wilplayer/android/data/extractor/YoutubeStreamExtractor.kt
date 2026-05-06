package com.wilplayer.android.data.extractor

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.schabi.newpipe.extractor.NewPipe
import org.schabi.newpipe.extractor.ServiceList
import org.schabi.newpipe.extractor.stream.StreamInfo
import javax.inject.Inject
import javax.inject.Singleton

private const val CACHE_TTL_MS = 45 * 60 * 1_000L   // YouTube stream URLs expire ~1h; use 45 min

/** Holds a resolved stream URL together with the timestamp it was fetched. */
private data class CachedUrl(val url: String, val fetchedAt: Long = System.currentTimeMillis()) {
    fun isExpired() = System.currentTimeMillis() - fetchedAt > CACHE_TTL_MS
}

@Singleton
class YoutubeStreamExtractor @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var isInitialized = false

    // In-memory cache: videoId → resolved stream URL (with TTL)
    private val urlCache = HashMap<String, CachedUrl>()

    private fun ensureInitialized() {
        if (!isInitialized) {
            NewPipe.init(WilPlayerDownloader.getInstance())
            isInitialized = true
        }
    }

    suspend fun extractStreamUrl(videoId: String): String? = withContext(Dispatchers.IO) {
        // Return cached URL if still valid
        urlCache[videoId]?.takeUnless { it.isExpired() }?.let { return@withContext it.url }

        ensureInitialized()
        try {
            val url = "https://www.youtube.com/watch?v=$videoId"
            val streamInfo = StreamInfo.getInfo(ServiceList.YouTube, url)

            // Prefer M4A (AAC) — best ExoPlayer compatibility; fall back to highest bitrate
            val audioStream = streamInfo.audioStreams
                .filter { it.format?.name?.lowercase()?.contains("m4a") == true }
                .maxByOrNull { it.bitrate }
                ?: streamInfo.audioStreams.maxByOrNull { it.bitrate }

            audioStream?.content?.also { streamUrl ->
                urlCache[videoId] = CachedUrl(streamUrl)
            }
        } } catch (e: Exception) {
            Log.e("YtExtractor", "Error for $videoId", e)
            throw e
            }
        }
    }

    /** Removes a cached entry so the next call re-extracts (useful on playback error). */
    fun invalidate(videoId: String) {
        urlCache.remove(videoId)
    }

    /** Clears the entire stream URL cache. */
    fun clearCache() {
        urlCache.clear()
    }
}
