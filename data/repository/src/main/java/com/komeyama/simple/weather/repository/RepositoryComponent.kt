package com.komeyama.simple.weather.repository

import com.komeyama.simple.weather.api.ForecastApi
import com.komeyama.simple.weather.db.dao.WeatherInfoDao
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
    fun weatherRepository(): WeatherRepository

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance forecastApi: ForecastApi,
            @BindsInstance weatherInfoDao: WeatherInfoDao
        ): RepositoryComponent
    }

    companion object {
        fun factory(): Factory = DaggerRepositoryComponent.factory()
    }
}