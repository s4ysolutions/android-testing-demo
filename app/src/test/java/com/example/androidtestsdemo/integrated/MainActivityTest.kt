package com.example.androidtestsdemo.integrated

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.androidtestsdemo.MainActivity
import com.example.androidtestsdemo.R
import com.example.androidtestsdemo.backends.INumbersProvider
import com.example.androidtestsdemo.mocks.MockkApplication
import com.example.androidtestsdemo.mocks.dagger.components.MockkApplicationComponent
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.junit4.MockKRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@Config(application = MockkApplication::class)
@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    @get:Rule
    val mockkRule = MockKRule(this) // eq. to clearAllMocks()

    @Inject
    lateinit var spyNumbersProvider: INumbersProvider

    @Before
    fun setup() {
        val component = ApplicationProvider
            .getApplicationContext<MockkApplication>().component as MockkApplicationComponent
        component.inject(this);
    }

    @Test
    fun textView_shouldReflectTheLengtOfNumbers() {
        every { spyNumbersProvider.numbers(any()) } returns listOf(1, 2, 3)

        launchActivity<MainActivity>().use { scenario ->
            assertThat(scenario.state).isEqualTo(Lifecycle.State.RESUMED)
            Espresso.onView(ViewMatchers.withId(R.id.text_view))
                .check(ViewAssertions.matches(ViewMatchers.withText("3")))
        }
    }
}