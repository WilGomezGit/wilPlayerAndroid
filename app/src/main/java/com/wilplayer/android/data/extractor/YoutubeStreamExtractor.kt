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

@Singleton
class YoutubeStreamExtractor @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var isInitialized = false

    private fun ensureInitialized() {
        if (!isInitialized) {
            NewPipe.init(WilPlayerDownloader.getInstance())
            isInitialized = true
        }
    }

    suspend fun extractStreamUrl(videoId: String): String? = withContext(Dispatchers.IO) {
        ensureInitialized()
        try {
            val service = ServiceList.YouTube
            val url = "https://www.youtube.com/watch?v=$videoId"
            val streamInfo = StreamInfo.getInfo(service, url)
            
            // Get the best audio stream (m4a preferred for ExoPlayer)
            val audioStream = streamInfo.audioStreams
                .filter { it.format.name.lowercase().contains("m4a") }
                .maxByOrNull { it.bitrate }
                ?: streamInfo.audioStreams.maxByOrNull { it.bitrate }

            audioStream?.content
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
