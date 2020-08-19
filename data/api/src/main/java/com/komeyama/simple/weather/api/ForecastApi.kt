package com.komeyama.simple.weather.api

import com.komeyama.simple.weather.model.DetailForecastInfo
import com.komeyama.simple.weather.model.ForecastInfo

interface ForecastApi {
    suspend fun getForecastListFromLatLon(lat: Float, lon: Float): ForecastInfo
    suspend fun getForecastListFromName(name: String): ForecastInfo
    suspend fun getDetailForecastListFromName(name: String): DetailForecastInfo
    suspend fun getAllCityForecastList(): List<ForecastInfo>
    suspend fun getAllPrefectureForecastList(): List<ForecastInfo>
}