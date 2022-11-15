package com.example.androidtestsdemo.application.android

import android.content.Context
import android.content.SharedPreferences

class DefaultStore(
    private val context: Context,
) : IStore {

    private val prefs: SharedPreferences
        get() = context.getSharedPreferences(
            "com.example.androidtestsdemo",
            Context.MODE_PRIVATE
        )

    override fun setQty(qty: Int) {
        prefs.edit()
            .putInt("qty", qty).apply()
    }

    override fun getQty(): Int {
        return prefs.getInt("qty", 0)
    }
}