package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.model.DetailForecastResponse
import com.komeyama.simple.weather.model.MainResponse
import com.komeyama.simple.weather.model.PinpointLocationResponse

interface ForecastMainDatabase {
    fun forecastMainInfoEntity(): List<ForecastMainInfoEntity>
    suspend fun save(
        mainResponse: MainResponse,
        detailForecastResponse: List<DetailForecastResponse>,
        pinpointLocationResponse: List<PinpointLocationResponse>
    )
}