package xyz.malefic.extensions.standard.string

/**
 * Extension function for the String class that toggles the string value between the provided
 * `first` and `second` strings.
 *
 * @param first The first string to compare with.
 * @param second The second string to compare with.
 * @return The `second` string if the current string is equal to `first`, otherwise returns the
 *   `first` string.
 */
fun String.either(first: String, second: String): String {
  return if (this == first) second else first
}
