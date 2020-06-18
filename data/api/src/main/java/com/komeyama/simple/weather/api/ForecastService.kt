package com.komeyama.simple.weather.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {
    @GET("forecast/webservice/json/v1")
    fun getForecastInfo(@Query("city") cityID: String): Call<ForecastInfo>
}