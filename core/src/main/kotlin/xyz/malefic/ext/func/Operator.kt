package xyz.malefic.ext.func

/**
 * Executes the function.
 */
operator fun (() -> Unit).unaryPlus() = this()

/**
 * Combines two functions into one that executes both in sequence.
 *
 * @receiver A function that returns Unit.
 * @param other Another function that returns Unit.
 * @return A new function that executes the receiver function followed by the other function.
 */
operator fun (() -> Unit).plus(other: () -> Unit) = { this().also { other() } }

/**
 * Repeats the execution of a function a specified number of times.
 *
 * @receiver The number of times to repeat the function execution.
 * @param block The function to be repeated.
 */
operator fun Int.times(block: () -> Unit) = repeat(this) { block() }

/**
 * Repeats the execution of a function a specified number of times.
 *
 * @receiver The number of times to repeat the function execution.
 * @param block The function to be repeated.
 */
infix fun Int.repeat(block: () -> Unit) = this * block
