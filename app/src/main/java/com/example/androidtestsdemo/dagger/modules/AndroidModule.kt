package com.example.androidtestsdemo.dagger.modules

import android.content.Context
import com.example.androidtestsdemo.application.android.IStore
import com.example.androidtestsdemo.application.android.DefaultStore
import dagger.Module
import dagger.Provides

@Module
class AndroidModule {
    companion object {
        @Provides
        fun provideStore(context: Context): IStore {
            return DefaultStore(context)
        }
    }
}