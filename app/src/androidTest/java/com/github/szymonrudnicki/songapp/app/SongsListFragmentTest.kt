package com.github.szymonrudnicki.songapp.app

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.github.szymonrudnicki.songapp.R
import com.github.szymonrudnicki.songapp.app.ui.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SongsListFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setUp() {
        activityRule.launchActivity(null)
    }

    @Test
    fun fragmentCanBeInstantiated() {
        onView(withId(R.id.fragment_songs_list_container))
                .check(matches(isDisplayed()))
    }

    @Test
    fun dialogIsDisplayedAfterClickingToolbarIcon() {
        onView(withId(R.id.action_select_source))
                .check(matches(isDisplayed()))
                .perform(click())

        onView(withText(R.string.select_source))
                .check(matches(isDisplayed()))
    }
}
