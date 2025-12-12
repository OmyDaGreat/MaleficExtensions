package xyz.malefic.extensions.application

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.window.MenuScope

@Composable
fun MenuScope.item(block: ItemBuilder.() -> Unit) = ItemBuilder().apply(block).compose(this)

/**
 * Represents an item with various properties and actions.
 *
 * @property text The text to be displayed for the item. Defaults to "Empty Item".
 * @property icon The icon to be displayed for the item. Defaults to null.
 * @property enabled Indicates whether the item is enabled. Defaults to true.
 * @property mnemonic The mnemonic character for the item. Defaults to null.
 * @property shortcut The keyboard shortcut for the item. Defaults to null.
 * @property onClick The action to be performed when the item is clicked. Defaults to an empty action.
 */
class ItemBuilder(
    var text: String = "Empty Item",
    var icon: Painter? = null,
    var enabled: Boolean = true,
    var mnemonic: Char? = null,
    var shortcut: KeyShortcut? = null,
    var onClick: OnClick = {},
) {
    /**
     * Sets the onClick action for the item.
     *
     * @param block The action to be performed when the item is clicked.
     */
    operator fun OnClick.invoke(block: OnClick) {
        onClick = block
    }

    /**
     * Composes the item with the specified properties.
     *
     * @return A composable function that creates the item.
     */
    @Composable
    fun compose(scope: MenuScope) {
        scope.Item(text, icon, enabled, mnemonic, shortcut, onClick)
    }
}

typealias OnClick = () -> Unit
