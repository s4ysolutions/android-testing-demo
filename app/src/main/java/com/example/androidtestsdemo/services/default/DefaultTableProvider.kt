package com.example.androidtestsdemo.services.default

import com.example.androidtestsdemo.services.INumbersSequenceProvider
import com.example.androidtestsdemo.services.ITableProvider

class DefaultTableProvider(private val numbersSequenceProvider: INumbersSequenceProvider) : ITableProvider {

    override fun rows(qty: Int): List<Pair<Int, Int>> {
        return this.numbersSequenceProvider.numbers(qty).map { Pair(it, it * 2) }
    }
}