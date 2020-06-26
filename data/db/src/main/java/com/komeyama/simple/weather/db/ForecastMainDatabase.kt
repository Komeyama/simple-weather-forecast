package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.model.DetailForecastResponse
import com.komeyama.simple.weather.model.MainResponse

interface ForecastMainDatabase {
    fun forecastMainInfoEntity(): List<ForecastMainInfoEntity>
    suspend fun save(response: MainResponse, response2: List<DetailForecastResponse>)
}