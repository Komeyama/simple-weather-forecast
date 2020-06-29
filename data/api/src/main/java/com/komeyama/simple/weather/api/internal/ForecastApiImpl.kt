package com.komeyama.simple.weather.api.internal

import com.komeyama.simple.weather.api.ForecastApi
import com.komeyama.simple.weather.model.ForecastInfo
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import javax.inject.Inject

internal class ForecastApiImpl @Inject constructor(
    private val httpClient: HttpClient
): ForecastApi {
    private val json = Json(JsonConfiguration(
        isLenient = true,
        ignoreUnknownKeys = true,
        serializeSpecialFloatingPointValues = true,
        useArrayPolymorphism = true
    ))
    override suspend fun getAllForecastLists(): ForecastInfo {
        val rawResponse = httpClient.get<String> {
            url("http://weather.livedoor.com/forecast/webservice/json/v1?city=410020")
            accept(ContentType.Application.Json)
        }
        return json.parse(ForecastInfo.serializer(), rawResponse)
    }

}