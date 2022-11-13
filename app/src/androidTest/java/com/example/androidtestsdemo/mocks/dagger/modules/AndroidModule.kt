package com.example.androidtestsdemo.mocks.dagger.modules

import android.content.Context
import com.example.androidtestsdemo.application.android.IStore
import com.example.androidtestsdemo.application.android.DefaultStore
import dagger.Module
import dagger.Provides
import io.mockk.spyk

@Module
class AndroidModule {
    companion object {
        @Provides
        fun providePersistedTableGenerator(context: Context): IStore {
            return spyk(DefaultStore(context))
        }
    }
}