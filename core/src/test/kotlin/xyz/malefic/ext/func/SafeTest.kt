package xyz.malefic.ext.func

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class SafeTest {
    @Test
    fun safelyReturnsResultOnSuccess() {
        val function: (Int) -> Int = { it * 2 }
        assertEquals(4, function.safely(2))
    }

    @Test
    fun safelyReturnsNullOnException() {
        val function: (Int) -> Int = { throw RuntimeException("Error") }
        assertNull(function.safely(2))
    }

    @Test
    fun safelyOrReturnsResultOnSuccess() {
        val function: (Int) -> Int = { it * 2 }
        assertEquals(4, function.safelyOr(2, 0))
    }

    @Test
    fun safelyOrReturnsDefaultOnException() {
        val function: (Int) -> Int = { throw RuntimeException("Error") }
        assertEquals(0, function.safelyOr(2, 0))
    }
}
