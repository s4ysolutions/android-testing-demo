package com.example.androidtestsdemo.dagger.modules

import com.example.androidtestsdemo.backends.INumbersProvider
import com.example.androidtestsdemo.backends.DefaultNumbersProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BackendsModule {
    companion object {
        @Singleton
        @Provides
        fun provideNumbersProvider(): INumbersProvider {
            return DefaultNumbersProvider()
        }
    }
}