package xyz.malefic.ext.string

import kotlin.test.Test
import kotlin.test.assertEquals

class StringTest {
    @Test
    fun convertsEmptyStringToTitleCase() {
        assertEquals("", "".titlecase())
    }

    @Test
    fun convertsSingleWordToTitleCase() {
        assertEquals("Hello", "hello".titlecase())
    }

    @Test
    fun convertsMultipleWordsToTitleCase() {
        assertEquals("Hello World", "hello world".titlecase())
    }

    @Test
    fun doesNotCapitalizeWordsInSet() {
        assertEquals(
            "The Quick Brown Fox Jumps Over the Lazy Dog",
            "the quick brown fox jumps over the lazy dog".titlecase(),
        )
    }

    @Test
    fun capitalizesFirstWordEvenIfInSet() {
        assertEquals("The Quick Brown Fox", "the quick brown fox".titlecase())
    }

    @Test
    fun handlesMixedCaseInput() {
        assertEquals("Hello World", "hElLo wOrLd".titlecase())
    }

    @Test
    fun handlesWordsWithMixedCaseInSet() {
        assertEquals("The Quick Brown Fox", "tHe qUiCk bRoWn fOx".titlecase())
    }

    @Test
    fun handlesWordsWithPunctuation() {
        assertEquals("Hello, World!", "hello, world!".titlecase())
    }

    @Test
    fun handlesWordsWithNumbers() {
        assertEquals("Hello 123 World", "hello 123 world".titlecase())
    }

    @Test
    fun convertsEmptyStringToCamelCase() {
        assertEquals("", "".toCamelCase())
    }

    @Test
    fun convertsSingleWordToCamelCase() {
        assertEquals("Hello", "hello".toCamelCase())
    }

    @Test
    fun convertsMultipleWordsToCamelCase() {
        assertEquals("HelloWorld", "hello world".toCamelCase())
    }

    @Test
    fun handlesMixedCaseInputInCamelCase() {
        assertEquals("HelloWorld", "hElLo wOrLd".toCamelCase())
    }

    @Test
    fun handlesWordsWithPunctuationInCamelCase() {
        assertEquals("Hello,World!", "hello, world!".toCamelCase())
    }

    @Test
    fun handlesWordsWithNumbersInCamelCase() {
        assertEquals("Hello123World", "hello 123 world".toCamelCase())
    }

    @Test
    fun emptyStringIsNotEmail() {
        assertEquals(false, "".isEmail())
    }

    @Test
    fun validEmailIsEmail() {
        assertEquals(true, "test@example.com".isEmail())
    }

    @Test
    fun invalidEmailIsNotEmail() {
        assertEquals(false, "test@.com".isEmail())
    }

    @Test
    fun emptyStringIsNotNumeric() {
        assertEquals(false, "".isNumeric())
    }

    @Test
    fun numericStringIsNumeric() {
        assertEquals(true, "12345".isNumeric())
    }

    @Test
    fun nonNumericStringIsNotNumeric() {
        assertEquals(false, "123a45".isNumeric())
    }

    @Test
    fun emptyStringCapitalizesFirstLetter() {
        assertEquals("", "".capitalizeFirstLetter())
    }

    @Test
    fun singleLetterCapitalizesFirstLetter() {
        assertEquals("A", "a".capitalizeFirstLetter())
    }

    @Test
    fun multipleLettersCapitalizesFirstLetter() {
        assertEquals("Hello", "hello".capitalizeFirstLetter())
    }
}
