package xyz.malefic.ext.func

import androidx.compose.runtime.Composable

/**
 * Executes the function.
 */
@Composable
operator fun (@Composable () -> Unit).unaryPlus() = this()

/**
 * Combines two functions into one that executes both in sequence.
 *
 * @receiver A function that returns Unit.
 * @param other Another function that returns Unit.
 * @return A new function that executes the receiver function followed by the other function.
 */
@Composable
operator fun (@Composable () -> Unit).plus(other: @Composable () -> Unit) = @Composable { this().also { other() } }

/**
 * Repeats the execution of a function a specified number of times.
 *
 * @receiver The number of times to repeat the function execution.
 * @param block The function to be repeated.
 */
@Composable
operator fun Int.times(block: @Composable () -> Unit) = repeat(this) { block() }

/**
 * Repeats the execution of a function a specified number of times.
 *
 * @receiver The number of times to repeat the function execution.
 * @param block The function to be repeated.
 */
@Composable
infix fun Int.repeat(block: @Composable () -> Unit) = this * block
