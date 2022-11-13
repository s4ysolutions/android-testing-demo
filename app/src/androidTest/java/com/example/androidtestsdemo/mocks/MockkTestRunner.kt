package com.example.androidtestsdemo.mocks

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class MockkTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, MockkApplication::class.java.name, context)
    }
}