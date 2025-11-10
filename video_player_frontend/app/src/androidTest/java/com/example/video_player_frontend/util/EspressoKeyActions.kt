package com.example.video_player_frontend.util

import android.view.KeyEvent
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions

/**
 * Small helpers to send key events in Espresso tests.
 */
object EspressoKeyActions {
    fun pressKey(code: Int): ViewAction = ViewActions.pressKey(code)
    fun pressDpadCenter(): ViewAction = ViewActions.pressKey(KeyEvent.KEYCODE_DPAD_CENTER)
    fun pressDpadLeft(): ViewAction = ViewActions.pressKey(KeyEvent.KEYCODE_DPAD_LEFT)
    fun pressDpadRight(): ViewAction = ViewActions.pressKey(KeyEvent.KEYCODE_DPAD_RIGHT)
    fun pressBack(): ViewAction = ViewActions.pressKey(KeyEvent.KEYCODE_BACK)
}
