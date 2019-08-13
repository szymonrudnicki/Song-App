package com.github.szymonrudnicki.songapp.app

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.github.szymonrudnicki.songapp.app.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun appLaunchesSuccessfully() {
        activityRule.launchActivity(null)
    }
}
