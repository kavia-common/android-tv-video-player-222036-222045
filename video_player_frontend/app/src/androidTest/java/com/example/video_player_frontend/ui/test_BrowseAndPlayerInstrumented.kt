package com.example.video_player_frontend.ui

import android.content.Intent
import android.view.KeyEvent
import androidx.fragment.app.testing.launchActivity
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.video_player_frontend.MainActivity
import com.example.video_player_frontend.R
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumentation tests for Android TV UI:
 * - Verify Browse screen renders item row
 * - DPAD navigation focus behavior
 * - Clicking a card opens PlayerActivity
 * - Player screen accepts DPAD/media key events (play/pause/seek)
 *
 * Note: ExoPlayer network playback is not asserted; we only validate UI/state & intents.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class BrowseAndPlayerInstrumentedTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        Intents.init()
        val ctx = ApplicationProvider.getApplicationContext<android.content.Context>()
        val intent = Intent(ctx, MainActivity::class.java)
        scenario = ActivityScenario.launch(intent)
    }

    @After
    fun tearDown() {
        Intents.release()
        scenario.close()
    }

    @Test
    fun browse_rendersMainContainer_andFocusIsSet() {
        onView(withId(R.id.main_container))
            .check(matches(isDisplayed()))
            .check(matches(isFocusable()))
    }

    @Test
    fun browse_clicksFirstItem_andLaunchesPlayerActivity() {
        // Leanback BrowseSupportFragment uses VerticalGrid/Row; we validate via intent fired.
        // Tap center to trigger default focused card if any
        onView(withId(R.id.main_container)).perform(click())

        // After click, PlayerActivity should be launched
        Intents.intended(hasComponent(PlayerActivity::class.java.name))
    }

    @Test
    fun player_showsPlayerView_andHandlesDpadKeys() {
        // Start PlayerActivity directly with sample extras
        val ctx = ApplicationProvider.getApplicationContext<android.content.Context>()
        val intent = Intent(ctx, PlayerActivity::class.java).apply {
            putExtra(PlayerActivity.EXTRA_TITLE, "Test Title")
            putExtra(PlayerActivity.EXTRA_URL, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        }
        ActivityScenario.launch<PlayerActivity>(intent).use {
            // PlayerView should be visible and focusable
            onView(withId(R.id.exo_player_view))
                .check(matches(isDisplayed()))
                .check(matches(isFocusable()))

            // Send DPAD center to toggle play/pause, and left/right to seek
            onView(withId(R.id.exo_player_view)).perform(pressKey(KeyEvent.KEYCODE_DPAD_CENTER))
            onView(withId(R.id.exo_player_view)).perform(pressKey(KeyEvent.KEYCODE_DPAD_LEFT))
            onView(withId(R.id.exo_player_view)).perform(pressKey(KeyEvent.KEYCODE_DPAD_RIGHT))

            // Back should finish the activity
            onView(withId(R.id.exo_player_view)).perform(pressKey(KeyEvent.KEYCODE_BACK))
        }
    }
}
