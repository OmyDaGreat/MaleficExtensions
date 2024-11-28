package xyz.malefic.extensions.precompose

import moe.tlaster.precompose.navigation.Navigator

/**
 * Navigates to the specified route using the [Navigator]. Recommended to be used in infix form on a
 * variable named `navi` for best look.
 *
 * @param route The route to navigate to.
 */
infix fun Navigator.gate(route: String) = this.navigate(route)
