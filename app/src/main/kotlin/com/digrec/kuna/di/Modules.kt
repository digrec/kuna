package com.digrec.kuna.di

import com.digrec.kuna.BuildConfig
import com.digrec.kuna.core.data.repository.KunaRepositoryImpl
import com.digrec.kuna.core.domain.GetAllKunaUseCase
import com.digrec.kuna.core.domain.repository.KunaRepository
import com.digrec.kuna.core.ui.settings.SettingsViewModel
import com.digrec.kuna.feature.kunalist.KunaListViewModel
import kotlinx.coroutines.Dispatchers
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

    val repositoryModule = module {
        single<KunaRepository> {
            KunaRepositoryImpl(ioDispatcher = Dispatchers.IO)
        }
    }

    val useCaseModule = module {
        factory { GetAllKunaUseCase(kunaRepository = get()) }
    }

    val viewModelModule = module {
        viewModel { KunaListViewModel(getAllKuna = get()) }
        viewModel {
            SettingsViewModel(BuildConfig.VERSION_CODE, BuildConfig.VERSION_NAME)
        }
    }
}
