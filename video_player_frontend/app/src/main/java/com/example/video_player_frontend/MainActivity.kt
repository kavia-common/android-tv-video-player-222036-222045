package com.example.video_player_frontend

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import com.example.video_player_frontend.ui.BrowseFragment

/**
 * PUBLIC_INTERFACE
 * Main Activity for Android TV.
 * Hosts the Leanback BrowseFragment which lists the static catalog of online videos.
 * Ensures a fragment container is present and back navigation is delegated to fragments.
 */
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Use an empty root layout as the container; BrowseFragment manages UI.
        setContentView(R.layout.activity_main)

        Log.d("TVApp", "MainActivity onCreate; savedInstanceState=$savedInstanceState")

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, BrowseFragment())
                .commitNow()
        }

        // Set up back dispatcher for future nested fragments if needed.
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Default behavior: finish the activity if no fragment consumes back
                Log.d("TVApp", "Back pressed in MainActivity; finishing")
                finish()
            }
        })
    }
}
