package com.komeyama.simple.weather.forecast.di

import android.app.Application
import com.komeyama.simple.weather.db.*
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class DbComponentModule {

    @Provides
    @Singleton
    fun provideFavoritePlaceStore(application: Application): FavoritePlaceDatabase {
        return DbComponent.factory().create(application, Dispatchers.IO, "forecast_info.db")
            .favoritePlaceDatabase()
    }

    @Provides
    @Singleton
    fun provideForecastInfoStore(application: Application): ForecastInfoDatabase {
        return DbComponent.factory()
            .create(application, Dispatchers.IO, "forecast_info.db").forecastInfoDatabase()
    }

    @Provides
    @Singleton
    fun provideForecastStore(application: Application): ForecastMainDatabase {
        return DbComponent.factory()
            .create(application, Dispatchers.IO, "forecast_info.db").forecastMainDatabase()
    }

    @Provides
    @Singleton
    fun provideWeatherStore(application: Application): WeatherDatabase {
        return DbComponent.factory()
            .create(application, Dispatchers.IO, "weather_info.db").weatherDatabase()
    }
}