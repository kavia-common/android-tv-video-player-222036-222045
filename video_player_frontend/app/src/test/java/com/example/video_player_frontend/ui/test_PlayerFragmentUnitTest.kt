package com.example.video_player_frontend.ui

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Unit tests for PlayerFragment's URL -> MediaItem builder logic.
 * These tests avoid touching Fragment lifecycle or requiring a Context.
 */
@RunWith(RobolectricTestRunner::class)
class PlayerFragmentUnitTest {

    @Test
    fun buildMediaItem_handlesCommonExtensions_andAlwaysReturnsItem() {
        val fragment = PlayerFragment()

        // Use reflection to invoke private buildMediaItem(url: String)
        val method = PlayerFragment::class.java.getDeclaredMethod("buildMediaItem", String::class.java)
        method.isAccessible = true

        val urls = listOf(
            "https://example.com/stream.m3u8",
            "https://example.com/video.MP4",
            "https://example.com/manifest.mpd",
            "https://example.com/unknown.bin"
        )

        urls.forEach { url ->
            val item: Any? = method.invoke(fragment, url)
            // All should produce a non-null object (MediaItem), regardless of extension
            assertNotNull("MediaItem should not be null for $url", item)
            // Be robust: just assert class name contains "MediaItem" (avoid toString reliance)
            assertTrue(
                "Unexpected return type for $url",
                item!!::class.java.simpleName.contains("MediaItem")
            )
        }
    }
}
