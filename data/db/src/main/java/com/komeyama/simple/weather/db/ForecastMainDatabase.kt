package com.komeyama.simple.weather.db

interface ForecastMainDatabase {
    fun forecastMainInfoEntity(): List<ForecastMainInfoEntity>
}