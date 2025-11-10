package com.example.video_player_frontend.ui

import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.video_player_frontend.R

/**
 * Fragment that manages Media3 ExoPlayer playback with PlayerView controller.
 * Lifecycle: initialize player in onStart/onResume, release in onPause/onStop, respecting API level recommendations.
 * D-Pad controls default to PlayerView, but we also handle play/pause/seek for reliability.
 */
class PlayerFragment : Fragment() {

    private var playerView: PlayerView? = null
    private var player: ExoPlayer? = null

    private val titleArg: String? get() = arguments?.getString(PlayerActivity.EXTRA_TITLE)
    private val urlArg: String? get() = arguments?.getString(PlayerActivity.EXTRA_URL)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_player, container, false)
        playerView = root.findViewById(R.id.exo_player_view)

        // Ensure the view can receive key events for DPAD / media keys.
        root.isFocusable = true
        root.isFocusableInTouchMode = true
        root.requestFocus()

        // Optional: Set content description/title for accessibility
        playerView?.contentDescription = titleArg ?: "Video"

        // Handle basic key events for TV remotes (play/pause/seek/back)
        root.setOnKeyListener { _, keyCode, event ->
            if (event.action != KeyEvent.ACTION_DOWN) return@setOnKeyListener false
            val exo = player ?: return@setOnKeyListener false
            when (keyCode) {
                KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {
                    if (exo.isPlaying) exo.pause() else exo.play()
                    true
                }
                KeyEvent.KEYCODE_MEDIA_PLAY -> {
                    exo.play()
                    true
                }
                KeyEvent.KEYCODE_MEDIA_PAUSE -> {
                    exo.pause()
                    true
                }
                KeyEvent.KEYCODE_DPAD_LEFT -> {
                    // Seek backward 10s
                    exo.seekBack()
                    true
                }
                KeyEvent.KEYCODE_DPAD_RIGHT -> {
                    // Seek forward 10s
                    exo.seekForward()
                    true
                }
                KeyEvent.KEYCODE_BACK -> {
                    requireActivity().finish()
                    true
                }
                else -> false
            }
        }

        return root
    }

    private fun buildMediaItem(url: String): MediaItem {
        val lower = url.lowercase()
        val contentType = when {
            lower.endsWith(".m3u8") -> MimeTypes.APPLICATION_M3U8
            lower.endsWith(".mpd") -> MimeTypes.APPLICATION_MPD
            lower.endsWith(".mp4") -> MimeTypes.VIDEO_MP4
            else -> null
        }

        val builder = MediaItem.Builder().setUri(url)
        if (contentType != null) builder.setMimeType(contentType)
        return builder.build()
    }

    private fun initializePlayer() {
        if (player != null) return
        val ctx = requireContext()
        val url = urlArg ?: return

        player = ExoPlayer.Builder(ctx).build().also { exo ->
            playerView?.player = exo
            exo.setMediaItem(buildMediaItem(url))
            exo.prepare()
            exo.playWhenReady = true
        }
    }

    private fun releasePlayer() {
        playerView?.player = null
        player?.release()
        player = null
    }

    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT < 24) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < 24) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT >= 24) {
            releasePlayer()
        }
    }
}
