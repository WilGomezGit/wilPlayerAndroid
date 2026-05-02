package com.wilplayer.android.data.extractor

import org.schabi.newpipe.extractor.downloader.Downloader
import org.schabi.newpipe.extractor.downloader.Request
import org.schabi.newpipe.extractor.downloader.Response
import okhttp3.OkHttpClient
import okhttp3.Request as OkHttpRequest

/**
 * Custom Downloader for NewPipe Extractor using OkHttp.
 * Handles the communication between the NewPipe Extractor library and the internet.
 */
class WilPlayerDownloader private constructor() : Downloader() {
    
    private val client: OkHttpClient = OkHttpClient.Builder()
        .followRedirects(true)
        .build()

    override fun execute(request: Request): Response {
        // 1. Build the OkHttp request
        val okHttpRequestBuilder = OkHttpRequest.Builder()
            .url(request.url) // In Kotlin, use property access

        // 2. Add headers
        request.headers().forEach { (headerName, headerValues) ->
            headerValues.forEach { value ->
                okHttpRequestBuilder.addHeader(headerName, value)
            }
        }

        // 3. Execute and handle response safely
        val okHttpRequest = okHttpRequestBuilder.build()
        val okHttpResponse = client.newCall(okHttpRequest).execute()
        
        // 4. Extract all data INSIDE the use block to ensure response is open
        return okHttpResponse.use { resp ->
            val body = resp.body?.string() ?: ""
            
            // 5. Return the NewPipe Response object with correct types
            Response(
                resp.code,
                resp.message,
                resp.headers.toMultimap(),
                body,
                resp.request.url.toString()
            )
        }
    }

    companion object {
        @Volatile
        private var instance: WilPlayerDownloader? = null
        
        fun getInstance(): WilPlayerDownloader {
            return instance ?: synchronized(this) {
                instance ?: WilPlayerDownloader().also { instance = it }
            }
        }
    }
}
