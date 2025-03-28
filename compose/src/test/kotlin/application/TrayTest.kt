package application

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.window.TrayState
import org.junit.Rule
import org.junit.Test
import xyz.malefic.ext.application.Menu
import xyz.malefic.ext.application.OnAction
import xyz.malefic.ext.application.Tray
import xyz.malefic.ext.application.item
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertSame

class TrayTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `Tray should have correct default values`() {
        // Arrange & Act
        val tray = Tray()

        // Assert
        assertIs<ColorPainter>(tray.icon)
        assertEquals(Color.Gray, (tray.icon as ColorPainter).color)
        assertEquals(null, tray.state)
        assertEquals(null, tray.tooltip)
    }

    @Test
    fun `Tray should be initialized with custom values`() {
        // Arrange
        val customIcon = ColorPainter(Color.Red)
        val customState = TrayState()
        val customTooltip = "Test Tooltip"
        val customAction: OnAction = { println("Custom action") }
        val customMenu: Menu = { /* Test menu */ }

        // Act
        val tray =
            Tray(
                icon = customIcon,
                state = customState,
                tooltip = customTooltip,
                onAction = customAction,
                menu = customMenu,
            )

        // Assert
        assertSame(customIcon, tray.icon)
        assertSame(customState, tray.state)
        assertEquals(customTooltip, tray.tooltip)
        assertSame(customAction, tray.onAction)
        assertSame(customMenu, tray.menu)
    }

    @Test
    fun `OnAction invoke operator should set onAction property`() {
        // Arrange
        val tray = Tray()
        val newAction: OnAction = { println("New action") }

        // Act
        with(tray) {
            onAction.invoke(newAction)
        }

        // Assert
        assertSame(newAction, tray.onAction)
    }

    @Test
    fun `Menu invoke operator should set menu property`() {
        // Arrange
        val tray = Tray()
        val newMenu: Menu = { /* New menu */ }

        // Act
        with(tray) {
            menu.invoke(newMenu)
        }

        // Assert
        assertSame(newMenu, tray.menu)
    }

    @Test
    fun `run tray composable`() {
        // Set up a test environment for the tray
        composeRule.mainClock.autoAdvance = true

        // Test the Tray configuration
        val tray =
            Tray().apply {
                icon = ColorPainter(Color.Red)
                tooltip = "Test Tooltip"
                onAction {
                    println("Action performed")
                }
                menu {
                    item {
                        text = "Item 1"
                        onClick {
                            println("Item 1 clicked")
                        }
                    }
                }
            }

        // Assert the properties were set correctly
        assertIs<ColorPainter>(tray.icon)
        assertEquals(Color.Red, (tray.icon as ColorPainter).color)
        assertEquals("Test Tooltip", tray.tooltip)
    }
}
