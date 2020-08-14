package com.komeyama.simple.weather.api.internal

import com.komeyama.simple.weather.api.ForecastApi
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import javax.inject.Singleton

@Module
internal class ApiModule {

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
            engine {
                config {
                    retryOnConnectionFailure(true)
                }
            }
        }
    }

    @Provides
    @Singleton
    fun provideForecastApi(httpClient: HttpClient): ForecastApi {
        return ForecastApiImpl(httpClient)
    }
}