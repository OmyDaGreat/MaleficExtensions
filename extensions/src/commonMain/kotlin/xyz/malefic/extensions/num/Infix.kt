package xyz.malefic.extensions.num

import kotlin.math.pow

/**
 * Computes the factorial of a non-negative integer.
 *
 * @return The factorial of the integer.
 * @receiver The integer for which the factorial is to be computed. Must be non-negative.
 * @throws IllegalArgumentException if the integer is negative.
 */
operator fun Int.not(): Int = this.`!`

/**
 * Returns the maximum of two comparable values.
 *
 * @param T The type of the values, which must implement the Comparable interface.
 * @param other The second value to compare.
 * @return The maximum of the two values.
 * @receiver The first value to compare.
 */
infix fun <T : Comparable<T>> T.max(other: T): T = if (this > other) this else other

/**
 * Returns the minimum of two comparable values.
 *
 * @param T The type of the values, which must implement the Comparable interface.
 * @param other The second value to compare.
 * @return The minimum of the two values.
 * @receiver The first value to compare.
 */
infix fun <T : Comparable<T>> T.min(other: T): T = if (this < other) this else other

/**
 * Computes the power of a number.
 *
 * @param T The type of the number, which must implement the Number interface.
 * @param exponent The exponent to which the number is to be raised.
 * @return The result of raising the number to the given exponent.
 * @receiver The base number to be raised to the power of the exponent.
 */
infix fun <T : Number> T.pow(exponent: Int) = this.toDouble().pow(exponent)

/**
 * Computes the greatest common divisor (GCD) of two integers using the Euclidean algorithm.
 *
 * @param other The second integer.
 * @return The GCD of the two integers.
 * @receiver The first integer.
 */
infix fun Int.gcd(other: Int) = gcd(this, other)

/**
 * Computes the least common multiple (LCM) of two integers.
 *
 * @param other The second integer.
 * @return The LCM of the two integers.
 * @receiver The first integer.
 */
infix fun Int.lcm(other: Int): Int = (this * other) / (this gcd other)

/**
 * Checks if the number is divisible by another number.
 *
 * @param other The divisor.
 * @return True if the number is divisible by the other number, false otherwise.
 * @receiver The dividend.
 */
infix fun Int.isDivisibleBy(other: Int): Boolean = this % other == 0
