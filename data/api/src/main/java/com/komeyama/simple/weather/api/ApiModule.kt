package com.komeyama.simple.weather.api

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
                .baseUrl("http://weather.livedoor.com/")
                .build()
    }

    @Provides
    @Singleton
    fun provideIGetForecastInfo(retrofit: Retrofit): ForecastService {
        return retrofit.create(ForecastService::class.java)
    }
}