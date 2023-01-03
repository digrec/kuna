package com.digrec.kuna.di

import com.digrec.kuna.BuildConfig
import com.digrec.kuna.core.ui.kunaList.KunaListViewModel
import com.digrec.kuna.core.ui.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * All modules used in dependency injection.
 *
 * Created by Dejan Igrec
 */
object Modules {

    val appModule = module {

    }

    val viewModelModule = module {
        viewModel { KunaListViewModel() }
        viewModel {
            SettingsViewModel(BuildConfig.VERSION_CODE, BuildConfig.VERSION_NAME)
        }
    }
}
