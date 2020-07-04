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
) : ForecastApi {

    /**
     * options: https://github.com/Kotlin/kotlinx.serialization/blob/ffe216f0293231b09eea24a10aa4bc26ff6d5b90/runtime/commonMain/src/kotlinx/serialization/json/JsonConfiguration.kt#L15-L44
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
     *  } catch(e :Exception) {
     *      Timber.e(e.message)
     *  }
     */
    override suspend fun getForecastList(id: String): ForecastInfo {
        val rawResponse = httpClient.get<String> {
            url("http://weather.livedoor.com/forecast/webservice/json/v1?city=${id}")
            accept(ContentType.Application.Json)
        }
        return json.parse(ForecastInfo.serializer(), rawResponse)
    }

}