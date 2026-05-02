package com.wilplayer.android.data.extractor

import org.schabi.newpipe.extractor.downloader.Downloader
import org.schabi.newpipe.extractor.downloader.Request
import org.schabi.newpipe.extractor.downloader.Response
import okhttp3.OkHttpClient
import okhttp3.Request as OkHttpRequest
import okhttp3.Response as OkHttpResponse

/**
 * Custom Downloader for NewPipe Extractor using OkHttp.
 * Handles the communication between the NewPipe Extractor library and the internet.
 */
class WilPlayerDownloader private constructor() : Downloader() {
    
    private val client: OkHttpClient = OkHttpClient.Builder()
        .followRedirects(true)
        .build()

    @Throws(java.io.IOException::class)
    override fun execute(request: Request): Response {
        // 1. Build the OkHttp request
        val okHttpRequestBuilder = OkHttpRequest.Builder()
            .url(request.url())
            .method(request.httpMethod(), null) // NewPipe usually uses GET

        // 2. Add headers
        request.headers().forEach { (headerName, headerValues) ->
            headerValues.forEach { value ->
                okHttpRequestBuilder.addHeader(headerName, value)
            }
        }

        // 3. Execute the request
        val okHttpResponse: OkHttpResponse = client.newCall(okHttpRequestBuilder.build()).execute()
        
        // 4. Extract body and close response properly using use block
        val responseBodyString = okHttpResponse.use { response ->
            response.body?.string() ?: ""
        }

        // 5. Return the NewPipe Response object
        return Response(
            okHttpResponse.code,
            okHttpResponse.message,
            okHttpResponse.headers.toMultimap(),
            responseBodyString,
            okHttpResponse.request.url.toString()
        )
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
