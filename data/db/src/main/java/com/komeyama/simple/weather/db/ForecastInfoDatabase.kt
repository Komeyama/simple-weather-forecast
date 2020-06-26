package com.komeyama.simple.weather.db


interface ForecastInfoDatabase {
    fun forecastInfo(): List<ForecastInfoEntity>
}