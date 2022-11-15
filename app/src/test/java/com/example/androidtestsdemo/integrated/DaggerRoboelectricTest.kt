package com.example.androidtestsdemo.integrated

import androidx.test.core.app.ApplicationProvider
import com.example.androidtestsdemo.TheApplication
import com.example.androidtestsdemo.mocks.MockkApplication
import com.example.androidtestsdemo.mocks.dagger.components.MockkApplicationComponent
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(application = MockkApplication::class)
@RunWith(RobolectricTestRunner::class)
class DaggerRoboelectricTest {
    @Test
    fun defaultStore_hasBeenConstructed() {
        val app = ApplicationProvider.getApplicationContext<TheApplication>()
        assertThat(app.component).isInstanceOf(MockkApplicationComponent::class.java)
    }
}