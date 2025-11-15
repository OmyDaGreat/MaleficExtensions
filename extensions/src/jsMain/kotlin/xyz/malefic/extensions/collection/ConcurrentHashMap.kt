package xyz.malefic.extensions.collection

/**
 * JavaScript implementation of ConcurrentHashMap that uses a regular MutableMap.
 * JavaScript is single-threaded by default, so true concurrency is not needed.
 * This implementation provides the same API but without thread-safety overhead.
 *
 * @param K the type of keys maintained by this map
 * @param V the type of mapped values
 */
actual class ConcurrentHashMap<K, V> : MutableMap<K, V> {
    private val backing: MutableMap<K, V>

    actual constructor() {
        backing = mutableMapOf()
    }

    actual constructor(initialCapacity: Int) {
        backing = LinkedHashMap(initialCapacity)
    }

    actual constructor(initialCapacity: Int, loadFactor: Float) {
        backing = LinkedHashMap(initialCapacity, loadFactor)
    }

    actual constructor(original: Map<K, V>) {
        backing = original.toMutableMap()
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
    ): V? {
        val existing = backing[key]
        if (existing == null && !backing.containsKey(key)) {
            backing[key] = value
        }
        return existing
    }

    actual fun remove(
        key: K,
        value: V,
    ): Boolean {
        val existing = backing[key]
        return if (existing == value) {
            backing.remove(key)
            true
        } else {
            false
        }
    }

    actual fun replace(
        key: K,
        oldValue: V,
        newValue: V,
    ): Boolean {
        val existing = backing[key]
        return if (existing == oldValue) {
            backing[key] = newValue
            true
        } else {
            false
        }
    }

    actual fun replace(
        key: K,
        value: V,
    ): V? =
        if (backing.containsKey(key)) {
            backing.put(key, value)
        } else {
            null
        }
}
