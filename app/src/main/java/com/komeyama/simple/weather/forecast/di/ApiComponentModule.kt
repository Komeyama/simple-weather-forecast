package com.komeyama.simple.weather.forecast.di

import com.komeyama.simple.weather.api.ApiComponent
import com.komeyama.simple.weather.api.IGetForecastInfo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiComponentModule {
    @Provides
    @Singleton
    fun provideRepository(
            apiComponent: ApiComponent
    ): IGetForecastInfo {
        return apiComponent.iGetForecastInfo()
    }

    @Provides
    @Singleton
    fun provideApiComponent():ApiComponent {
        return ApiComponent.factory().create()
    }
}