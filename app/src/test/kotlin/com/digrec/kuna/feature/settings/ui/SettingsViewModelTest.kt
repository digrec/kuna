package com.digrec.kuna.feature.settings.ui

import org.junit.Assert.assertEquals
import org.junit.Test

/** Unit tests for [SettingsViewModel]. */
class SettingsViewModelTest {

    @Test
    fun `when init then it should format app version correctly`() {
        val versionCode = 123
        val versionName = "1.2.3"
        val viewModel = SettingsViewModel(versionCode, versionName)

        assertEquals("v1.2.3+123", viewModel.appVersion)
    }
}
