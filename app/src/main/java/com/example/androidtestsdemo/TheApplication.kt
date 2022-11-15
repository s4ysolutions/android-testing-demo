package com.example.androidtestsdemo

import android.app.Application
import com.example.androidtestsdemo.dagger.components.DaggerTheApplicationComponent
import com.example.androidtestsdemo.dagger.components.TheApplicationComponent

open class TheApplication : Application() {
    val component: TheApplicationComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): TheApplicationComponent {
        return DaggerTheApplicationComponent.factory().create(this)
    }
}