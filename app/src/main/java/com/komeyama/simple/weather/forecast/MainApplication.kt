package com.komeyama.simple.weather.forecast

import com.facebook.stetho.Stetho
import com.komeyama.simple.weather.forecast.di.AppComponent
import com.komeyama.simple.weather.forecast.di.createAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

open class MainApplication : DaggerApplication() {

    private val appComponent: AppComponent by lazy {
        createAppComponent()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
        setTimber()
        setSteth()
    }

    private fun setTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun setSteth() {
        // Stetho
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(
                    Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(
                    Stetho.defaultInspectorModulesProvider(this))
                .build())
    }
}