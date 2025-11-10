package com.example.video_player_frontend.ui

import android.content.Intent
import android.view.KeyEvent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.video_player_frontend.R
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Ensures missing URL does not crash PlayerActivity/Fragment and back key exits.
 */
@RunWith(AndroidJUnit4::class)
class PlayerErrorHandlingInstrumentedTest {

    @Test
    fun player_withMissingUrl_handlesBackWithoutCrash() {
        val ctx = ApplicationProvider.getApplicationContext<android.content.Context>()
        val intent = Intent(ctx, PlayerActivity::class.java).apply {
            putExtra(PlayerActivity.EXTRA_TITLE, "No URL")
            // Intentionally no EXTRA_URL
        }
        ActivityScenario.launch<PlayerActivity>(intent).use {
            // Press back to finish gracefully
            onView(withId(R.id.player_root)).perform(pressKey(KeyEvent.KEYCODE_BACK))
        }
    }
}
