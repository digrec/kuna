package com.digrec.kuna.feature.settings.ui

import androidx.lifecycle.ViewModel


/**
 * Created by Dejan Igrec
 */
class SettingsViewModel(versionCode: Int, versionName: String) : ViewModel() {
    val appVersion = "v$versionName+$versionCode"
}
