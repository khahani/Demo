package de.check24.girokonto

import android.app.Application
import de.check24.girokonto.di.AppComponent
import de.check24.girokonto.di.DaggerAppComponent
import timber.log.Timber

open class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}