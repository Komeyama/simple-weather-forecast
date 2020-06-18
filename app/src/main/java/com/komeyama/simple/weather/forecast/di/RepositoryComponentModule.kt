package com.komeyama.simple.weather.forecast.di

import com.komeyama.simple.weather.api.ForecastApi
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
    fun provideRepositoryComponent(forecastApi: ForecastApi): RepositoryComponent {
        return RepositoryComponent.factory().create(
                forecastApi = forecastApi
        )
    }
}