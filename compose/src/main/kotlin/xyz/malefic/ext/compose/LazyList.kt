package xyz.malefic.ext.compose

import androidx.compose.foundation.lazy.LazyListState

/**
 * Scrolls the LazyList to the top.
 *
 * This function scrolls the LazyList to the first item (index 0).
 */
suspend fun LazyListState.scrollToTop() = this scroll 0

/**
 * Scrolls the LazyList to the specified index.
 *
 * @param index The index of the item to scroll to.
 */
suspend infix fun LazyListState.scroll(index: Int) = animateScrollToItem(index)
