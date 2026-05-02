package com.wilplayer.android.data.extractor

import org.schabi.newpipe.extractor.downloader.Downloader
import org.schabi.newpipe.extractor.downloader.Request
import org.schabi.newpipe.extractor.downloader.Response
import okhttp3.OkHttpClient

/**
 * Custom Downloader for NewPipe Extractor using OkHttp
 */
class WilPlayerDownloader private constructor() : Downloader() {
    // Explicit type to avoid Builder vs Client confusion in some compiler versions
    private val client: OkHttpClient = OkHttpClient.Builder()
        .followRedirects(true)
        .build()

    override fun execute(request: Request): Response {
        val builder = okhttp3.Request.Builder().url(request.url())
        request.headers().forEach { (key, values) ->
            values.forEach { value -> builder.addHeader(key, value) }
        }
        
        val response = client.newCall(builder.build()).execute()
        val body = response.body?.string()
        return Response(
            response.code,
            response.message,
            response.headers.toMultimap(),
            body ?: "",
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
