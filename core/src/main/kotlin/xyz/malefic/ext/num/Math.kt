package xyz.malefic.ext.num

import kotlin.math.sqrt

/**
 * Function to calculate the factorial of a number.
 *
 * @return The factorial of the number.
 * @receiver The number to calculate the factorial of.
 * @throws IllegalArgumentException if the number is negative.
 */
fun Int.factorial(): Int = this.`!`

/**
 * Extension property to calculate the factorial of an integer.
 *
 * @return The factorial of the integer.
 * @receiver The integer to calculate the factorial of.
 * @throws IllegalArgumentException if the integer is negative.
 */
@Suppress("ObjectPropertyName")
val Int.`!`: Int
    get() {
        require(this >= 0) { "Factorial is not defined for negative numbers" }

        tailrec fun tailrecHelper(
            n: Int,
            acc: Int,
        ): Int = if (n <= 1) acc else tailrecHelper(n - 1, n * acc)

        return tailrecHelper(this, 1)
    }

/**
 * Function to calculate the triangular value of a number.
 *
 * @return The triangular value of the number.
 * @receiver The number to calculate the triangular value of.
 * @throws IllegalArgumentException if the number is negative.
 */
fun Int.trinum(): Int {
    require(this >= 0) { "Triangular value is not defined for negative numbers" }
    return if (this == 0) 0 else this + (this - 1).trinum()
}

/**
 * Function to check if a number is prime.
 *
 * @return `true` if the number is prime, `false` otherwise.
 * @receiver The number to check.
 */
fun Int.isPrime(): Boolean {
    if (this <= 1) return false
    for (i in 2..sqrt(this.toDouble()).toInt()) {
        if (this % i == 0) return false
    }
    return true
}

/**
 * Function to find the greatest common divisor (GCD) of two numbers.
 *
 * @param a The first number.
 * @param b The second number.
 * @return The GCD of the two numbers.
 */
fun gcd(
    a: Int,
    b: Int,
): Int = if (b == 0) a else gcd(b, a % b)

/**
 * Function to generate a list of Fibonacci numbers up to a given count.
 *
 * @return A list of Fibonacci numbers.
 * @receiver The number of Fibonacci numbers to generate.
 * @throws IllegalArgumentException if the count is negative.
 */
fun Int.generateFibonacci(): List<Int> {
    require(this >= 0) { "Count must be non-negative" }
    val fibonacciList = mutableListOf<Int>()
    var a = 0
    var b = 1
    for (i in 0 until this) {
        fibonacciList.add(a)
        val temp = a
        a = b
        b += temp
    }
    return fibonacciList
}
