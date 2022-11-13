package com.example.androidtestsdemo.dagger.modules

import com.example.androidtestsdemo.backends.INumbersProvider
import com.example.androidtestsdemo.application.core.ITableGenerator
import com.example.androidtestsdemo.backends.DefaultNumbersProvider
import com.example.androidtestsdemo.application.core.DefaultTableGenerator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {
    companion object {
        @Singleton
        @Provides
        fun provideTableProvider(numbersProvider: INumbersProvider): ITableGenerator {
            return DefaultTableGenerator(numbersProvider)
        }
    }
}