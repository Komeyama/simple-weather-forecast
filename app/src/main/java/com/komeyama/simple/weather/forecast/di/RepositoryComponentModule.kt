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
        forecastApi: ForecastApi,
        forecastInfoDatabase: ForecastInfoDatabase,
        forecastMainDatabase: ForecastMainDatabase,
        detailCopyrightMainDatabase: DetailCopyrightMainDatabase,
        detailDescriptionDatabase: DetailDescriptionDatabase,
        detailForecastDatabase: DetailForecastDatabase,
        detailImageDatabase: DetailImageDatabase,
        detailImageOfCopyrightDatabase: DetailImageOfCopyrightDatabase,
        detailLocationDatabase: DetailLocationDatabase,
        pinpointLocationDatabase: PinpointLocationDatabase
    ): RepositoryComponent {
        return RepositoryComponent.factory().create(
            forecastApi = forecastApi,
            forecastInfoDatabase = forecastInfoDatabase,
            forecastMainDatabase = forecastMainDatabase,
            detailCopyrightMainDatabase = detailCopyrightMainDatabase,
            detailDescriptionDatabase = detailDescriptionDatabase,
            detailForecastDatabase = detailForecastDatabase,
            detailImageDatabase = detailImageDatabase,
            detailImageOfCopyrightDatabase = detailImageOfCopyrightDatabase,
            detailLocationDatabase = detailLocationDatabase,
            pinpointLocationDatabase = pinpointLocationDatabase
        )
    }
}