package com.example.androidtestsdemo.application.core

interface ITableGenerator {
    fun rows(qty: Int):List<Pair<Int, Int>>
}