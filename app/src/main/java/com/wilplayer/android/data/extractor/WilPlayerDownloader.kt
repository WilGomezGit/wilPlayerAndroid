package com.wilplayer.android.data.extractor

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request as OkHttpRequest
import okhttp3.RequestBody.Companion.toRequestBody
import org.schabi.newpipe.extractor.downloader.Downloader
import org.schabi.newpipe.extractor.downloader.Request
import org.schabi.newpipe.extractor.downloader.Response

/**
 * Custom Downloader for NewPipe Extractor using OkHttp.
 *
 * Critical fix: OkHttp requires a non-null RequestBody for POST/PUT/PATCH methods.
 * Passing null crashes with "method POST must have a request body". We now
 * use request.dataToSend() as the body for those methods.
 */
class WilPlayerDownloader private constructor() : Downloader() {

    private val client: OkHttpClient = OkHttpClient.Builder()
        .followRedirects(true)
        .build()

    override fun execute(request: Request): Response {
        // Build the request body.
        // For GET requests dataToSend() returns null → requestBody is null → fine.
        // For POST requests dataToSend() has content → must pass non-null body to OkHttp.
        val bodyBytes = request.dataToSend()
        val requestBody = when {
            bodyBytes != null ->
                bodyBytes.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            request.httpMethod() in listOf("POST", "PUT", "PATCH") ->
                // Safety: OkHttp rejects null body on these methods even without content.
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
