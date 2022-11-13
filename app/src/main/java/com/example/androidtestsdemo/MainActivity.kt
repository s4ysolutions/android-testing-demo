package com.example.androidtestsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.androidtestsdemo.application.android.IStore
import com.example.androidtestsdemo.application.core.ITableGenerator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var store: IStore
    @Inject lateinit var tableGenerator: ITableGenerator

    override fun onCreate(savedInstanceState: Bundle?) {
        val component = (application as TheApplication).component
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        store.setQty(10)
        findViewById<TextView>(R.id.text_view).text=
            tableGenerator.rows(store.getQty()).size.toString()
    }
}