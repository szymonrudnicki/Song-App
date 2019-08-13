package com.github.szymonrudnicki.songapp.data

import androidx.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = getApplicati
        assertEquals("com.github.szymonrudnicki.songapp", appContext.packageName)
    }
}
