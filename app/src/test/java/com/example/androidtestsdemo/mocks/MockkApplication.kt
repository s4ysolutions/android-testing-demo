package com.example.androidtestsdemo.mocks

import com.example.androidtestsdemo.TheApplication
import com.example.androidtestsdemo.dagger.components.TheApplicationComponent
import com.example.androidtestsdemo.mocks.dagger.components.DaggerMockkApplicationComponent
import io.mockk.spyk

class MockkApplication: TheApplication() {
    override fun initializeComponent(): TheApplicationComponent {
        return DaggerMockkApplicationComponent.factory().create(spyk(this))
    }
}