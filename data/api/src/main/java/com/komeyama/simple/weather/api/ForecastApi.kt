package com.komeyama.simple.weather.api

import com.komeyama.simple.weather.model.ForecastInfo

interface ForecastApi {
    suspend fun getForecastListFromLatLon(lat: Float, lon: Float): ForecastInfo
    suspend fun getForecastListFromName(name: String): ForecastInfo
    suspend fun getAllCityForecastList(): List<ForecastInfo>
}