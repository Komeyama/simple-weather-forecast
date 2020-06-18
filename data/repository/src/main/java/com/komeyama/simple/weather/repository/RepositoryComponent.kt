package com.komeyama.simple.weather.repository

import com.komeyama.simple.weather.api.ForecastService
import com.komeyama.simple.weather.repository.internal.RepositoryModule
import com.komeyama.simple.weather.repository.internal.WeatherRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            RepositoryModule::class
        ]
)
interface RepositoryComponent {
    fun weatherRepository(): WeatherRepository

    @Component.Factory
    interface Factory {
        fun create(
                @BindsInstance forecastService: ForecastService
        ): RepositoryComponent
    }

    companion object {
        fun factory(): Factory = DaggerRepositoryComponent.factory()
    }
}