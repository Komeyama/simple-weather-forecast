package com.komeyama.simple.weather.api

import com.komeyama.simple.weather.api.internal.ApiModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class
    ]
)
interface ApiComponent {
    fun forecastService(): ForecastApi

    @Component.Factory
    interface Factory {
        fun create(): ApiComponent
    }

    companion object {
        fun factory(): Factory = DaggerApiComponent.factory()
    }
}