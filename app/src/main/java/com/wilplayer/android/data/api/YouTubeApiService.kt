package com.wilplayer.android.data.api

import com.wilplayer.android.data.model.*
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {

    /** Search for videos / playlists */
    @GET("search")
    suspend fun search(
        @Query("part")       part: String = "snippet",
        @Query("q")          query: String,
        @Query("type")       type: String = "video",
        @Query("videoCategoryId") categoryId: String = "10", // Music
        @Query("maxResults") maxResults: Int = 25,
        @Query("pageToken")  pageToken: String? = null,
        @Query("key")        apiKey: String,
    ): YouTubeSearchResponse

    /** Get video details (duration, statistics) */
    @GET("videos")
    suspend fun getVideoDetails(
        @Query("part")  part: String = "snippet,contentDetails,statistics",
        @Query("id")    videoIds: String,
        @Query("key")   apiKey: String,
    ): YouTubeVideoResponse

    /** Get trending music */
    @GET("videos")
    suspend fun getTrending(
        @Query("part")         part: String = "snippet,contentDetails,statistics",
        @Query("chart")        chart: String = "mostPopular",
        @Query("videoCategoryId") categoryId: String = "10",
        @Query("regionCode")   regionCode: String = "CO",
        @Query("maxResults")   maxResults: Int = 25,
        @Query("pageToken")    pageToken: String? = null,
        @Query("key")          apiKey: String,
    ): YouTubeChartResponse

    /** Get playlist items */
    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("part")       part: String = "snippet",
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResults: Int = 50,
        @Query("pageToken")  pageToken: String? = null,
        @Query("key")        apiKey: String,
    ): YouTubePlaylistItemsResponse

    /** Related videos */
    @GET("search")
    suspend fun getRelated(
        @Query("part")          part: String = "snippet",
        @Query("relatedToVideoId") videoId: String,
        @Query("type")          type: String = "video",
        @Query("maxResults")    maxResults: Int = 15,
        @Query("key")           apiKey: String,
    ): YouTubeSearchResponse
}
