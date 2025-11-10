package com.example.video_player_frontend.ui

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.video_player_frontend.R
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Verifies that PlayerFragment can go through start/resume/pause/stop without crashes.
 * This indirectly validates player initialize/release guard paths.
 */
@RunWith(AndroidJUnit4::class)
class PlayerFragmentLifecycleInstrumentedTest {

    @Test
    fun playerFragment_transitions_doNotCrash() {
        val ctx = ApplicationProvider.getApplicationContext<android.content.Context>()
        val intent = Intent(ctx, PlayerActivity::class.java).apply {
            putExtra(PlayerActivity.EXTRA_TITLE, "Lifecycle Test")
            putExtra(PlayerActivity.EXTRA_URL, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4")
        }

        ActivityScenario.launch<PlayerActivity>(intent).use { scenario ->
            // Move through lifecycle states
            scenario.moveToState(androidx.lifecycle.Lifecycle.State.RESUMED)
            scenario.moveToState(androidx.lifecycle.Lifecycle.State.STARTED)
            scenario.moveToState(androidx.lifecycle.Lifecycle.State.RESUMED)
            scenario.moveToState(androidx.lifecycle.Lifecycle.State.DESTROYED)
        }
    }
}
