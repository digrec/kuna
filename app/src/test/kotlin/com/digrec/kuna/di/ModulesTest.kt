package com.digrec.kuna.di

import android.content.Context
import io.ktor.client.engine.HttpClientEngine
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.verify.verify

/** Unit test to verify the Koin dependency injection configuration. */
class ModulesTest : KoinTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun verifyAllModules() {
        val allModules = module {
            includes(
                listOf(
                    Modules.appModule,
                    Modules.viewModelModule,
                    Modules.repositoryModule,
                    Modules.useCaseModule,
                )
            )
        }
        allModules.verify(extraTypes = listOf(Context::class, HttpClientEngine::class))
    }
}
