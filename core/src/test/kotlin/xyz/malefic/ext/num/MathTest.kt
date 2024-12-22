package xyz.malefic.ext.num

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MathTest {
    @Test
    fun factorial_returnsCorrectValue_forPositiveNumbers() {
        assertEquals(120, 5.factorial())
    }

    @Test
    fun factorial_returnsOne_forZero() {
        assertEquals(1, 0.factorial())
    }

    @Test
    fun factorial_throwsException_forNegativeNumbers() {
        assertFailsWith<IllegalArgumentException> { (-1).factorial() }
    }

    @Test
    fun trinum_returnsCorrectValue_forPositiveNumbers() {
        assertEquals(15, 5.trinum())
    }

    @Test
    fun trinum_returnsOne_forZero() {
        assertEquals(0, 0.trinum())
    }

    @Test
    fun trinum_throwsException_forNegativeNumbers() {
        assertFailsWith<IllegalArgumentException> { (-1).trinum() }
    }

    @Test
    fun isPrime_returnsTrue_forPrimeNumbers() {
        assertTrue(7.isPrime())
    }

    @Test
    fun isPrime_returnsFalse_forNonPrimeNumbers() {
        assertFalse(4.isPrime())
    }

    @Test
    fun isPrime_returnsFalse_forNumbersLessThanTwo() {
        assertFalse(1.isPrime())
    }

    @Test
    fun gcd_returnsCorrectValue_forPositiveNumbers() {
        assertEquals(6, gcd(54, 24))
    }

    @Test
    fun gcd_returnsFirstNumber_whenSecondNumberIsZero() {
        assertEquals(54, gcd(54, 0))
    }

    @Test
    fun generateFibonacci_returnsCorrectSequence_forPositiveCount() {
        assertEquals(listOf(0, 1, 1, 2, 3, 5, 8), 7.generateFibonacci())
    }

    @Test
    fun generateFibonacci_returnsEmptyList_forZeroCount() {
        assertEquals(emptyList(), 0.generateFibonacci())
    }

    @Test
    fun generateFibonacci_throwsException_forNegativeCount() {
        assertFailsWith<IllegalArgumentException> { (-1).generateFibonacci() }
    }
}
