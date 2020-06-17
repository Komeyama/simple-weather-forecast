package com.komeyama.simple.weather.api

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
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
    fun provideIGetForecastInfo(retrofit: Retrofit): IGetForecastInfo {
        return retrofit.create(IGetForecastInfo::class.java)
    }
}