package com.digrec.kuna.di

import androidx.room.Room
import com.digrec.kuna.BuildConfig
import com.digrec.kuna.core.data.database.KunaDatabase
import com.digrec.kuna.core.data.repository.KunaRepositoryImpl
import com.digrec.kuna.core.domain.GetAllKunaUseCase
import com.digrec.kuna.core.domain.RefreshAllKunaUseCase
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
import org.koin.android.ext.koin.androidContext
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
        single {
            Room.databaseBuilder(
                context = androidContext(),
                klass = KunaDatabase::class.java,
                name = KunaDatabase.DB_FILE_NAME,
            ).build()
        }
        factory {
            get<KunaDatabase>()
                .kunaDao()
        }
        single<KunaRepository> {
            KunaRepositoryImpl(
                dao = get(),
                httpClient = get(),
            )
        }
    }

    val useCaseModule = module {
        factory { GetAllKunaUseCase(kunaRepository = get()) }
        factory { RefreshAllKunaUseCase(kunaRepository = get()) }
    }

    val viewModelModule = module {
        viewModel { KunaListViewModel(getAllKuna = get(), refreshAllKuna = get()) }
        viewModel {
            SettingsViewModel(BuildConfig.VERSION_CODE, BuildConfig.VERSION_NAME)
        }
    }
}
