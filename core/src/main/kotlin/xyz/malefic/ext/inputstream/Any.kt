package xyz.malefic.ext.inputstream

import java.io.InputStream

/**
 * Function to get an InputStream for a given resource string.
 *
 * @param string the resource path as a string
 * @return InputStream for the resource, or null if the resource is not found
 */
fun Any.grass(string: String): InputStream? = this::class.java.getResourceAsStream(string)
