package xyz.malefic.ext.any

/**
 * Resolves the nullable receiver to a non-null value.
 *
 * @param ifNotNull The value to return if the receiver is not null.
 * @param ifNull The value to return if the receiver is null.
 * @return `ifNotNull` if the receiver is not null, otherwise `ifNull`.
 */
fun <T> T?.resolveNull(
    ifNotNull: T,
    ifNull: T,
): T = this?.let { ifNotNull } ?: ifNull
