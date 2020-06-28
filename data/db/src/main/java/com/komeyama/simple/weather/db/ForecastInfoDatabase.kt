package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.model.DetailCopyrightResponse
import com.komeyama.simple.weather.model.DetailForecastResponse
import com.komeyama.simple.weather.model.MainResponse
import com.komeyama.simple.weather.model.PinpointLocationResponse

interface ForecastInfoDatabase {
    suspend fun forecastInfo(): List<ForecastInfoEntity>
    suspend fun save(
        mainResponse: MainResponse,
        detailForecastResponse: List<DetailForecastResponse>,
        pinpointLocationResponse: List<PinpointLocationResponse>,
        copyright: List<DetailCopyrightResponse>
    )
}