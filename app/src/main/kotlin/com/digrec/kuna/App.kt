package com.digrec.kuna

import android.app.Application
import com.digrec.kuna.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber


/**
 * Created by Dejan Igrec
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                Modules.appModule,
                Modules.viewModelModule,
                Modules.repositoryModule,
                Modules.useCaseModule,
            )
        }

        Timber.plant(Timber.DebugTree())

        Timber.i("=== Kuna v${BuildConfig.VERSION_NAME}+${BuildConfig.VERSION_CODE} ===")
    }
}
