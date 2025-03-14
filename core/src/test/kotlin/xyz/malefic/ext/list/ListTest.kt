package xyz.malefic.ext.list

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ListTest {
    @Test
    fun getReturnsElementAtIndex() {
        val list = listOf("a", "b", "c")
        assertEquals("b", list[1, "default"])
    }

    @Test
    fun getReturnsDefaultWhenIndexOutOfBounds() {
        val list = listOf("a", "b", "c")
        assertEquals("default", list[5, "default"])
    }

    @Test
    fun getReturnsDefaultWhenElementIsNull() {
        val list = listOf("a", null, "c")
        assertEquals("default", list[1, "default"])
    }

    @Test
    fun getReturnsElementWhenIndexIsValidAndElementIsNotNull() {
        val list = listOf("a", "b", "c")
        assertEquals("c", list[2, "default"])
    }

    @Test
    fun getReturnsDefaultWhenListIsEmpty() {
        val list = emptyList<String?>()
        assertEquals("default", list[0, "default"])
    }
}
