package xyz.malefic.ext.tuple

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TupleTest {
    @Test
    fun pairToTripleCreatesTripleWithCorrectValues() {
        val pair = Pair("foo", 42)
        val triple = pair to true

        assertEquals("foo", triple.first)
        assertEquals(42, triple.second)
        assertEquals(true, triple.third)
    }

    @Test
    fun tripleToQuadrupleCreatesQuadrupleWithCorrectValues() {
        val triple = Triple("foo", 42, true)
        val quadruple = triple to 3.14

        assertEquals("foo", quadruple.first)
        assertEquals(42, quadruple.second)
        assertEquals(true, quadruple.third)
        assertEquals(3.14, quadruple.fourth)
    }

    @Test
    fun quadrupleToQuintupleCreatesQuintupleWithCorrectValues() {
        val quadruple = Quadruple("foo", 42, true, 3.14)
        val quintuple = quadruple to 'c'

        assertEquals("foo", quintuple.first)
        assertEquals(42, quintuple.second)
        assertEquals(true, quintuple.third)
        assertEquals(3.14, quintuple.fourth)
        assertEquals('c', quintuple.fifth)
    }

    @Test
    fun quintupleToSextupleCreatesSextupleWithCorrectValues() {
        val quintuple = Quintuple("foo", 42, true, 3.14, 'c')
        val sextuple = quintuple to "bar"

        assertEquals("foo", sextuple.first)
        assertEquals(42, sextuple.second)
        assertEquals(true, sextuple.third)
        assertEquals(3.14, sextuple.fourth)
        assertEquals('c', sextuple.fifth)
        assertEquals("bar", sextuple.sixth)
    }

    @Test
    fun sextupleToSeptupleCreatesSeptupleWithCorrectValues() {
        val sextuple = Sextuple("foo", 42, true, 3.14, 'c', "bar")
        val septuple = sextuple to 100L

        assertEquals("foo", septuple.first)
        assertEquals(42, septuple.second)
        assertEquals(true, septuple.third)
        assertEquals(3.14, septuple.fourth)
        assertEquals('c', septuple.fifth)
        assertEquals("bar", septuple.sixth)
        assertEquals(100L, septuple.seventh)
    }

    @Test
    fun septupleToOctupleCreatesOctupleWithCorrectValues() {
        val septuple = Septuple("foo", 42, true, 3.14, 'c', "bar", 100L)
        val octuple = septuple to listOf(1, 2, 3)

        assertEquals("foo", octuple.first)
        assertEquals(42, octuple.second)
        assertEquals(true, octuple.third)
        assertEquals(3.14, octuple.fourth)
        assertEquals('c', octuple.fifth)
        assertEquals("bar", octuple.sixth)
        assertEquals(100L, octuple.seventh)
        assertEquals(listOf(1, 2, 3), octuple.eighth)
    }

    @Test
    fun octupleToNonupleCreatesNonupleWithCorrectValues() {
        val octuple = Octuple("foo", 42, true, 3.14, 'c', "bar", 100L, listOf(1, 2, 3))
        val nonuple = octuple to setOf("a", "b")

        assertEquals("foo", nonuple.first)
        assertEquals(42, nonuple.second)
        assertEquals(true, nonuple.third)
        assertEquals(3.14, nonuple.fourth)
        assertEquals('c', nonuple.fifth)
        assertEquals("bar", nonuple.sixth)
        assertEquals(100L, nonuple.seventh)
        assertEquals(listOf(1, 2, 3), nonuple.eighth)
        assertEquals(setOf("a", "b"), nonuple.ninth)
    }

    @Test
    fun nonupleToDecupleCreatesDecupleWithCorrectValues() {
        val nonuple = Nonuple("foo", 42, true, 3.14, 'c', "bar", 100L, listOf(1, 2, 3), setOf("a", "b"))
        val decuple = nonuple to mapOf(1 to "one")

        assertEquals("foo", decuple.first)
        assertEquals(42, decuple.second)
        assertEquals(true, decuple.third)
        assertEquals(3.14, decuple.fourth)
        assertEquals('c', decuple.fifth)
        assertEquals("bar", decuple.sixth)
        assertEquals(100L, decuple.seventh)
        assertEquals(listOf(1, 2, 3), decuple.eighth)
        assertEquals(setOf("a", "b"), decuple.ninth)
        assertEquals(mapOf(1 to "one"), decuple.tenth)
    }

    @Test
    fun chainedToFunctionsCreateCorrectTuple() {
        val decuple =
            "foo" to 42 to true to 3.14 to 'c' to "bar" to 100L to
                listOf(1, 2, 3) to setOf("a", "b") to mapOf(1 to "one")

        assertEquals("foo", decuple.first)
        assertEquals(42, decuple.second)
        assertEquals(true, decuple.third)
        assertEquals(3.14, decuple.fourth)
        assertEquals('c', decuple.fifth)
        assertEquals("bar", decuple.sixth)
        assertEquals(100L, decuple.seventh)
        assertEquals(listOf(1, 2, 3), decuple.eighth)
        assertEquals(setOf("a", "b"), decuple.ninth)
        assertEquals(mapOf(1 to "one"), decuple.tenth)
    }

    @Test
    fun tupleCanContainNullValues() {
        val triple = "foo" to null to null

        assertEquals("foo", triple.first)
        assertEquals(null, triple.second)
        assertEquals(null, triple.third)
    }

    @Test
    fun tupleCanContainComplexObjects() {
        data class CustomObject(
            val value: String,
        )
        val obj1 = CustomObject("object1")
        val obj2 = CustomObject("object2")

        val triple = obj1 to obj2 to "third"

        assertEquals(obj1, triple.first)
        assertEquals(obj2, triple.second)
        assertEquals("third", triple.third)
    }
}
