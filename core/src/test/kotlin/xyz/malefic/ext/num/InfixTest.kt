package xyz.malefic.ext.num

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class InfixTest {
    @Test
    fun factorialOfZeroIsOne() {
        assertEquals(1, !0)
    }

    @Test
    fun factorialOfOneIsOne() {
        assertEquals(1, !1)
    }

    @Test
    fun factorialOfThree() {
        assertEquals(6, !3)
    }

    @Test
    fun factorialOfFour() {
        assertEquals(24, !4)
    }

    @Test
    fun factorialOfFive() {
        assertEquals(120, !5)
    }

    @Test
    fun factorialOfNegativeNumberThrowsException() {
        assertFailsWith<IllegalArgumentException> { !-1 }
    }

    @Test
    fun maxOfTwoNumbers() {
        assertEquals(5, 3 max 5)
    }

    @Test
    fun minOfTwoNumbers() {
        assertEquals(3, 3 min 5)
    }

    @Test
    fun maxOfThreeNumbers() {
        assertEquals(5, 3 max 5 max 4)
    }

    @Test
    fun minOfThreeNumbers() {
        assertEquals(3, 3 min 5 min 4)
    }

    @Test
    fun powerOfNumber() {
        assertEquals(8.0, 2 pow 3)
    }

    @Test
    fun powerOfNumberWithDifferentTypes() {
        assertEquals(8.0, 2L pow 3)
        assertEquals(8.0, 2.0 pow 3)
        assertEquals(8.0, 2.0f pow 3)
    }

    @Test
    fun gcdOfTwoPositiveNumbers() {
        assertEquals(6, 54 gcd 24)
    }

    @Test
    fun gcdOfPositiveAndNegativeNumber() {
        assertEquals(6, 54 gcd -24)
    }

    @Test
    fun gcdOfTwoNegativeNumbers() {
        assertEquals(-6, -54 gcd -24)
    }

    @Test
    fun gcdWithZero() {
        assertEquals(54, 54 gcd 0)
        assertEquals(24, 0 gcd 24)
    }

    @Test
    fun gcdOfTwoZeros() {
        assertEquals(0, 0 gcd 0)
    }

    @Test
    fun lcmOfTwoPositiveNumbers() {
        assertEquals(216, 54 lcm 24)
    }

    @Test
    fun lcmOfPositiveAndNegativeNumber() {
        assertEquals(-216, 54 lcm -24)
    }

    @Test
    fun lcmOfTwoNegativeNumbers() {
        assertEquals(-216, -54 lcm -24)
    }

    @Test
    fun lcmWithZero() {
        assertEquals(0, 54 lcm 0)
        assertEquals(0, 0 lcm 24)
    }

    @Test
    fun lcmOfTwoZeros() {
        assertFailsWith<ArithmeticException> { 0 lcm 0 }
    }

    @Test
    fun isDivisibleByTest() {
        assertEquals(true, 10 isDivisibleBy 2)
        assertEquals(false, 10 isDivisibleBy 3)
        assertEquals(true, 15 isDivisibleBy 5)
        assertEquals(false, 15 isDivisibleBy 4)
        assertEquals(true, 0 isDivisibleBy 1)
        assertFailsWith<ArithmeticException> { 1 isDivisibleBy 0 }
    }
}
