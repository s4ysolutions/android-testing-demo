package com.example.androidtestsdemo.mocks.dagger.components

import android.content.Context
import com.example.androidtestsdemo.MainActivityTest
import com.example.androidtestsdemo.dagger.components.TheApplicationComponent
import com.example.androidtestsdemo.mocks.dagger.modules.AndroidModule
import com.example.androidtestsdemo.mocks.dagger.modules.BackendsModule
import com.example.androidtestsdemo.mocks.dagger.modules.CoreModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BackendsModule::class, CoreModule::class, AndroidModule::class])
interface MockkApplicationComponent: TheApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): MockkApplicationComponent
    }
    fun inject(test: MainActivityTest)
}