package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.model.Response

interface ForecastDatabase {
    fun forecastInfoEntity(): List<ForecastInfoEntity>
    suspend fun save(response: Response)
}