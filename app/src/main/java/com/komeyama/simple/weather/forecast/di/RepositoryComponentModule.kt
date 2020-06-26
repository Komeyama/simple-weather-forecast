package com.komeyama.simple.weather.forecast.di

import com.komeyama.simple.weather.api.ForecastApi
import com.komeyama.simple.weather.db.*
import com.komeyama.simple.weather.repository.RepositoryComponent
import com.komeyama.simple.weather.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryComponentModule {
    @Provides
    @Singleton
    fun provideRepository(
        repositoryComponent: RepositoryComponent
    ): WeatherRepository {
        return repositoryComponent.weatherRepository()
    }

    @Provides
    @Singleton
    fun provideRepositoryComponent(
        forecastApi: ForecastApi,
        forecastInfoDatabase: ForecastInfoDatabase,
        forecastMainDatabase: ForecastMainDatabase,
        detailDescriptionDatabase: DetailDescriptionDatabase,
        detailForecastDatabase: DetailForecastDatabase,
        detailLocationDatabase: DetailLocationDatabase,
        pinpointLocationDatabase: PinpointLocationDatabase
    ): RepositoryComponent {
        return RepositoryComponent.factory().create(
            forecastApi = forecastApi,
            forecastInfoDatabase = forecastInfoDatabase,
            detailForecastDatabase = detailForecastDatabase,
            forecastMainDatabase = forecastMainDatabase,
            detailDescriptionDatabase = detailDescriptionDatabase,
            detailLocationDatabase = detailLocationDatabase,
            pinpointLocationDatabase = pinpointLocationDatabase
        )
    }
}