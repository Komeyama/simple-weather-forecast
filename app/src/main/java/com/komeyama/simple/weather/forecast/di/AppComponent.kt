package com.komeyama.simple.weather.forecast.di

import android.app.Application
import com.komeyama.simple.weather.forecast.MainActivityModule
import com.komeyama.simple.weather.forecast.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            MainActivityModule.MainActivityBuilder::class
        ]
)
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    override fun inject(app: MainApplication)
}

fun Application.createAppComponent() = DaggerAppComponent.factory().create(this)