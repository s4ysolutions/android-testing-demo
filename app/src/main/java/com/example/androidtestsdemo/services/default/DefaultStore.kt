package com.example.androidtestsdemo.services.default

import android.content.Context
import android.content.SharedPreferences
import com.example.androidtestsdemo.services.IStore
import com.example.androidtestsdemo.services.ITableProvider

class DefaultStore(
    private val context: Context,
    private val tableProvider: ITableProvider
) : IStore {

    val prefs: SharedPreferences
        get() = context.getSharedPreferences(
            "com.example.androidtestsdemo",
            Context.MODE_PRIVATE
        )

    override fun setQty(qty: Int) {
        prefs.edit()
            .putInt("qty", qty).apply()
    }

    override fun getTable(): List<Pair<Int, Int>> {
        return tableProvider.rows(prefs.getInt("qty", 0))
    }
}