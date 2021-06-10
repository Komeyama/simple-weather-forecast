package com.komeyama.simple.weather.api.internal

import com.komeyama.simple.weather.api.BuildConfig
import com.komeyama.simple.weather.api.ForecastApi
import com.komeyama.simple.weather.model.*
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
) : ForecastApi {

    companion object {
        const val API_BASE_URL = "http://api.openweathermap.org/data/2.5/"
    }

    /**
     * Options list: https://github.com/Kotlin/kotlinx.serialization/blob/ffe216f0293231b09eea24a10aa4bc26ff6d5b90/runtime/commonMain/src/kotlinx/serialization/json/JsonConfiguration.kt#L15-L44
     */
    private val json = Json(
        JsonConfiguration.Stable.copy(
            isLenient = true,
            ignoreUnknownKeys = true
        )
    )

    /**
     * use it in the exception handling
     * e.g.)
     *  try {
     *      getForecastList("xxxxxx")
     *  } catch(e: Exception) {
     *      Timber.e(e.message)
     *  }
     */
    override suspend fun getForecastListFromLatLon(lat: Float, lon: Float): ForecastInfo {
        val rawResponse = httpClient.get<String> {
            url("${API_BASE_URL}/weather?lat=${lat}&lon=${lon}&APPID=${BuildConfig.apiKey}")
            accept(ContentType.Application.Json)
        }
        return json.parse(ForecastInfo.serializer(), rawResponse)
    }

    override suspend fun getForecastListFromName(name: String): ForecastInfo {
        val rawResponse = httpClient.get<String> {
            url("${API_BASE_URL}/weather?q=${name}&APPID=${BuildConfig.apiKey}")
            accept(ContentType.Application.Json)
        }
        return json.parse(ForecastInfo.serializer(), rawResponse)
    }

    override suspend fun getDetailForecastListFromName(name: String): DetailForecastInfo {
        val rawResponse = httpClient.get<String> {
            url("${API_BASE_URL}/forecast?q=${name}&APPID=${BuildConfig.apiKey}")
            accept(ContentType.Application.Json)
        }
        return json.parse(DetailForecastInfo.serializer(), rawResponse)
    }

    override suspend fun getWeeklyForecastFromLatLon(lat: Float, lon: Float): WeeklyForecastInfo {
        val rawResponse = httpClient.get<String> {
            url("${API_BASE_URL}/onecall?lat=${lat}&lon=${lon}&APPID=${BuildConfig.apiKey}")
            accept(ContentType.Application.Json)
        }
        return json.parse(WeeklyForecastInfo.serializer(), rawResponse)
    }

    /**
     * TODO: fix emergency
     */
    override suspend fun getAllCityForecastList(): List<ForecastInfo> {
        val forecastInfoList: MutableList<ForecastInfo> = mutableListOf()
        CityIds.values().forEach { cityId ->
            forecastInfoList.add(getForecastListFromName(cityId.id))
        }
        return forecastInfoList
    }

    override suspend fun getAllPrefectureForecastList(): List<ForecastInfo> {
        val forecastInfoList: MutableList<ForecastInfo> = mutableListOf()
        PrefectureIds.values().forEach { prefectureId ->
            forecastInfoList.add(getForecastListFromName(prefectureId.prefectureName))
        }
        return forecastInfoList
    }


}