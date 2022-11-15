package com.example.androidtestsdemo.backends

import kotlin.random.Random

/**
 * it is a simualtion of some expternal backend service
 */
class DefaultNumbersProvider : INumbersProvider {
    override fun numbers(qty: Int): List<Int> {
        return generateSequence { Random.nextInt(100) }
            .take(qty).toList()
    }
}