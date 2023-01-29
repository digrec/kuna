package com.digrec.kuna.feature.kunalist

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.digrec.kuna.feature.kunalist.ui.KunaListRoute


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
