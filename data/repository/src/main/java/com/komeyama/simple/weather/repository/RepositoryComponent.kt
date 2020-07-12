package com.komeyama.simple.weather.repository

import com.komeyama.simple.weather.api.ForecastApi
import com.komeyama.simple.weather.db.*
import com.komeyama.simple.weather.repository.internal.RepositoryModule
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
    fun forecastRepository(): ForecastRepository

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance favoritePlaceDatabase: FavoritePlaceDatabase,
            @BindsInstance forecastApi: ForecastApi,
            @BindsInstance forecastInfoDatabase: ForecastInfoDatabase,
            @BindsInstance forecastMainDatabase: ForecastMainDatabase,
            @BindsInstance detailCopyrightDatabase: DetailCopyrightDatabase,
            @BindsInstance detailCopyrightMainDatabase: DetailCopyrightMainDatabase,
            @BindsInstance detailDescriptionDatabase: DetailDescriptionDatabase,
            @BindsInstance detailForecastDatabase: DetailForecastDatabase,
            @BindsInstance detailImageDatabase: DetailImageDatabase,
            @BindsInstance detailImageOfCopyrightDatabase: DetailImageOfCopyrightDatabase,
            @BindsInstance detailLocationDatabase: DetailLocationDatabase,
            @BindsInstance pinpointLocationDatabase: PinpointLocationDatabase
        ): RepositoryComponent
    }

    companion object {
        fun factory(): Factory = DaggerRepositoryComponent.factory()
    }
}