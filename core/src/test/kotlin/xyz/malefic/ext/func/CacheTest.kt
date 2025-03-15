package xyz.malefic.ext.func

import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.Test
import kotlin.test.assertEquals

class CacheTest {
    @Test
    fun memoizeCachesResultsWithoutRecalculation() {
        val callCount = AtomicInteger(0)
        val function: (Int) -> Int = {
            callCount.incrementAndGet()
            it * 2
        }
        val memoizedFunction = function.memoize()

        assertEquals(4, memoizedFunction(2))
        assertEquals(1, callCount.get())

        assertEquals(4, memoizedFunction(2))
        assertEquals(1, callCount.get()) // Call count should not increase
    }

    @Test
    fun memoizeCachesDifferentInputsIndependently() {
        val callCount = AtomicInteger(0)
        val function: (Int) -> Int = {
            callCount.incrementAndGet()
            it * 2
        }
        val memoizedFunction = function.memoize()

        assertEquals(4, memoizedFunction(2))
        assertEquals(6, memoizedFunction(3))
        assertEquals(2, callCount.get())

        assertEquals(4, memoizedFunction(2))
        assertEquals(6, memoizedFunction(3))
        assertEquals(2, callCount.get()) // Should still be 2
    }

    @Test
    fun memoizeWithLimitVerifyEvictionBehavior() {
        val callCount = AtomicInteger(0)
        val function: (Int) -> Int = {
            callCount.incrementAndGet()
            it * 2
        }
        val memoizedFunction = function.memoizeWithLimit(2)

        assertEquals(4, memoizedFunction(2))
        assertEquals(6, memoizedFunction(3))
        assertEquals(8, memoizedFunction(4))
        assertEquals(3, callCount.get())

        assertEquals(8, memoizedFunction(4))
        assertEquals(3, callCount.get()) // No change for cached value

        assertEquals(4, memoizedFunction(2))
        assertEquals(4, callCount.get()) // Should increment because value was evicted
    }

    @Test
    fun memoizeWithExpirationVerifyExpiration() {
        val callCount = AtomicInteger(0)
        val function: (Int) -> Int = {
            callCount.incrementAndGet()
            it * 2
        }
        val memoizedFunction = function.memoizeWithExpiration(100)

        assertEquals(4, memoizedFunction(2))
        assertEquals(1, callCount.get())

        assertEquals(4, memoizedFunction(2))
        assertEquals(1, callCount.get()) // Call count unchanged for non-expired value

        Thread.sleep(150)
        assertEquals(4, memoizedFunction(2))
        assertEquals(2, callCount.get()) // Should increment after expiration
    }

    @Test
    fun concurrentMemoizeVerifyCachingBehavior() {
        val callCount = AtomicInteger(0)
        val function: (Int) -> Int = {
            callCount.incrementAndGet()
            it * 2
        }
        val memoizedFunction = function.concurrentMemoize()

        assertEquals(4, memoizedFunction(2))
        assertEquals(1, callCount.get())

        assertEquals(4, memoizedFunction(2))
        assertEquals(1, callCount.get()) // Call count should remain unchanged
    }
}
