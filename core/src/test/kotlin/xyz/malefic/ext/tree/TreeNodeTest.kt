package xyz.malefic.ext.tree

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class TreeNodeTest {
    // Create TreeNode with value and empty children list
    @Test
    fun create_tree_node_with_value_and_empty_children() {
        val node = TreeNode("root")

        assertEquals("root", node.value)
        assertTrue(node.children.isEmpty())
    }

    // Create TreeNode with value and non-empty children list
    @Test
    fun create_tree_node_with_value_and_children() {
        val child1 = TreeNode("child1")
        val child2 = TreeNode("child2")
        val root = TreeNode("root", listOf(child1, child2))

        assertEquals("root", root.value)
        assertEquals(2, root.children.size)
        assertEquals("child1", root.children[0].value)
        assertEquals("child2", root.children[1].value)
    }

    // Create single node using companion object method
    @Test
    fun create_single_node_using_companion_method() {
        val node = TreeNode.createSingleNode("test")

        assertEquals("test", node.value)
        assertTrue(node.children.isEmpty())
    }

    // Convert simple TreeNode to string representation
    @Test
    fun convert_simple_node_to_string() {
        val node = TreeNode("root")

        assertEquals("root", node.toString())
    }

    // Convert complex TreeNode with children to string representation
    @Test
    fun convert_complex_node_to_string() {
        val child1 = TreeNode("child1")
        val child2 = TreeNode("child2")
        val root = TreeNode("root", listOf(child1, child2))

        assertEquals("root { child1, child2 }", root.toString())
    }

    // Create TreeNode with null value
    @Test
    fun create_tree_node_with_null_value() {
        val node = TreeNode<String?>(null)

        assertNull(node.value)
        assertTrue(node.children.isEmpty())
    }

    // Filter tree with predicate that matches no nodes
    @Test
    fun filter_tree_with_no_matches() {
        val root = TreeNode("root", listOf(TreeNode("child")))

        val filtered = root.filter { false }

        assertNull(filtered)
    }

    // Filter tree with predicate that matches only leaf nodes
    @Test
    fun filter_tree_matches_only_leaves() {
        val leaf1 = TreeNode("leaf1")
        val leaf2 = TreeNode("leaf2")
        val parent = TreeNode("parent", listOf(leaf1, leaf2))
        val root = TreeNode("root", listOf(parent))

        val filtered = root.filter { it.startsWith("leaf") }

        assertNull(filtered)
    }

    // Flatten empty tree (single node without children)
    @Test
    fun flatten_single_node_tree() {
        val node = TreeNode("root")

        val flattened = node.flattenTree().toList()

        assertEquals(1, flattened.size)
        assertEquals(Pair("root", 0), flattened[0])
    }

    // Convert deeply nested tree to string representation
    @Test
    fun convert_deeply_nested_tree_to_string() {
        val leaf = TreeNode("leaf")
        val middle = TreeNode("middle", listOf(leaf))
        val root = TreeNode("root", listOf(middle))

        assertEquals("root { middle { leaf } }", root.toString())
    }
}
