package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.model.*

interface ForecastInfoDatabase {
    suspend fun forecastInfo(): List<ForecastInfoEntity>
    suspend fun save(
        id: Int,
        forecastInfo: ForecastInfo?
    )
}