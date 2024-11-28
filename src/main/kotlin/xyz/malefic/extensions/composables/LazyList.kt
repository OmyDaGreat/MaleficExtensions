package xyz.malefic.extensions.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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

/**
 * Adds a grid of items to the LazyList.
 *
 * @param T The type of the items in the list.
 * @param data The list of items to be displayed in the grid.
 * @param columnCount The number of columns in the grid.
 * @param modifier The modifier to be applied to the Row.
 * @param horizontalArrangement The horizontal arrangement of the items in the row.
 * @param itemContent The composable function that defines the content of each item.
 */
fun <T> LazyListScope.gridItems(
  data: List<T>,
  columnCount: Int,
  modifier: Modifier,
  horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
  itemContent: @Composable BoxScope.(T) -> Unit,
) {
  val size = data.count()
  val rows = if (size == 0) 0 else 1 + (size - 1) / columnCount
  items(rows, key = { it.hashCode() }) { rowIndex ->
    Row(horizontalArrangement = horizontalArrangement, modifier = modifier) {
      for (columnIndex in 0 until columnCount) {
        val itemIndex = rowIndex * columnCount + columnIndex
        if (itemIndex < size) {
          Box(modifier = Modifier.weight(1F, fill = true), propagateMinConstraints = true) {
            itemContent(data[itemIndex])
          }
        } else {
          Spacer(Modifier.weight(1F, fill = true))
        }
      }
    }
  }
}
