package com.digrec.kuna.di

import com.digrec.kuna.BuildConfig
import com.digrec.kuna.core.data.repository.KunaRepositoryImpl
import com.digrec.kuna.core.domain.GetAllKunaUseCase
import com.digrec.kuna.core.domain.repository.KunaRepository
import com.digrec.kuna.feature.kunalist.ui.KunaListViewModel
import com.digrec.kuna.feature.settings.ui.SettingsViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import timber.log.Timber


/**
 * All modules used in dependency injection.
 *
 * Created by Dejan Igrec
 */
object Modules {

    val appModule = module {

    }

    val repositoryModule = module {
        single {
            HttpClient(Android) {
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            if (BuildConfig.DEBUG) Timber.d(message) else Timber.i(message)
                        }
                    }
                    level = if (BuildConfig.DEBUG) LogLevel.BODY else LogLevel.INFO
                }
                expectSuccess = true
                install(ContentNegotiation) {
                    json()
                }
                defaultRequest {
                    url(KunaRepository.API_BASE_URL)
                }
            }
        }
        single<KunaRepository> {
            KunaRepositoryImpl(
                httpClient = get(),
                ioDispatcher = Dispatchers.IO,
            )
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
