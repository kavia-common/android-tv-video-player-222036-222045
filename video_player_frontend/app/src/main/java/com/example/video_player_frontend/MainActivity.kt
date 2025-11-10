package com.example.video_player_frontend

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.video_player_frontend.ui.BrowseFragment

/**
 * Main Activity for Android TV.
 * Hosts the Leanback BrowseFragment which lists the static catalog of online videos.
 */
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Use an empty root layout as the container; BrowseFragment manages UI.
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, BrowseFragment())
                .commitNow()
        }
    }
}
