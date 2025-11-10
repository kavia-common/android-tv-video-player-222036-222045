package com.example.video_player_frontend.model

// PUBLIC_INTERFACE
data class VideoItem(
    /** Title to display in browse grid. */
    val title: String,
    /** Description or source info. */
    val description: String,
    /** Direct URL to the video (MP4 or HLS m3u8). */
    val url: String,
    /** Optional thumbnail URL or local resource name (not mandatory). */
    val thumbnailUrl: String? = null
)
