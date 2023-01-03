package com.digrec.kuna.core.ui.kunaList

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


/**
 * Main Kuna list route.
 */
const val kunaListRoute = "kuna_list_route"

fun NavController.navigateToKunaList(navOptions: NavOptions? = null) {
    this.navigate(kunaListRoute, navOptions)
}

fun NavGraphBuilder.kunaListScreen(onClickSettings: () -> Unit) {
    composable(route = kunaListRoute) {
        KunaListRoute(onClickSettings = onClickSettings)
    }
}
