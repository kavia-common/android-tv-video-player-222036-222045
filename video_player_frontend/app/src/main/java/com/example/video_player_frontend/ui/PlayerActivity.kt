package com.example.video_player_frontend.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.video_player_frontend.R

/**
 * Activity that hosts the PlayerFragment to render fullscreen playback UI.
 */
class PlayerActivity : FragmentActivity() {

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_URL = "extra_url"
        const val EXTRA_DESC = "extra_desc"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_player)

        if (savedInstanceState == null) {
            val fragment = PlayerFragment().apply {
                arguments = intent?.extras
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.player_root, fragment)
                .commitNow()
        }
    }
}
