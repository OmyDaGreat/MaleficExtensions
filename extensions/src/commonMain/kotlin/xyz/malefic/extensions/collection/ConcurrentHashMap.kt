package xyz.malefic.extensions.collection

/**
 * A thread-safe MutableMap implementation that provides concurrent access.
 * Platform-specific implementations ensure thread safety according to the platform's
 * concurrency model.
 *
 * @param K the type of keys maintained by this map (must be non-nullable)
 * @param V the type of mapped values (must be non-nullable)
 */
expect class ConcurrentHashMap<K, V>() : MutableMap<K, V> {
    /**
     * Creates a new ConcurrentHashMap with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the map
     */
    constructor(initialCapacity: Int)

    /**
     * Creates a new ConcurrentHashMap with the specified initial capacity and load factor.
     *
     * @param initialCapacity the initial capacity of the map
     * @param loadFactor the load factor of the map
     */
    constructor(initialCapacity: Int, loadFactor: Float)

    /**
     * Creates a new ConcurrentHashMap containing all mappings from the specified map.
     *
     * @param original the map whose mappings are to be copied
     */
    constructor(original: Map<K, V>)

    /**
     * Returns the number of key-value mappings in this map.
     */
    override val size: Int

    /**
     * Returns a MutableSet view of the mappings contained in this map.
     */
    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>

    /**
     * Returns a MutableSet view of the keys contained in this map.
     */
    override val keys: MutableSet<K>

    /**
     * Returns a MutableCollection view of the values contained in this map.
     */
    override val values: MutableCollection<V>

    /**
     * Returns true if this map contains no key-value mappings.
     */
    override fun isEmpty(): Boolean

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key the key whose presence in this map is to be tested
     */
    override fun containsKey(key: K): Boolean

    /**
     * Returns true if this map maps one or more keys to the specified value.
     *
     * @param value the value whose presence in this map is to be tested
     */
    override fun containsValue(value: V): Boolean

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     */
    override fun get(key: K): V?

    /**
     * Associates the specified value with the specified key in this map.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with the key, or null if there was no mapping
     */
    override fun put(
        key: K,
        value: V,
    ): V?

    /**
     * Copies all of the mappings from the specified map to this map.
     *
     * @param from the map whose mappings are to be copied
     */
    override fun putAll(from: Map<out K, V>)

    /**
     * Removes the mapping for a key from this map if it is present.
     *
     * @param key the key whose mapping is to be removed
     * @return the previous value associated with the key, or null if there was no mapping
     */
    override fun remove(key: K): V?

    /**
     * Removes all of the mappings from this map.
     */
    override fun clear()

    /**
     * Atomically associates the specified key with the given value if the key
     * is not already associated with a value.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with the key, or null if there was no mapping
     */
    fun putIfAbsent(
        key: K,
        value: V,
    ): V?

    /**
     * Atomically removes the entry for a key only if currently mapped to the specified value.
     *
     * @param key the key whose mapping is to be removed
     * @param value the value expected to be associated with the key
     * @return true if the value was removed
     */
    fun remove(
        key: K,
        value: V,
    ): Boolean

    /**
     * Atomically replaces the entry for a key only if currently mapped to the specified value.
     *
     * @param key the key whose mapping is to be replaced
     * @param oldValue the value expected to be associated with the key
     * @param newValue the value to be associated with the key
     * @return true if the value was replaced
     */
    fun replace(
        key: K,
        oldValue: V,
        newValue: V,
    ): Boolean

    /**
     * Atomically replaces the entry for a key only if currently mapped to some value.
     *
     * @param key the key whose mapping is to be replaced
     * @param value the value to be associated with the key
     * @return the previous value associated with the key, or null if there was no mapping
     */
    fun replace(
        key: K,
        value: V,
    ): V?
}
