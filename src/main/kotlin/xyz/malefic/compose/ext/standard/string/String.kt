package xyz.malefic.compose.ext.standard.string

/**
 * Extension function for the String class that toggles the string value between the provided
 * `first` and `second` strings.
 *
 * @param first The first string to compare with.
 * @param second The second string to compare with.
 * @return The `second` string if the current string is equal to `first`, otherwise returns the
 *   `first` string.
 */
fun String.either(
    first: String,
    second: String,
) = if (this == first) second else first

/**
 * Extension function for the String class that checks if the string contains any of the substrings
 * from a given list.
 *
 * @param substrings The list of substrings to check for.
 * @return `true` if the string contains any of the substrings, otherwise `false`.
 */
@SafeVarargs
fun String.containsAny(vararg substrings: String) = substrings.any { this.contains(it) }

/**
 * Extension function for the String class that converts the string to its hexadecimal
 * representation.
 *
 * @return The hexadecimal representation of the string.
 */
fun String.toHex() = this.toByteArray().joinToString("") { "%02x".format(it) }

/**
 * Extension function for the String class that converts a hexadecimal string back to its original
 * string representation.
 *
 * @return The original string representation of the hexadecimal string.
 */
fun String.fromHex(): String {
    val output = StringBuilder()
    for (i in this.indices step 2) {
        val str = this.substring(i, i + 2)
        output.append(str.toInt(16).toChar())
    }
    return output.toString()
}
