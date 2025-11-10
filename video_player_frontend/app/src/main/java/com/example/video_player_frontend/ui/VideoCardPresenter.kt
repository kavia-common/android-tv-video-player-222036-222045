package com.example.video_player_frontend.ui

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.example.video_player_frontend.model.VideoItem

/**
 * Simple card presenter for video items in Leanback.
 * Uses title text prominently; no thumbnail loading to keep dependencies minimal.
 */
class VideoCardPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val context: Context = parent.context
        val cardView = ImageCardView(context).apply {
            setMainImageDimensions(320, 180)
            isFocusable = true
            isFocusableInTouchMode = true
            setBackgroundColor(Color.DKGRAY)
        }
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val video = item as VideoItem
        val cardView = viewHolder.view as ImageCardView
        cardView.titleText = video.title
        cardView.contentText = video.description
        // We skip image loading to keep this minimal; ImageCardView still provides focus affordance.
        cardView.mainImage = null
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        val cardView = viewHolder.view as ImageCardView
        cardView.mainImage = null
    }
}
