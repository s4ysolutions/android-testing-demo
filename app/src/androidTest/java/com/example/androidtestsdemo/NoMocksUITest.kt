package com.example.androidtestsdemo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.junit4.MockKRule
// import androidx.test.ext.junit.rules.ActivityScenarioRule
// import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class NoMocksUITest {
    @get:Rule
    val mockkRule = MockKRule(this) // eq. to clearAllMocks()

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun storeLengthDefault_shouldBe_10() {
        onView(withId(R.id.text_view)).check(matches(withText("10")))
    }
}