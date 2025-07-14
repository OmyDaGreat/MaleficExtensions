package xyz.malefic.extensions.func

/**
 * Executes the receiver function safely, returning null if an exception occurs.
 *
 * @param T The input type for the function.
 * @param R The output type of the function.
 * @param input The input value for the function.
 * @return The result of the function or null if an exception occurred.
 */
fun <T, R> ((T) -> R).safely(input: T): R? =
    try {
        this(input)
    } catch (e: Exception) {
        null
    }

/**
 * Executes the receiver function safely, returning a default value if an exception occurs.
 *
 * @param T The input type for the function.
 * @param R The output type of the function.
 * @param input The input value for the function.
 * @param default The default value to return in case of an exception.
 * @return The result of the function or the default value if an exception occurred.
 */
fun <T, R> ((T) -> R).safelyOr(
    input: T,
    default: R,
): R =
    try {
        this(input)
    } catch (e: Exception) {
        default
    }
