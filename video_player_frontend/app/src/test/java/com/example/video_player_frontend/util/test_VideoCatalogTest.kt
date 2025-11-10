package com.example.video_player_frontend.util

import com.example.video_player_frontend.model.VideoItem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit tests for VideoCatalog static data.
 * Verifies size, basic fields, and URL types without network access.
 */
class VideoCatalogTest {

    @Test
    fun catalog_hasExpectedMinItems_andValidUrls() {
        val all = VideoCatalog.getAll()
        // Ensure catalog is not empty and has at least 5 items for UI row
        assertTrue("Catalog should have at least 5 items", all.size >= 5)

        // Validate fields for first item
        val first: VideoItem = all.first()
        assertTrue(first.title.isNotBlank())
        assertTrue(first.description.isNotBlank())
        assertTrue("URL must be http(s)", first.url.startsWith("http"))

        // Ensure mixture of HLS and MP4 content for player mime detection
        val hasHls = all.any { it.url.lowercase().endsWith(".m3u8") }
        val hasMp4 = all.any { it.url.lowercase().endsWith(".mp4") }
        assertTrue("Catalog should include at least one HLS item", hasHls)
        assertTrue("Catalog should include at least one MP4 item", hasMp4)

        // Thumbnails are optional but if present must be http(s)
        all.filter { !it.thumbnailUrl.isNullOrBlank() }
            .forEach { assertTrue("Thumbnail must be http(s)", it.thumbnailUrl!!.startsWith("http")) }

        // No duplicates by title to maintain clean UI
        val titles = all.map { it.title }
        assertEquals("Titles should be unique", titles.size, titles.toSet().size)
    }

    @Test
    fun catalog_items_areFocusableCandidates() {
        val all = VideoCatalog.getAll()
        // Titles should be concise for TV UI, verify not overly long (> 100 chars)
        assertFalse(all.any { it.title.length > 100 })
    }
}
