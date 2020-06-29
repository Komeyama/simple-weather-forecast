package com.komeyama.simple.weather.api.internal

import com.google.gson.GsonBuilder
import com.komeyama.simple.weather.api.ForecastApi
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class ApiModule {

    companion object {
        const val WEATHER_URL = "http://weather.livedoor.com/"
    }

    @Provides
    @Singleton
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().create()
                )
            )
            .baseUrl(WEATHER_URL)
            .build()
    }


    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    Json(
                        JsonConfiguration.Stable.copy()
                    )
                )
            }
        }
    }

    @Provides
    @Singleton
    fun provideForecastApi(httpClient: HttpClient): ForecastApi {
        return ForecastApiImpl(httpClient)
    }
}