package com.example.androidtestsdemo.application.core

import com.example.androidtestsdemo.backends.INumbersProvider

class DefaultTableGenerator(private val numbersSequenceProvider: INumbersProvider) :
    ITableGenerator {

    override fun rows(qty: Int): List<Pair<Int, Int>> {
        return this.numbersSequenceProvider.numbers(qty).map { Pair(it, it * 2) }
    }
}