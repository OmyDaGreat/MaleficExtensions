package xyz.malefic.extensions.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Extension function for Modifier to conditionally show or hide a composable.
 *
 * @param condition Boolean value. If true, the Modifier is applied; otherwise, a Modifier with size
 *   0.dp is applied.
 * @return A Modifier that is either the original or one with size 0.dp based on the condition.
 */
fun Modifier.showIf(condition: Boolean) = this.modifyIfElse(condition, Modifier, Modifier.size(0.dp))

/**
 * Extension function for Modifier to add a rounded background with padding.
 *
 * @param backgroundColor The color of the background.
 * @param cornerRadius The radius of the corners.
 * @param padding The padding to be applied.
 * @return A Modifier with a rounded background and padding.
 */
fun Modifier.roundedBackgroundWithPadding(
    backgroundColor: Color,
    cornerRadius: Dp,
    padding: Dp,
) = this.background(backgroundColor, shape = RoundedCornerShape(cornerRadius)).padding(padding)

/**
 * Extension function for Modifier to animate the visibility of a composable.
 *
 * @param isVisible Boolean value. If true, the composable is fully visible; otherwise, it is fully
 *   transparent.
 * @return A Modifier with the alpha value set based on the visibility.
 */
fun Modifier.animateVisibility(isVisible: Boolean) = this.alpha(1f.takeIf { isVisible } ?: 0f)

/**
 * Extension function for Modifier to conditionally append another Modifier.
 *
 * @param condition Boolean value. If true, the provided modifier is appended; otherwise, the
 *   original Modifier is returned.
 * @param modifier The Modifier to be appended if the condition is true.
 * @return A Modifier that is either the original or the provided modifier based on the condition.
 */
fun Modifier.modifyIf(
    condition: Boolean,
    modifier: Modifier,
) = this.modifyIfElse(condition, modifier, Modifier)

/**
 * Extension function for Modifier to conditionally append one of two Modifiers.
 *
 * @param condition Boolean value. If true, the provided modifier is appended; otherwise, the
 *   alternate modifier is appended.
 * @param modifier The Modifier to be appended if the condition is true.
 * @param alternateModifier The Modifier to be applied if the condition is false.
 * @return A Modifier that is either the provided modifier or the alternate modifier based on the
 *   condition.
 */
fun Modifier.modifyIfElse(
    condition: Boolean,
    modifier: Modifier,
    alternateModifier: Modifier,
) = this.then(modifier.takeIf { condition } ?: alternateModifier)

/**
 * Extension function for Modifier to make the composable clickable based on a condition.
 *
 * @param condition Boolean value. If true, the composable is clickable; otherwise, it is not
 *   clickable.
 * @param onClick Lambda function to be invoked when the modifier is clicked.
 * @return A Modifier that is conditionally clickable.
 */
fun Modifier.clickableIf(
    condition: Boolean,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
) = this.modifyIfElse(condition, Modifier.clickable(enabled, onClickLabel, role, onClick), Modifier)

/**
 * Operator function to combine two Modifiers using the `then` method.
 *
 * @param other The Modifier to be combined with the current Modifier.
 * @return A Modifier that is the result of combining the current Modifier with the provided Modifier.
 */
operator fun Modifier.plus(other: Modifier) = this.then(other)

/**
 * Operator function to apply a block of code to the current Modifier and return a new Modifier.
 *
 * @param block A lambda function with a receiver of type Modifier that returns a Modifier.
 * @return A Modifier that is the result of applying the block to the current Modifier.
 */
@Composable
operator fun Modifier.invoke(block: @Composable Modifier.() -> Modifier) = this.block()
