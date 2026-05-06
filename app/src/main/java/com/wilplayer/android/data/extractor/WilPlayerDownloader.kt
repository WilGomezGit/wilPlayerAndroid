package com.wilplayer.android.data.extractor

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request as OkHttpRequest
import okhttp3.RequestBody.Companion.toRequestBody
import org.schabi.newpipe.extractor.downloader.Downloader
import org.schabi.newpipe.extractor.downloader.Request
import org.schabi.newpipe.extractor.downloader.Response

class WilPlayerDownloader private constructor() : Downloader() {

    private val client: OkHttpClient = OkHttpClient.Builder()
        .followRedirects(true)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                .header("Accept-Language", "en-US,en;q=0.9")
                // Esta cookie evita la página "The page needs to be reloaded"
                .header("Cookie", "CONSENT=YES+")
                .build()
            chain.proceed(request)
        }
        .build()

    override fun execute(request: Request): Response {
        val bodyBytes = request.dataToSend()
        val requestBody = when {
            bodyBytes != null ->
                bodyBytes.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            request.httpMethod() in listOf("POST", "PUT", "PATCH") ->
                ByteArray(0).toRequestBody("application/x-www-form-urlencoded".toMediaTypeOrNull())
            else -> null
        }

        val okHttpRequestBuilder = OkHttpRequest.Builder()
            .url(request.url())
            .method(request.httpMethod(), requestBody)

        request.headers().forEach { (headerName, headerValues) ->
            headerValues.forEach { value ->
                okHttpRequestBuilder.addHeader(headerName, value)
            }
        }

        val okHttpRequest = okHttpRequestBuilder.build()
        val okHttpResponse = client.newCall(okHttpRequest).execute()

        return okHttpResponse.use { resp ->
            val body = resp.body?.string() ?: ""
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

        fun getInstance(): WilPlayerDownloader =
            instance ?: synchronized(this) {
                instance ?: WilPlayerDownloader().also { instance = it }
            }
    }
}
