package xyz.malefic.ext.func

/**
 * Composes two functions, applying the receiver function first and then the [other] function.
 *
 * @param T The input type for the receiver function.
 * @param R The output type of the receiver function and input type for the other function.
 * @param V The output type of the other function.
 * @param other The function to apply after the receiver function.
 * @return A composed function that applies the receiver function followed by the other function.
 */
infix fun <T, R, V> ((T) -> R).andThen(other: (R) -> V): (T) -> V = { other(this(it)) }

/**
 * Composes two functions, applying the [other] function first and then the receiver function.
 *
 * @param T The input type for the other function.
 * @param R The output type of the other function and input type for the receiver function.
 * @param V The output type of the receiver function.
 * @param other The function to apply before the receiver function.
 * @return A composed function that applies the other function followed by the receiver function.
 */
infix fun <T, R, V> ((R) -> V).compose(other: (T) -> R): (T) -> V = { this(other(it)) }
