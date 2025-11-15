package xyz.malefic.extensions.collection

import java.util.concurrent.ConcurrentHashMap as JvmConcurrentHashMap

/**
 * Android implementation of ConcurrentHashMap that delegates to java.util.concurrent.ConcurrentHashMap.
 * This provides true thread-safe concurrent access with high performance on the Android platform.
 *
 * @param K the type of keys maintained by this map
 * @param V the type of mapped values
 */
actual class ConcurrentHashMap<K, V> : MutableMap<K, V> {
    @Suppress("JavaCollectionWithNullableTypeArgument")
    private val backing: JvmConcurrentHashMap<K, V>

    actual constructor() {
        backing = JvmConcurrentHashMap()
    }

    actual constructor(initialCapacity: Int) {
        backing = JvmConcurrentHashMap(initialCapacity)
    }

    actual constructor(
        initialCapacity: Int,
        loadFactor: Float,
    ) {
        backing = JvmConcurrentHashMap(initialCapacity, loadFactor)
    }

    actual constructor(original: Map<K, V>) {
        backing = JvmConcurrentHashMap(original)
    }

    actual override val size: Int
        get() = backing.size

    actual override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = backing.entries

    actual override val keys: MutableSet<K>
        get() = backing.keys

    actual override val values: MutableCollection<V>
        get() = backing.values

    actual override fun isEmpty(): Boolean = backing.isEmpty()

    actual override fun containsKey(key: K): Boolean = backing.containsKey(key)

    actual override fun containsValue(value: V): Boolean = backing.containsValue(value)

    actual override fun get(key: K): V? = backing[key]

    actual override fun put(
        key: K,
        value: V,
    ): V? = backing.put(key, value)

    actual override fun putAll(from: Map<out K, V>) = backing.putAll(from)

    actual override fun remove(key: K): V? = backing.remove(key)

    actual override fun clear() = backing.clear()

    actual fun putIfAbsent(
        key: K,
        value: V,
    ): V? = backing.putIfAbsent(key, value)

    actual fun remove(
        key: K,
        value: V,
    ): Boolean = backing.remove(key, value)

    actual fun replace(
        key: K,
        oldValue: V,
        newValue: V,
    ): Boolean = backing.replace(key, oldValue, newValue)

    actual fun replace(
        key: K,
        value: V,
    ): V? = backing.replace(key, value)
}
