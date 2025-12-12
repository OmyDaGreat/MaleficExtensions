package xyz.malefic.extensions.application

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.MenuScope
import androidx.compose.ui.window.TrayState
import androidx.compose.ui.window.rememberTrayState

/**
 * Creates a tray with the specified properties.
 *
 * @receiver The application scope in which the tray icon is created.
 * @param block A lambda with receiver of type TrayFactory to configure the tray icon.
 */
@Composable
fun ApplicationScope.tray(block: Tray.() -> Unit) = Tray().apply(block).compose()()

/**
 * A factory class for creating and configuring a tray icon.
 *
 * @property icon The icon to be displayed in the tray. Defaults to a gray box.
 * @property state The state of the tray icon. Defaults to a remembered tray state.
 * @property tooltip The tooltip text to be displayed when hovering over the tray icon.
 * @property onAction The action to be performed when the tray icon is clicked.
 * @property menu A composable function to create the tray menu.
 */
class Tray(
    var icon: Painter = ColorPainter(Gray),
    var state: TrayState? = null,
    var tooltip: String? = null,
    var onAction: OnAction = {},
    var menu: Menu = {},
) {
    /**
     * Sets the action to be performed when the tray icon is clicked.
     *
     * @param block The lambda to be executed on tray icon click.
     */
    operator fun OnAction.invoke(block: OnAction) {
        onAction = block
    }

    /**
     * Sets the composable function to create the tray menu.
     *
     * @param block The composable function to create the tray menu.
     */
    operator fun Menu.invoke(block: Menu) {
        menu = block
    }

    /**
     * Composes the tray icon with the specified properties.
     *
     * @return A composable function that creates the tray icon.
     */
    @Composable
    fun compose(): @Composable ApplicationScope.() -> Unit =
        {
            Tray(icon, state ?: rememberTrayState(), tooltip, onAction, menu)
        }
}

typealias OnAction = () -> Unit
typealias Menu = @Composable MenuScope.() -> Unit
