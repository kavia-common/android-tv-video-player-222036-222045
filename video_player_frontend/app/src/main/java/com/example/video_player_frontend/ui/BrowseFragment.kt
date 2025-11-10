package com.example.video_player_frontend.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.Presenter
import com.example.video_player_frontend.model.VideoItem
import com.example.video_player_frontend.util.VideoCatalog

/**
 * PUBLIC_INTERFACE
 * Leanback Browse screen showing a single row of static online videos.
 * D-Pad navigation is supported by Leanback default behaviors.
 */
class BrowseFragment : BrowseSupportFragment() {

    private lateinit var rowsAdapter: ArrayObjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "Online Video Catalog"
        headersState = HEADERS_DISABLED
        isHeadersTransitionOnBackEnabled = false

        Log.d("TVApp", "BrowseFragment onCreate; building rows")
        setupRows()
        setupClickListeners()
    }

    private fun setupRows() {
        rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = rowsAdapter

        val cardPresenter: Presenter = VideoCardPresenter()
        val cardRowAdapter = ArrayObjectAdapter(cardPresenter)

        VideoCatalog.getAll().forEach { cardRowAdapter.add(it) }

        val listRow = ListRow(null, cardRowAdapter)
        rowsAdapter.add(listRow)
        Log.d("TVApp", "BrowseFragment rows set with ${cardRowAdapter.size()} items")
    }

    private fun setupClickListeners() {
        onItemViewClickedListener = OnItemViewClickedListener { _, item, _, _ ->
            if (item is VideoItem) {
                Log.d("TVApp", "Item clicked: title=${item.title} url=${item.url}")
                val intent = Intent(requireContext(), PlayerActivity::class.java).apply {
                    putExtras(
                        bundleOf(
                            PlayerActivity.EXTRA_TITLE to item.title,
                            PlayerActivity.EXTRA_URL to item.url,
                            PlayerActivity.EXTRA_DESC to item.description
                        )
                    )
                }
                startActivity(intent)
            }
        }
    }
}
