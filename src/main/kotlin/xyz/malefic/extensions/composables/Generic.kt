package xyz.malefic.extensions.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Adds a space (Spacer) with the specified height and width to a Composable function.
 *
 * @param height The height of the space in dp. Default is 8 dp.
 * @param width The optional width of the space in dp. If null, only the height will be applied.
 * @receiver A Composable function to which the space will be added.
 */
@Composable
fun @Composable () -> Unit.space(height: Int? = 8, width: Int? = null) {
  this()
  Spacer(
    Modifier.let { if (height != null) it.height(height.dp) else it }
      .let { if (width != null) it.width(width.dp) else it }
  )
}

/**
 * Adds a space (Spacer) with the specified height to a Composable function.
 *
 * @param height The height of the space in dp.
 * @receiver A Composable function to which the space will be added.
 */
@Composable
infix fun @Composable () -> Unit.space(height: Int) {
  this()
  Spacer(modifier = Modifier.height(height.dp))
}
