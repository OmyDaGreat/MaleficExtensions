package xyz.malefic.extensions.func

import androidx.compose.runtime.Composable

/**
 * Composes two functions, applying the receiver function first and then the [other] function.
 *
 * @param T The input type for the receiver function.
 * @param R The output type of the receiver function and input type for the other function.
 * @param V The output type of the other function.
 * @param other The function to apply after the receiver function.
 * @return A composed function that applies the receiver function followed by the other function.
 */
@Composable
infix fun <T, R, V> (@Composable (T) -> R).andThen(other: @Composable (R) -> V): @Composable (T) -> V = { other(this(it)) }

/**
 * Composes two functions, applying the [other] function first and then the receiver function.
 *
 * @param T The input type for the other function.
 * @param R The output type of the other function and input type for the receiver function.
 * @param V The output type of the receiver function.
 * @param other The function to apply before the receiver function.
 * @return A composed function that applies the other function followed by the receiver function.
 */
@Composable
infix fun <T, R, V> (@Composable (R) -> V).compose(other: @Composable (T) -> R): @Composable (T) -> V = { this(other(it)) }
