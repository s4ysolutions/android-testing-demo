package com.example.androidtestsdemo.backends

interface INumbersProvider {
    fun numbers(qty: Int): List<Int>
}