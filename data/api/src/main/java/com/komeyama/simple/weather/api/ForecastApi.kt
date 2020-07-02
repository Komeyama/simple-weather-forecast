package com.komeyama.simple.weather.api

import com.komeyama.simple.weather.model.ForecastInfo

interface ForecastApi {
    suspend fun getAllForecastLists(): ForecastInfo
}