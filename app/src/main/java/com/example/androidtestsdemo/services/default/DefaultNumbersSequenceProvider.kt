package com.example.androidtestsdemo.services.default

import com.example.androidtestsdemo.services.INumbersSequenceProvider

class DefaultNumbersSequenceProvider: INumbersSequenceProvider {
    override fun numbers(qty: Int): List<Int> {
        return generateSequence(1) { it +  1 }.take(qty).toList()
    }
}