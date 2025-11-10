package com.example.video_player_frontend.ui

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.video_player_frontend.R
import com.example.video_player_frontend.model.VideoItem

/**
 * PUBLIC_INTERFACE
 * Card presenter for video items in Leanback.
 * Loads thumbnails via Glide with colorful placeholders and error fallbacks.
 */
class VideoCardPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val context: Context = parent.context
        val cardView = ImageCardView(context).apply {
            setMainImageDimensions(320, 180)
            isFocusable = true
            isFocusableInTouchMode = true
            setBackgroundColor(Color.TRANSPARENT)
            // Show content text under title when focused
            infoAreaBackground = ContextCompat.getDrawable(context, android.R.color.transparent)
            // Enable selected/focused state highlighting
            setSelected(true)
            setFocusable(true)
        }
        // Remove selected by default but keep focusable
        cardView.isSelected = false
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val video = item as VideoItem
        val cardView = viewHolder.view as ImageCardView
        cardView.titleText = video.title
        cardView.contentText = video.description

        val placeholder = ContextCompat.getDrawable(cardView.context, R.drawable.placeholder_colorful)
        val errorDrawable = ContextCompat.getDrawable(cardView.context, R.drawable.error_placeholder)

        val opts = RequestOptions()
            .placeholder(placeholder)
            .error(errorDrawable)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .centerCrop()

        val url = video.thumbnailUrl

        if (!url.isNullOrBlank()) {
            Glide.with(cardView.context)
                .asBitmap()
                .load(url)
                .apply(opts)
                .into(cardView.mainImageView)
        } else {
            // Fallback to colorful gradient so cards are never black
            cardView.mainImage = placeholder
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        val cardView = viewHolder.view as ImageCardView
        // Clear Glide to avoid leaks
        Glide.with(cardView.context).clear(cardView.mainImageView)
        cardView.mainImage = null
    }
}
