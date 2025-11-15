package xyz.malefic.extensions.collection

/**
 * A MutableMap implementation that maintains access-order iteration.
 * The least-recently-used (LRU) entry is at the front of the iteration order.
 *
 * This is achieved by removing and re-inserting entries on access (get/put).
 *
 * @param K the type of keys maintained by this map
 * @param V the type of mapped values
 * @param initialCapacity the initial capacity of the map (default is 16)
 * @param loadFactor the load factor of the map (default is 0.75f)
 */
class AccessOrderLinkedHashMap<K, V>(
    initialCapacity: Int = 16,
    loadFactor: Float = 0.75f,
) : MutableMap<K, V> {
    private val backing = LinkedHashMap<K, V>(initialCapacity, loadFactor)

    /** Returns the number of key-value pairs in the map. */
    override val size: Int get() = backing.size

    /** Checks if the map is empty. */
    override fun isEmpty(): Boolean = backing.isEmpty()

    /** Checks if the map contains the specified key. */
    override fun containsKey(key: K): Boolean = backing.containsKey(key)

    /** Checks if the map contains the specified value. */
    override fun containsValue(value: V): Boolean = backing.containsValue(value)

    /**
     * Retrieves the value associated with the specified key.
     * Moves the accessed entry to the end of the iteration order.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or null if the key is not present
     */
    override fun get(key: K): V? {
        val value = backing.remove(key) ?: return null
        backing[key] = value
        return value
    }

    /**
     * Associates the specified value with the specified key in the map.
     * If the key already exists, it is moved to the end of the iteration order.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with the key, or null if there was no mapping
     */
    override fun put(
        key: K,
        value: V,
    ): V? {
        val prev = if (backing.containsKey(key)) backing.remove(key) else null
        backing[key] = value
        return prev
    }

    /**
     * Copies all of the mappings from the specified map to this map.
     * Existing keys are updated and moved to the end of the iteration order.
     *
     * @param from the map whose mappings are to be copied
     */
    override fun putAll(from: Map<out K, V>) = from.forEach { (k, v) -> put(k, v) }

    /**
     * Removes the mapping for the specified key from the map.
     *
     * @param key the key whose mapping is to be removed
     * @return the previous value associated with the key, or null if there was no mapping
     */
    override fun remove(key: K): V? = backing.remove(key)

    /** Removes all mappings from the map. */
    override fun clear() = backing.clear()

    /** Returns a MutableSet view of the mappings contained in this map. */
    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = backing.entries

    /** Returns a MutableSet view of the keys contained in this map. */
    override val keys: MutableSet<K>
        get() = backing.keys

    /** Returns a MutableCollection view of the values contained in this map. */
    override val values: MutableCollection<V>
        get() = backing.values
}
