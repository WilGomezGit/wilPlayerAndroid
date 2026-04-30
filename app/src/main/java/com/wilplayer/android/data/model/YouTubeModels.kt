package com.wilplayer.android.data.model

import com.google.gson.annotations.SerializedName

// ── YouTube Search Response ───────────────────────────────────────────────────

data class YouTubeSearchResponse(
    @SerializedName("kind") val kind: String,
    @SerializedName("etag") val etag: String,
    @SerializedName("nextPageToken") val nextPageToken: String? = null,
    @SerializedName("prevPageToken") val prevPageToken: String? = null,
    @SerializedName("pageInfo") val pageInfo: PageInfo,
    @SerializedName("items") val items: List<SearchItem>
)

data class PageInfo(
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("resultsPerPage") val resultsPerPage: Int
)

data class SearchItem(
    @SerializedName("kind") val kind: String,
    @SerializedName("etag") val etag: String,
    @SerializedName("id") val id: SearchItemId,
    @SerializedName("snippet") val snippet: SearchSnippet
)

data class SearchItemId(
    @SerializedName("kind") val kind: String,
    @SerializedName("videoId") val videoId: String? = null,
    @SerializedName("playlistId") val playlistId: String? = null
)

data class SearchSnippet(
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("channelId") val channelId: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnails") val thumbnails: Thumbnails,
    @SerializedName("channelTitle") val channelTitle: String,
    @SerializedName("liveBroadcastContent") val liveBroadcastContent: String
)

data class Thumbnails(
    @SerializedName("default") val default: ThumbnailInfo? = null,
    @SerializedName("medium") val medium: ThumbnailInfo? = null,
    @SerializedName("high") val high: ThumbnailInfo? = null,
    @SerializedName("standard") val standard: ThumbnailInfo? = null,
    @SerializedName("maxres") val maxres: ThumbnailInfo? = null
)

data class ThumbnailInfo(
    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int? = null,
    @SerializedName("height") val height: Int? = null
)

// ── YouTube Video Details Response ────────────────────────────────────────────

data class YouTubeVideoResponse(
    @SerializedName("kind") val kind: String,
    @SerializedName("etag") val etag: String,
    @SerializedName("items") val items: List<VideoItem>
)

data class VideoItem(
    @SerializedName("kind") val kind: String,
    @SerializedName("etag") val etag: String,
    @SerializedName("id") val id: String,
    @SerializedName("snippet") val snippet: VideoSnippet? = null,
    @SerializedName("contentDetails") val contentDetails: ContentDetails? = null,
    @SerializedName("statistics") val statistics: VideoStatistics? = null
)

data class VideoSnippet(
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("channelId") val channelId: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnails") val thumbnails: Thumbnails,
    @SerializedName("channelTitle") val channelTitle: String,
    @SerializedName("tags") val tags: List<String>? = null,
    @SerializedName("categoryId") val categoryId: String
)

data class ContentDetails(
    @SerializedName("duration") val duration: String,  // ISO 8601 e.g. PT3M45S
    @SerializedName("dimension") val dimension: String,
    @SerializedName("definition") val definition: String
)

data class VideoStatistics(
    @SerializedName("viewCount") val viewCount: String?,
    @SerializedName("likeCount") val likeCount: String?,
    @SerializedName("commentCount") val commentCount: String?
)

// ── YouTube Playlist Items Response ──────────────────────────────────────────

data class YouTubePlaylistItemsResponse(
    @SerializedName("kind") val kind: String,
    @SerializedName("etag") val etag: String,
    @SerializedName("nextPageToken") val nextPageToken: String? = null,
    @SerializedName("pageInfo") val pageInfo: PageInfo,
    @SerializedName("items") val items: List<PlaylistItem>
)

data class PlaylistItem(
    @SerializedName("kind") val kind: String,
    @SerializedName("etag") val etag: String,
    @SerializedName("id") val id: String,
    @SerializedName("snippet") val snippet: PlaylistItemSnippet
)

data class PlaylistItemSnippet(
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("channelId") val channelId: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnails") val thumbnails: Thumbnails,
    @SerializedName("channelTitle") val channelTitle: String,
    @SerializedName("playlistId") val playlistId: String,
    @SerializedName("position") val position: Int,
    @SerializedName("resourceId") val resourceId: ResourceId
)

data class ResourceId(
    @SerializedName("kind") val kind: String,
    @SerializedName("videoId") val videoId: String
)

// ── YouTube Trending / Chart Response ────────────────────────────────────────

data class YouTubeChartResponse(
    @SerializedName("kind") val kind: String,
    @SerializedName("etag") val etag: String,
    @SerializedName("nextPageToken") val nextPageToken: String? = null,
    @SerializedName("items") val items: List<VideoItem>
)
