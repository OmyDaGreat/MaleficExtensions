package xyz.malefic.ext.any

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AnyTest {
    @Test
    fun resolveNullReturnsIfNotNullWhenReceiverIsNotNull() {
        val receiver: String? = "value"
        assertEquals("notNull", receiver.resolveNull("notNull", "null"))
    }

    @Test
    fun resolveNullReturnsIfNullWhenReceiverIsNull() {
        val receiver: String? = null
        assertEquals("null", receiver.resolveNull("notNull", "null"))
    }

    @Test
    fun resolveNullReturnsIfNotNullWhenReceiverIsNotNullForInt() {
        val receiver: Int? = 42
        assertEquals(100, receiver.resolveNull(100, 0))
    }

    @Test
    fun resolveNullReturnsIfNullWhenReceiverIsNullForInt() {
        val receiver: Int? = null
        assertEquals(0, receiver.resolveNull(100, 0))
    }

    @Test
    fun resolveNullReturnsIfNotNullWhenReceiverIsNotNullForCustomObject() {
        data class CustomObject(
            val value: String,
        )
        val receiver: CustomObject? = CustomObject("value")
        assertEquals(CustomObject("notNull"), receiver.resolveNull(CustomObject("notNull"), CustomObject("null")))
    }

    @Test
    fun resolveNullReturnsIfNullWhenReceiverIsNullForCustomObject() {
        data class CustomObject(
            val value: String,
        )
        val receiver: CustomObject? = null
        assertEquals(CustomObject("null"), receiver.resolveNull(CustomObject("notNull"), CustomObject("null")))
    }
}
