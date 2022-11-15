package com.example.androidtestsdemo

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidtestsdemo.TheApplication
import com.example.androidtestsdemo.mocks.MockkApplication
import com.example.androidtestsdemo.mocks.dagger.components.MockkApplicationComponent
import com.google.common.truth.Truth.assertThat
import io.mockk.junit4.MockKRule
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MockkUITest {
    @get:Rule
    val mockkRule = MockKRule(this) // eq. to clearAllMocks()

    @Test
    fun component_shouldBeMockked() {
        val app = ApplicationProvider.getApplicationContext<TheApplication>()
        assertThat(app).isInstanceOf(MockkApplication::class.java)
        assertThat(app.component).isInstanceOf(MockkApplicationComponent::class.java)
    }
}