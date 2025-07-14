package xyz.malefic.extensions.func

import java.util.concurrent.ConcurrentHashMap

/**
 * Creates a memoized version of the receiver function.
 *
 * @param T The input type for the function.
 * @param R The output type of the function.
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
 * @return A function that caches results for previously used inputs, with a size limit.
 */
fun <T, R> ((T) -> R).memoizeWithLimit(maxSize: Int): (T) -> R {
    val cache = LinkedHashMap<T, R>(maxSize, 0.75f, true)
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
 * @return A function that caches results for previously used inputs, with an expiration time.
 */
fun <T, R> ((T) -> R).memoizeWithExpiration(expirationTimeMs: Long): (T) -> R {
    data class CachedValue<R>(
        val value: R,
        val timestamp: Long,
    )
    val cache = mutableMapOf<T, CachedValue<R>>()

    return { input: T ->
        val now = System.currentTimeMillis()
        val cached = cache[input]

        if (cached != null && now - cached.timestamp < expirationTimeMs) {
            cached.value
        } else {
            this(input).also { result ->
                cache[input] = CachedValue(result, now)
            }
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
