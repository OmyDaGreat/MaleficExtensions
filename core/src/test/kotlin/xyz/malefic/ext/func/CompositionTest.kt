package xyz.malefic.ext.func

import kotlin.test.Test
import kotlin.test.assertEquals

class CompositionTest {
    @Test
    fun andThenAppliesFunctionsInOrder() {
        val multiplyBy2: (Int) -> Int = { it * 2 }
        val add3: (Int) -> Int = { it + 3 }
        val composedFunction = multiplyBy2 andThen add3

        assertEquals(7, composedFunction(2))
        assertEquals(11, composedFunction(4))
    }

    @Test
    fun composeAppliesFunctionsInReverseOrder() {
        val multiplyBy2: (Int) -> Int = { it * 2 }
        val add3: (Int) -> Int = { it + 3 }
        val composedFunction = add3 compose multiplyBy2

        assertEquals(7, composedFunction(2))
        assertEquals(11, composedFunction(4))
    }

    @Test
    fun andThenWithDifferentTypes() {
        val toString: (Int) -> String = { it.toString() }
        val addExclamation: (String) -> String = { "$it!" }
        val composedFunction = toString andThen addExclamation

        assertEquals("2!", composedFunction(2))
        assertEquals("4!", composedFunction(4))
    }

    @Test
    fun composeWithDifferentTypes() {
        val toString: (Int) -> String = { it.toString() }
        val addExclamation: (String) -> String = { "$it!" }
        val composedFunction = addExclamation compose toString

        assertEquals("2!", composedFunction(2))
        assertEquals("4!", composedFunction(4))
    }
}
