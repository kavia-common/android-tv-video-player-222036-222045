package com.example.video_player_frontend.ui

import android.content.Intent
import android.os.Bundle
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
    }

    private fun setupClickListeners() {
        onItemViewClickedListener = OnItemViewClickedListener { _, item, _, _ ->
            if (item is VideoItem) {
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
