package com.digrec.kuna.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.digrec.kuna.core.ui.component.KunaBackground
import com.digrec.kuna.core.ui.settings.navigateToSettings
import com.digrec.kuna.core.ui.settings.settingsScreen
import com.digrec.kuna.feature.kunalist.kunaListRoute
import com.digrec.kuna.feature.kunalist.kunaListScreen
import com.digrec.kuna.ui.theme.KunaTheme


/**
 * The 25 Kuna app.
 *
 * Created by Dejan Igrec
 */
@Composable
fun KunaApp() {
    KunaTheme {
        val navController = rememberNavController()

        KunaBackground(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = kunaListRoute,
            ) {
                kunaListScreen(
                    onClickSettings = { navController.navigateToSettings() },
                )
                settingsScreen(
                    onClickBack = { navController.navigateUp() },
                )
            }
        }
    }
}

@Preview
@Composable
fun KunaAppDefaultPreview() {
    KunaApp()
}
