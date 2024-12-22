package xyz.malefic.ext.bool

import kotlin.test.Test
import kotlin.test.assertEquals

class BoolHandlingTest {
    @Test
    fun runT_executesTrueFunction_whenBooleanIsTrue() {
        var executed = false

        true runT {
            executed = true
        } runF {
            executed = false
        }

        assertEquals(true, executed)
    }

    @Test
    fun runT_doesNotExecuteTrueFunction_whenBooleanIsFalse() {
        var executed = false
        false.runT(t = { executed = true })
        assertEquals(false, executed)
    }

    @Test
    fun runF_executesFalseFunction_whenBooleanIsFalse() {
        var executed = false
        false.runF(f = { executed = true })
        assertEquals(true, executed)
    }

    @Test
    fun runF_doesNotExecuteFalseFunction_whenBooleanIsTrue() {
        var executed = false
        true.runF(f = { executed = true })
        assertEquals(false, executed)
    }
}
