package com.komeyama.simple.weather.forecast.di

import com.komeyama.simple.weather.api.ApiComponent
import com.komeyama.simple.weather.api.ForecastApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiComponentModule {
    @Provides
    @Singleton
    fun provideForecastService(
        apiComponent: ApiComponent
    ): ForecastApi {
        return apiComponent.forecastService()
    }

    @Provides
    @Singleton
    fun provideApiComponent(): ApiComponent {
        return ApiComponent.factory().create()
    }
}