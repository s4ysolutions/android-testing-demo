package com.example.androidtestsdemo.dagger.components

import android.content.Context
import com.example.androidtestsdemo.MainActivity
import com.example.androidtestsdemo.dagger.modules.CoreModule
import com.example.androidtestsdemo.dagger.modules.AndroidModule
import com.example.androidtestsdemo.dagger.modules.BackendsModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BackendsModule::class, CoreModule::class, AndroidModule::class])
interface TheApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): TheApplicationComponent
    }

    fun inject(activity: MainActivity)
}