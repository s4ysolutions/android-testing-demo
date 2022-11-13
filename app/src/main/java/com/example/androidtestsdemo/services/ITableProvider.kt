package com.example.androidtestsdemo.services

interface ITableProvider {
    fun rows(qty: Int):List<Pair<Int, Int>>
}