package xyz.malefic.extensions.collection

import kotlin.concurrent.AtomicReference

/**
 * Native implementation of ConcurrentHashMap using atomic operations.
 * This implementation uses AtomicReference for thread-safe access to the underlying map.
 * Note: This provides basic thread safety but may not have the same performance
 * characteristics as the JVM implementation for high-concurrency scenarios.
 *
 * @param K the type of keys maintained by this map
 * @param V the type of mapped values
 */
actual class ConcurrentHashMap<K, V> : MutableMap<K, V> {
    private val backingRef: AtomicReference<MutableMap<K, V>>

    actual constructor() {
        backingRef = AtomicReference(mutableMapOf())
    }

    actual constructor(initialCapacity: Int) {
        backingRef = AtomicReference(LinkedHashMap(initialCapacity))
    }

    actual constructor(initialCapacity: Int, loadFactor: Float) {
        backingRef = AtomicReference(LinkedHashMap(initialCapacity, loadFactor))
    }

    actual constructor(original: Map<K, V>) {
        backingRef = AtomicReference(original.toMutableMap())
    }

    /**
     * Atomically updates the backing map using a lock-free compare-and-set loop.
     *
     * The [block] receives the current map and must return a pair of:
     *  1. a new `MutableMap` instance to install, and
     *  2. the result value to return to the caller.
     *
     * If another thread updates the map concurrently, the operation retries until the CAS succeeds.
     *
     * Notes:
     *  - Do not mutate the provided map in place; always return a new map instance.
     *
     * @param block transformation producing the next map state and a result.
     * @param R the type of the result produced by [block].
     * @return the result from the successfully applied update.
     */
    private inline fun <R> atomicUpdate(block: (MutableMap<K, V>) -> Pair<MutableMap<K, V>, R>): R {
        while (true) {
            val current = backingRef.value
            val (updated, result) = block(current)
            if (backingRef.compareAndSet(current, updated)) {
                return result
            }
        }
    }

    /**
     * Executes a read-only operation on the backing map without modification.
     *
     * This method provides a consistent snapshot view of the map for read operations.
     * The [block] receives the current map state and can safely read from it without concerns about concurrent modifications during the read.
     *
     * Note: The snapshot is taken at the time of the call. Subsequent modifications by other threads will not be reflected in this operation.
     *
     * @param block a function that receives the current map and produces a result. * @param R the type of the result produced by [block].
     * @return the result of applying [block] to the current map state.
     */
    private inline fun <R> atomicRead(block: (Map<K, V>) -> R): R = block(backingRef.value)

    actual override val size: Int
        get() = atomicRead { it.size }

    actual override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() =
            atomicRead {
                it.entries
                    .map { entry ->
                        object : MutableMap.MutableEntry<K, V> {
                            override val key = entry.key
                            override val value = entry.value

                            override fun setValue(newValue: V): V = throw UnsupportedOperationException("Cannot modify entry directly")
                        }
                    }.toMutableSet()
            }

    actual override val keys: MutableSet<K>
        get() = atomicRead { it.keys.toMutableSet() }

    actual override val values: MutableCollection<V>
        get() = atomicRead { it.values.toMutableList() }

    actual override fun isEmpty(): Boolean = atomicRead { it.isEmpty() }

    actual override fun containsKey(key: K): Boolean = atomicRead { it.containsKey(key) }

    actual override fun containsValue(value: V): Boolean = atomicRead { it.containsValue(value) }

    actual override fun get(key: K): V? = atomicRead { it[key] }

    actual override fun put(
        key: K,
        value: V,
    ): V? =
        atomicUpdate { map ->
            val newMap = map.toMutableMap()
            val oldValue = newMap.put(key, value)
            newMap to oldValue
        }

    actual override fun putAll(from: Map<out K, V>) {
        atomicUpdate { map ->
            val newMap = map.toMutableMap()
            newMap.putAll(from)
            newMap to Unit
        }
    }

    actual override fun remove(key: K): V? =
        atomicUpdate { map ->
            val newMap = map.toMutableMap()
            val oldValue = newMap.remove(key)
            newMap to oldValue
        }

    actual override fun clear() {
        atomicUpdate { _ ->
            mutableMapOf<K, V>() to Unit
        }
    }

    actual fun putIfAbsent(
        key: K,
        value: V,
    ): V? =
        atomicUpdate { map ->
            val newMap = map.toMutableMap()
            val existing = newMap[key]
            if (existing == null && !newMap.containsKey(key)) {
                newMap[key] = value
            }
            newMap to existing
        }

    actual fun remove(
        key: K,
        value: V,
    ): Boolean =
        atomicUpdate { map ->
            val newMap = map.toMutableMap()
            val existing = newMap[key]
            val removed =
                if (existing == value) {
                    newMap.remove(key)
                    true
                } else {
                    false
                }
            newMap to removed
        }

    actual fun replace(
        key: K,
        oldValue: V,
        newValue: V,
    ): Boolean =
        atomicUpdate { map ->
            val newMap = map.toMutableMap()
            val existing = newMap[key]
            val replaced =
                if (existing == oldValue) {
                    newMap[key] = newValue
                    true
                } else {
                    false
                }
            newMap to replaced
        }

    actual fun replace(
        key: K,
        value: V,
    ): V? =
        atomicUpdate { map ->
            val newMap = map.toMutableMap()
            val oldValue =
                if (newMap.containsKey(key)) {
                    newMap.put(key, value)
                } else {
                    null
                }
            newMap to oldValue
        }
}
