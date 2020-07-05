package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.model.*
import kotlinx.coroutines.flow.Flow

interface ForecastInfoDatabase {
    suspend fun forecastInfo(): List<ForecastInfoEntity>
    suspend fun forecastInfoFlow(): Flow<List<ForecastInfoEntity>>
    suspend fun save(forecastInfo: List<ForecastInfo?>)
}