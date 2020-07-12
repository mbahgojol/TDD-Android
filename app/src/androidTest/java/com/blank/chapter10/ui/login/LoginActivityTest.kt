package com.blank.chapter10.ui.login

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.blank.chapter10.R
import okhttp3.internal.wait
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.blank.chapter10", appContext.packageName)
    }

    @Test
    fun user_can_enter_email() {
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(ViewActions.typeText("Daniel"))
        Espresso.onView(ViewMatchers.withId(R.id.etPwd)).perform(ViewActions.typeText("Daniel"))
        Espresso.onView(ViewMatchers.withId(R.id.btnLogin))
            .check(ViewAssertions.matches(ViewMatchers.withText("Login")))
        Espresso.onView(ViewMatchers.withId(R.id.btnLogin)).perform(ViewActions.click())
    }
}