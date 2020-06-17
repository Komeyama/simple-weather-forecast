package com.komeyama.simple.weather.api

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            ApiModule::class
        ]
)
interface ApiComponent {
    fun iGetForecastInfo(): IGetForecastInfo

    @Component.Factory
    interface Factory {
        fun create(): ApiComponent
    }

    companion object {
        fun factory(): Factory = DaggerApiComponent.factory()
    }
}