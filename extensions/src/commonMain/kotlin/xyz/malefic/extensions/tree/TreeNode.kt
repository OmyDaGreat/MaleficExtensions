package xyz.malefic.extensions.tree

/**
 * Represents a node in a tree structure, capable of holding a value and a list of child nodes.
 *
 * @param T The type of the value stored in the TreeNode.
 * @property value The value contained in the TreeNode.
 * @property children A list of child TreeNodes, defaulting to an empty list.
 *
 * Provides utility functions to create single nodes, convert the tree to a string,
 * flatten the tree into a sequence of value-depth pairs, and filter the tree based on a predicate.
 */
data class TreeNode<T>(
    val value: T,
    val children: List<TreeNode<T>> = emptyList(),
) {
    companion object {
        /**
         * Creates a single TreeNode with the specified value and no children.
         *
         * @param value The value to be stored in the TreeNode.
         * @return A TreeNode containing the specified value.
         */
        fun <T> createSingleNode(value: T): TreeNode<T> = TreeNode(value)
    }

    /**
     * Returns a string representation of the TreeNode.
     *
     * The string includes the node's value and, if present, its children
     * enclosed in curly braces and separated by commas.
     *
     * @return A string representation of the TreeNode.
     */
    override fun toString(): String =
        buildString {
            append(value.toString())
            if (children.isNotEmpty()) {
                append(" { ")
                append(children.joinToString(separator = ", ") { it.toString() })
                append(" }")
            }
        }

    /**
     * Flattens the tree into a sequence of pairs containing each node's value and its depth.
     *
     * @param depth The initial depth of the root node, default is 0.
     * @return A sequence of pairs where each pair consists of a node's value and its depth in the tree.
     */
    fun flattenTree(depth: Int = 0): Sequence<Pair<T, Int>> =
        sequence {
            yield(value to depth)
            children.asSequence().flatMap { it.flattenTree(depth + 1) }.forEach { yield(it) }
        }

    /**
     * Filters the tree based on a given predicate.
     *
     * @param predicate A function that evaluates each node's value to determine if it should be included.
     * @return A new TreeNode containing only the nodes that satisfy the predicate, or null if the root node does not satisfy the predicate.
     */
    fun filter(predicate: (T) -> Boolean): TreeNode<T>? {
        if (!predicate(this.value)) return null
        val filteredChildren = this.children.mapNotNull { it.filter(predicate) }
        return TreeNode(this.value, filteredChildren)
    }
}
