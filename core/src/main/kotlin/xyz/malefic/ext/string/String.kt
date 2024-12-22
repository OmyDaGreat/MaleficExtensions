package xyz.malefic.ext.string

import java.util.Locale

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

/**
 * A set of words that should not be capitalized in the title case.
 */
private val WORDS_NOT_TO_CAPITALIZE =
    setOf(
        "a",
        "an",
        "the",
        "and",
        "but",
        "or",
        "for",
        "nor",
        "on",
        "at",
        "to",
        "from",
        "by",
        "in",
        "of",
    )

/**
 * Extension function to convert a string to title case.
 *
 * Words in the `WORDS_NOT_TO_CAPITALIZE` set will remain in lowercase unless they are the first word.
 *
 * @receiver The string to be converted to title case.
 * @return The string in title case.
 */
fun String.titlecase(): String {
    if (isEmpty()) return this

    return split(" ")
        .mapIndexed { index, word ->
            if (index == 0 || !WORDS_NOT_TO_CAPITALIZE.contains(word.lowercase(Locale.getDefault()))) {
                word.substring(0, 1).uppercase() + word.substring(1).lowercase()
            } else {
                word.lowercase()
            }
        }.joinToString(" ")
}

/**
 * Extension function to check if a string is a valid email address.
 *
 * @receiver The string to be checked.
 * @return `true` if the string matches the email pattern, `false` otherwise.
 */
fun String.isEmail(): Boolean = matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex())

/**
 * Extension function to check if a string is numeric.
 *
 * @receiver The string to be checked.
 * @return `true` if the string contains only digits, `false` otherwise.
 */
fun String.isNumeric(): Boolean = matches("\\d+".toRegex())

/**
 * Extension function to capitalize the first letter of a string.
 *
 * @receiver The string to be modified.
 * @return The string with the first letter capitalized.
 */
fun String.capitalizeFirstLetter(): String = replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

/**
 * Extension function to convert a string with spaces to camel case.
 *
 * @receiver The string to be converted.
 * @return The string in camel case.
 */
fun String.toCamelCase(): String = split("\\s+".toRegex()).joinToString("") { it.lowercase().capitalizeFirstLetter() }

// On 8/12/2024, 9:47 AM, Theodore Vang helped Owen Tang with math :D
