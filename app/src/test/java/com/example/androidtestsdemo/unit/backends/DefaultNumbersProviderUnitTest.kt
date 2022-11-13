package com.example.androidtestsdemo.unit.backends

import com.example.androidtestsdemo.backends.DefaultNumbersProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DefaultNumbersProviderUnitTest {
    @Test
    fun numbersProvider_works() {
        val numbersSequenceProvider = DefaultNumbersProvider()
        val seq = numbersSequenceProvider.numbers(3)
        assertThat(seq).isInstanceOf(List::class.java)
        assertThat(seq.size).isEqualTo(3)
    }
}