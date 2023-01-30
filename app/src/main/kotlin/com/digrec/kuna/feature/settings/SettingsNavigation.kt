package com.digrec.kuna.feature.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.digrec.kuna.feature.settings.ui.SettingsRoute


/**
 * App settings route.
 */
const val settingsRoute = "settings_route"

fun NavController.navigateToSettings(
    navOptions: NavOptions = NavOptions.Builder().setLaunchSingleTop(true).build()
) {
    this.navigate(settingsRoute, navOptions)
}

fun NavGraphBuilder.settingsScreen(onClickBack: () -> Unit) {
    composable(route = settingsRoute) {
        SettingsRoute(onClickBack = onClickBack)
    }
}
