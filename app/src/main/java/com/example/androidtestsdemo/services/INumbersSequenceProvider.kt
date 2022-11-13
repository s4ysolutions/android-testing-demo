package com.example.androidtestsdemo.services

interface INumbersSequenceProvider {
    fun numbers(qty: Int): List<Int>
}