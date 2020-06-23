package com.komeyama.simple.weather.forecast.di

import android.app.Application
import com.komeyama.simple.weather.db.DbComponent
import com.komeyama.simple.weather.db.DetailDescriptionDatabase
import com.komeyama.simple.weather.db.DetailLocationDatabase
import com.komeyama.simple.weather.db.ForecastDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class DbComponentModule {
    @Provides
    @Singleton
    fun provideForecastStore(
        application: Application
    ): ForecastDatabase {
        return DbComponent.factory()
            .create(application, Dispatchers.IO, "forecast_info.db").forecastDatabase()
    }

    @Provides
    @Singleton
    fun provideDetailDescriptionStore(
        application: Application
    ): DetailDescriptionDatabase {
        return DbComponent.factory()
            .create(application, Dispatchers.IO, "detail_description.db").detailDescriptionDatabase()
    }

    @Provides
    @Singleton
    fun provideDetailLocationStore(
        application: Application
    ): DetailLocationDatabase {
        return DbComponent.factory()
            .create(application, Dispatchers.IO, "detail_location.db").detailLocationDatabase()
    }
}