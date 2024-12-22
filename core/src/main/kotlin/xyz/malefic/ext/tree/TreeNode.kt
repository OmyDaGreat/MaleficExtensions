package xyz.malefic.ext.tree

/**
 * Data class representing a tree node.
 *
 * @param T The type of the value.
 * @param value The value of the node.
 * @param children The children of the node.
 */
data class TreeNode<T>(
    val value: T,
    val children: List<TreeNode<T>> = emptyList(),
) {
    /**
     * Returns the string representation of the tree node.
     *
     * @return The string representation of the tree node.
     */
    override fun toString(): String =
        buildString {
            append(value.toString())
            if (children.isNotEmpty()) {
                append(" { ")
                children.joinTo(this, separator = ", ") { it.toString() }
                append(" }")
            }
        }

    /**
     * Flattens a tree structure into a list of pairs.
     *
     * @param depth The current depth of the node.
     * @return A list of pairs containing the file and its depth.
     */
    fun flattenTree(depth: Int = 0): List<Pair<T, Int>> =
        mutableListOf<Pair<T, Int>>().also {
            it.add(value to depth)
            children.forEach { child -> it.addAll(child.flattenTree(depth + 1)) }
        }

    /**
     * Filters the tree nodes based on a given predicate.
     *
     * This function recursively filters the tree nodes. If a node does not satisfy the predicate, it
     * will be excluded from the resulting tree.
     *
     * @param predicate The condition to filter the nodes.
     * @return The filtered tree node or null if the node does not satisfy the predicate.
     */
    fun filter(predicate: (T) -> Boolean): TreeNode<T>? {
        if (!predicate(this.value)) return null
        val filteredChildren = this.children.mapNotNull { it.filter(predicate) }
        return TreeNode(this.value, filteredChildren)
    }
}
