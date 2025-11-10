package com.example.video_player_frontend.util

import com.example.video_player_frontend.model.VideoItem

/**
 * Static catalog for reliable online video URLs (mix of MP4 and HLS).
 * These are public demo/sample content sources.
 */
object VideoCatalog {
    // PUBLIC_INTERFACE
    fun getAll(): List<VideoItem> {
        /** Returns a static list of sample videos suitable for testing. */
        return listOf(
            VideoItem(
                title = "Big Buck Bunny (HLS)",
                description = "HLS test stream",
                url = "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.m3u8",
                thumbnailUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/images/BigBuckBunny.jpg"
            ),
            VideoItem(
                title = "Sintel (HLS)",
                description = "HLS sample",
                url = "https://storage.googleapis.com/gtv-videos-bucket/sample/Sintel.m3u8",
                thumbnailUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/images/Sintel.jpg"
            ),
            VideoItem(
                title = "Tears of Steel (HLS)",
                description = "HLS sample",
                url = "https://storage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.m3u8",
                thumbnailUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/images/TearsOfSteel.jpg"
            ),
            VideoItem(
                title = "Big Buck Bunny (MP4)",
                description = "MP4 sample",
                url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                thumbnailUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/images/BigBuckBunny.jpg"
            ),
            VideoItem(
                title = "Sintel (MP4)",
                description = "MP4 sample",
                url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
                thumbnailUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/images/Sintel.jpg"
            ),
            VideoItem(
                title = "For Bigger Escape (MP4)",
                description = "MP4 sample",
                url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
                thumbnailUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerEscapes.jpg"
            ),
            VideoItem(
                title = "Subaru Outback On Street And Dirt (MP4)",
                description = "MP4 sample",
                url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
                thumbnailUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/images/SubaruOutbackOnStreetAndDirt.jpg"
            ),
            VideoItem(
                title = "We Are Going On Bullrun (MP4)",
                description = "MP4 sample",
                url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
                thumbnailUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/images/WeAreGoingOnBullrun.jpg"
            )
        )
    }
}
