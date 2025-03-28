package xyz.malefic.ext.file

import xyz.malefic.ext.func.plus
import xyz.malefic.ext.func.repeat
import xyz.malefic.ext.func.times
import kotlin.test.Test

class OperatorTest {
    @Test
    fun test() {
        (
            {
                println("a")
            } + {
                println("b")
            } + {
                println("c")
            }
        )()
        3 * {
            println("a")
        }
        3 repeat {
            println("b")
        }
    }
}
