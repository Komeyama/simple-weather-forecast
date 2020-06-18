package com.komeyama.simple.weather.api

import com.komeyama.simple.weather.model.ForecastInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {
    @GET("forecast/webservice/json/v1")
    suspend fun getForecastInfo(@Query("city") cityID: String): Response<ForecastInfo>
}