package com.komeyama.simple.weather.db

interface ForecastDatabase {
    fun forecastInfoEntity(): List<ForecastInfoEntity>
}