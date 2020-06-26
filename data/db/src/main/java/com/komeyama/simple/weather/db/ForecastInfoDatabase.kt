package com.komeyama.simple.weather.db


interface ForecastInfoDatabase {
    suspend fun forecastInfo(): List<ForecastInfoEntity>
}