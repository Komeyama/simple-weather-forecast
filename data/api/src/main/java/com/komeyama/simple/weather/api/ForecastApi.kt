package com.komeyama.simple.weather.api

import com.komeyama.simple.weather.model.ForecastInfo
import com.komeyama.simple.weather.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {
//    @GET("forecast/webservice/json/v1")
//    suspend fun getForecastInfo(@Query("city") cityID: String): Response<ForecastInfo>
    suspend fun getAllForecastLists(): ForecastInfo
}