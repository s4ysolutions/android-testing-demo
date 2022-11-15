package com.example.androidtestsdemo.unit.core

import com.example.androidtestsdemo.application.core.DefaultTableGenerator
import com.example.androidtestsdemo.backends.DefaultNumbersProvider
import com.example.androidtestsdemo.backends.INumbersProvider
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import io.mockk.junit4.MockKRule
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

/**
 * Unit test should not use dagger
 */
class DefaultTableGeneratorUnitTest {
    @get:Rule
    val mockkRule = MockKRule(this) // eq. to clearAllMocks()

    private val spyNumbersProvider: INumbersProvider = spyk(DefaultNumbersProvider());

    @Test
    fun tableGenerator_works() {
        every { spyNumbersProvider.numbers(3) } returns listOf(1, 2, 3)

        val tableGenerator = DefaultTableGenerator(spyNumbersProvider)
        val table = tableGenerator.rows(3)
        assertThat(table[0].first).isEqualTo(1);
        assertThat(table[2].second).isEqualTo(6);
    }

    @Test
    fun tableGenerator_worksForAnswer() {
        every { spyNumbersProvider.numbers(any()) } answers {
            val qty = firstArg<Int>()
            generateSequence(1) {it +1  }.take(qty).toList()
        }

        val tableGenerator = DefaultTableGenerator(spyNumbersProvider)
        val qty = Random.nextInt(10, 100);
        val table = tableGenerator.rows(qty)
        assertThat(table.size).isEqualTo(qty);
        assertThat(table.first().first).isEqualTo(1);
        assertThat(table.last().second).isEqualTo(qty * 2);
    }
}