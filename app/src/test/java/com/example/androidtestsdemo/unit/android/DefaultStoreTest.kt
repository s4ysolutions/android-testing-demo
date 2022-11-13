package com.example.androidtestsdemo.unit.android

import androidx.test.core.app.ApplicationProvider
import com.example.androidtestsdemo.TheApplication
import com.example.androidtestsdemo.application.android.DefaultStore
import com.example.androidtestsdemo.mocks.dagger.components.MockkApplicationComponent
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DefaultStoreTest {

    @Test
    fun defaultStore_restoresZeroByDefault() {
        val app = ApplicationProvider.getApplicationContext<TheApplication>()
        val store = DefaultStore(app)

        assertThat(store.getQty()).isEqualTo(0)
    }

    @Test
    fun defaultStore_restoresValue() {
        val app = ApplicationProvider.getApplicationContext<TheApplication>()
        val store = DefaultStore(app)

        assertThat(store.getQty()).isEqualTo(0)

        store.setQty(10)
        assertThat(store.getQty()).isEqualTo(10)
        assertThat(store.getQty()).isEqualTo(10)

        store.setQty(20)
        assertThat(store.getQty()).isEqualTo(20)
        assertThat(store.getQty()).isEqualTo(20)

        store.setQty(10)
        assertThat(store.getQty()).isEqualTo(10)
        assertThat(store.getQty()).isEqualTo(10)
    }
}