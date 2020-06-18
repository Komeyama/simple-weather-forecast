package com.komeyama.simple.weather.api.internal

import com.google.gson.GsonBuilder
import com.komeyama.simple.weather.api.ForecastApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class ApiModule {

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
    fun provideIGetForecastInfo(retrofit: Retrofit): ForecastApi {
        return retrofit.create(ForecastApi::class.java)
    }
}