package com.komeyama.simple.weather.api

import com.komeyama.simple.weather.model.ForecastInfo

interface ForecastApi {
    suspend fun getForecastList(lat: Float, lon: Float): ForecastInfo
    suspend fun getAllCityForecastList(): List<ForecastInfo>
}