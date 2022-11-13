package com.example.androidtestsdemo.services

interface IStore {
    fun setQty(qty: Int)
    fun getTable(): List<Pair<Int, Int>>
}