package com.komeyama.simple.weather.repository

import com.komeyama.simple.weather.model.ForecastInfo
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {
    suspend fun refresh()
    suspend fun forecastContents(): Flow<List<ForecastInfo>>
    suspend fun dummySave()
    suspend fun dummyLoad()
}