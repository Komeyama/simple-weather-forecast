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
    fun provideForecastInfoStore(
        application: Application
    ): ForecastInfoDatabase {
        return DbComponent.factory()
            .create(application, Dispatchers.IO, "forecast_info.db").forecastInfoDatabase()
    }

    @Provides
    @Singleton
    fun provideForecastStore(
        application: Application
    ): ForecastMainDatabase {
        return DbComponent.factory()
            .create(application, Dispatchers.IO, "forecast_info.db").forecastMainDatabase()
    }

    @Provides
    @Singleton
    fun provideDetailCopyrightStore(
        application: Application
    ): DetailCopyrightDatabase {
        return DbComponent.factory()
            .create(application, Dispatchers.IO, "detail_copyright.db").detailCopyrightDatabase()
    }

    @Provides
    @Singleton
    fun provideDetailCopyrightMainStore(
        application: Application
    ): DetailCopyrightMainDatabase {
        return DbComponent.factory()
            .create(application, Dispatchers.IO, "detail_copyright.db").detailCopyrightMainDatabase()
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
    fun provideDetailForecastStore(
        application: Application
    ): DetailForecastDatabase {
        return DbComponent.factory()
            .create(application, Dispatchers.IO, "detail_forecast.db").detailForecastDatabase()
    }

    @Provides
    @Singleton
    fun provideDetailImageStore(
        application: Application
    ): DetailImageDatabase {
        return DbComponent.factory()
            .create(application, Dispatchers.IO, "detail_image.db").detailImageDatabase()
    }

    @Provides
    @Singleton
    fun provideDetailImageOfCopyrightStore(
        application: Application
    ): DetailImageOfCopyrightDatabase {
        return DbComponent.factory()
            .create(application, Dispatchers.IO, "detail_image_copyright.db").detailImageOfCopyrightDatabase()
    }

    @Provides
    @Singleton
    fun provideDetailLocationStore(
        application: Application
    ): DetailLocationDatabase {
        return DbComponent.factory()
            .create(application, Dispatchers.IO, "detail_location.db").detailLocationDatabase()
    }

    @Provides
    @Singleton
    fun providePinpointLocationStore(
        application: Application
    ): PinpointLocationDatabase {
        return DbComponent.factory()
            .create(application, Dispatchers.IO, "pinpoint_location.db").pinpointLocationDatabase()
    }
}