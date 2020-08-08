package com.komeyama.simple.weather.forecast.di

import com.komeyama.simple.weather.api.ForecastApi
import com.komeyama.simple.weather.db.*
import com.komeyama.simple.weather.repository.RepositoryComponent
import com.komeyama.simple.weather.repository.ForecastRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryComponentModule {
    @Provides
    @Singleton
    fun provideRepository(
        repositoryComponent: RepositoryComponent
    ): ForecastRepository {
        return repositoryComponent.forecastRepository()
    }

    @Provides
    @Singleton
    fun provideRepositoryComponent(
        favoritePlaceDatabase: FavoritePlaceDatabase,
        forecastApi: ForecastApi,
        forecastInfoDatabase: ForecastInfoDatabase,
        forecastMainDatabase: ForecastMainDatabase,
        weatherDatabase: WeatherDatabase
    ): RepositoryComponent {
        return RepositoryComponent.factory().create(
            favoritePlaceDatabase = favoritePlaceDatabase,
            forecastApi = forecastApi,
            forecastInfoDatabase = forecastInfoDatabase,
            forecastMainDatabase = forecastMainDatabase,
            weatherDatabase = weatherDatabase
        )
    }
}