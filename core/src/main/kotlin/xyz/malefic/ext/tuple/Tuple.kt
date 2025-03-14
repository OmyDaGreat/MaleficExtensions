package xyz.malefic.ext.tuple

/**
 * Extension function to create a Triple from a Pair and an additional value.
 *
 * @param third the third value to be added to the Pair
 * @return a Triple containing the values from the Pair and the additional value
 */
infix fun <A, B, C> Pair<A, B>.to(third: C): Triple<A, B, C> = Triple(first, second, third)

/**
 * A data class representing a triple of values.
 *
 * @param A the type of the first value
 * @param B the type of the second value
 * @param C the type of the third value
 * @property first the first value
 * @property second the second value
 * @property third the third value
 */
data class Triple<A, B, C>(
    val first: A,
    val second: B,
    val third: C,
) {
    /**
     * Extension function to create a Quadruple from a Triple and an additional value.
     *
     * @param other the fourth value to be added to the Triple
     * @return a Quadruple containing the values from the Triple and the additional value
     */
    infix fun <D> to(other: D): Quadruple<A, B, C, D> = Quadruple(first, second, third, other)
}

/**
 * A data class representing a quadruple of values.
 *
 * @param A the type of the first value
 * @param B the type of the second value
 * @param C the type of the third value
 * @param D the type of the fourth value
 * @property first the first value
 * @property second the second value
 * @property third the third value
 * @property fourth the fourth value
 */
data class Quadruple<A, B, C, D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
) {
    /**
     * Extension function to create a Quintuple from a Quadruple and an additional value.
     *
     * @param other the fifth value to be added to the Quadruple
     * @return a Quintuple containing the values from the Quadruple and the additional value
     */
    infix fun <E> to(other: E): Quintuple<A, B, C, D, E> = Quintuple(first, second, third, fourth, other)
}

/**
 * A data class representing a quintuple of values.
 *
 * @param A the type of the first value
 * @param B the type of the second value
 * @param C the type of the third value
 * @param D the type of the fourth value
 * @param E the type of the fifth value
 * @property first the first value
 * @property second the second value
 * @property third the third value
 * @property fourth the fourth value
 * @property fifth the fifth value
 */
data class Quintuple<A, B, C, D, E>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E,
) {
    /**
     * Extension function to create a Sextuple from a Quintuple and an additional value.
     *
     * @param other the sixth value to be added to the Quintuple
     * @return a Sextuple containing the values from the Quintuple and the additional value
     */
    infix fun <F> to(other: F): Sextuple<A, B, C, D, E, F> = Sextuple(first, second, third, fourth, fifth, other)
}

/**
 * A data class representing a sextuple of values.
 *
 * @param A the type of the first value
 * @param B the type of the second value
 * @param C the type of the third value
 * @param D the type of the fourth value
 * @param E the type of the fifth value
 * @param F the type of the sixth value
 * @property first the first value
 * @property second the second value
 * @property third the third value
 * @property fourth the fourth value
 * @property fifth the fifth value
 * @property sixth the sixth value
 */
data class Sextuple<A, B, C, D, E, F>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E,
    val sixth: F,
) {
    /**
     * Extension function to create a Septuple from a Sextuple and an additional value.
     *
     * @param other the seventh value to be added to the Sextuple
     * @return a Septuple containing the values from the Sextuple and the additional value
     */
    infix fun <G> to(other: G): Septuple<A, B, C, D, E, F, G> = Septuple(first, second, third, fourth, fifth, sixth, other)
}

/**
 * A data class representing a septuple of values.
 *
 * @param A the type of the first value
 * @param B the type of the second value
 * @param C the type of the third value
 * @param D the type of the fourth value
 * @param E the type of the fifth value
 * @param F the type of the sixth value
 * @param G the type of the seventh value
 * @property first the first value
 * @property second the second value
 * @property third the third value
 * @property fourth the fourth value
 * @property fifth the fifth value
 * @property sixth the sixth value
 * @property seventh the seventh value
 */
data class Septuple<A, B, C, D, E, F, G>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E,
    val sixth: F,
    val seventh: G,
) {
    /**
     * Extension function to create an Octuple from a Septuple and an additional value.
     *
     * @param other the eighth value to be added to the Septuple
     * @return an Octuple containing the values from the Septuple and the additional value
     */
    infix fun <H> to(other: H): Octuple<A, B, C, D, E, F, G, H> = Octuple(first, second, third, fourth, fifth, sixth, seventh, other)
}

/**
 * A data class representing an octuple of values.
 *
 * @param A the type of the first value
 * @param B the type of the second value
 * @param C the type of the third value
 * @param D the type of the fourth value
 * @param E the type of the fifth value
 * @param F the type of the sixth value
 * @param G the type of the seventh value
 * @param H the type of the eighth value
 * @property first the first value
 * @property second the second value
 * @property third the third value
 * @property fourth the fourth value
 * @property fifth the fifth value
 * @property sixth the sixth value
 * @property seventh the seventh value
 * @property eighth the eighth value
 */
data class Octuple<A, B, C, D, E, F, G, H>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E,
    val sixth: F,
    val seventh: G,
    val eighth: H,
) {
    /**
     * Extension function to create a Nonuple from an Octuple and an additional value.
     *
     * @param other the ninth value to be added to the Octuple
     * @return a Nonuple containing the values from the Octuple and the additional value
     */
    infix fun <I> to(other: I): Nonuple<A, B, C, D, E, F, G, H, I> =
        Nonuple(first, second, third, fourth, fifth, sixth, seventh, eighth, other)
}

/**
 * A data class representing a nonuple of values.
 *
 * @param A the type of the first value
 * @param B the type of the second value
 * @param C the type of the third value
 * @param D the type of the fourth value
 * @param E the type of the fifth value
 * @param F the type of the sixth value
 * @param G the type of the seventh value
 * @param H the type of the eighth value
 * @param I the type of the ninth value
 * @property first the first value
 * @property second the second value
 * @property third the third value
 * @property fourth the fourth value
 * @property fifth the fifth value
 * @property sixth the sixth value
 * @property seventh the seventh value
 * @property eighth the eighth value
 * @property ninth the ninth value
 */
data class Nonuple<A, B, C, D, E, F, G, H, I>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E,
    val sixth: F,
    val seventh: G,
    val eighth: H,
    val ninth: I,
) {
    /**
     * Extension function to create a Decuple from a Nonuple and an additional value.
     *
     * @param other the tenth value to be added to the Nonuple
     * @return a Decuple containing the values from the Nonuple and the additional value
     */
    infix fun <J> to(other: J): Decuple<A, B, C, D, E, F, G, H, I, J> =
        Decuple(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, other)
}

/**
 * A data class representing a decuple of values.
 *
 * @param A the type of the first value
 * @param B the type of the second value
 * @param C the type of the third value
 * @param D the type of the fourth value
 * @param E the type of the fifth value
 * @param F the type of the sixth value
 * @param G the type of the seventh value
 * @param H the type of the eighth value
 * @param I the type of the ninth value
 * @param J the type of the tenth value
 * @property first the first value
 * @property second the second value
 * @property third the third value
 * @property fourth the fourth value
 * @property fifth the fifth value
 * @property sixth the sixth value
 * @property seventh the seventh value
 * @property eighth the eighth value
 * @property ninth the ninth value
 * @property tenth the tenth value
 */
data class Decuple<A, B, C, D, E, F, G, H, I, J>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E,
    val sixth: F,
    val seventh: G,
    val eighth: H,
    val ninth: I,
    val tenth: J,
)
