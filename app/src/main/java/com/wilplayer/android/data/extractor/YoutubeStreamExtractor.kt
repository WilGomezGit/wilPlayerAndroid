package com.wilplayer.android.data.extractor

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.schabi.newpipe.extractor.NewPipe
import org.schabi.newpipe.extractor.ServiceList
import org.schabi.newpipe.extractor.services.youtube.YoutubeService
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

/**
 * Custom Downloader for NewPipe Extractor using OkHttp
 */
class WilPlayerDownloader private constructor() : org.schabi.newpipe.extractor.downloader.Downloader() {
    private val client = okhttp3.OkHttpClient.Builder()
        .followRedirects(true)
        .build()

    override fun execute(request: org.schabi.newpipe.extractor.downloader.Request): org.schabi.newpipe.extractor.downloader.Response {
        val builder = okhttp3.Request.Builder().url(request.url())
        request.headers().forEach { (key, values) ->
            values.forEach { value -> builder.addHeader(key, value) }
        }
        
        val response = client.newCall(builder.build()).execute()
        val body = response.body?.string()
        return org.schabi.newpipe.extractor.downloader.Response(
            response.code,
            response.message,
            response.headers.toMultimap(),
            body,
            response.request.url.toString()
        )
    }

    companion object {
        private var instance: WilPlayerDownloader? = null
        fun getInstance(): WilPlayerDownloader {
            if (instance == null) instance = WilPlayerDownloader()
            return instance!!
        }
    }
}
