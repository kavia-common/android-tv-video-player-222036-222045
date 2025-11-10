package com.example.video_player_frontend.ui

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import com.example.video_player_frontend.R

/**
 * PUBLIC_INTERFACE
 * Activity that hosts the PlayerFragment to render fullscreen playback UI.
 * Ensures robust back handling and logs for emulator diagnosis.
 */
class PlayerActivity : FragmentActivity() {

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_URL = "extra_url"
        const val EXTRA_DESC = "extra_desc"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // We use the fragment layout as root container; PlayerFragment is injected inside.
        setContentView(R.layout.fragment_player)

        Log.d("TVApp", "PlayerActivity onCreate; extras=${intent?.extras}")

        if (savedInstanceState == null) {
            val fragment = PlayerFragment().apply {
                arguments = intent?.extras
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.player_root, fragment)
                .commitNow()
        }

        // Ensure back exits the player activity.
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("TVApp", "Back pressed in PlayerActivity; finishing")
                finish()
            }
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.d("TVApp", "Hardware BACK detected in PlayerActivity via onKeyDown; finishing")
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
