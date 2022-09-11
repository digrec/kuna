package com.digrec.kuna

import android.app.Application
import timber.log.Timber


/**
 * Created by Dejan Igrec
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        Timber.i("=== Kuna v${BuildConfig.VERSION_NAME}+${BuildConfig.VERSION_CODE} ===")
    }
}
