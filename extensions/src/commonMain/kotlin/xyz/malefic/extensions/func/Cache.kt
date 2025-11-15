package xyz.malefic.extensions.func

import xyz.malefic.extensions.collection.AccessOrderLinkedHashMap
import xyz.malefic.extensions.collection.ConcurrentHashMap
import kotlin.time.TimeSource.Monotonic

/**
 * Creates a memoized version of the receiver function.
 *
 * @param T The input type for the function.
 * @param R The output type of the function.
 * @receiver The function to be memoized.
 * @return A function that caches results for previously used inputs.
 */
fun <T, R> ((T) -> R).memoize(): (T) -> R {
    val cache = mutableMapOf<T, R>()
    return { input: T ->
        cache.getOrPut(input) { this(input) }
    }
}

/**
 * Creates a size-limited memoized version of the receiver function.
 *
 * @param T The input type for the function.
 * @param R The output type of the function.
 * @param maxSize The maximum number of entries to keep in the cache.
 * @receiver The function to be memoized.
 * @return A function that caches results for previously used inputs, with a size limit.
 */
fun <T, R> ((T) -> R).memoizeWithLimit(maxSize: Int): (T) -> R {
    val cache = AccessOrderLinkedHashMap<T, R>(maxSize, 0.75f)
    return { input: T ->
        cache[input] ?: this(input).also {
            cache[input] = it
            if (cache.size > maxSize) {
                cache.remove(cache.keys.first())
            }
        }
    }
}

/**
 * Creates a time-based expiration memoized version of the receiver function.
 *
 * @param T The input type for the function.
 * @param R The output type of the function.
 * @param expirationTimeMs The time in milliseconds after which the cache entry expires.
 * @receiver The function to be memoized.
 * @return A function that caches results for previously used inputs, with an expiration time.
 */
fun <T, R> ((T) -> R).memoizeWithExpiration(expirationTimeMs: Long): (T) -> R {
    data class CachedValue<R>(
        val value: R,
        val timestamp: kotlin.time.TimeMark,
    )
    val cache = mutableMapOf<T, CachedValue<R>>()

    return { input: T ->
        val cached = cache[input]

        if (cached != null && cached.timestamp.elapsedNow().inWholeMilliseconds < expirationTimeMs) {
            cached.value
        } else {
            val result = this(input)
            cache[input] = CachedValue(result, Monotonic.markNow())
            result
        }
    }
}

/**
 * Creates a thread-safe memoized version of the receiver function.
 *
 * @param T The input type for the function.
 * @param R The output type of the function.
 * @return A function that safely caches results across multiple threads.
 */
fun <T, R> ((T) -> R).concurrentMemoize(): (T) -> R {
    val cache = ConcurrentHashMap<T, R>()
    return { input: T ->
        cache.getOrPut(input) { this(input) }
    }
}
