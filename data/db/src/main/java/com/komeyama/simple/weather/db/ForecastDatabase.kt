package com.komeyama.simple.weather.db

interface ForecastDatabase {
    fun weatherInfoEntity(): List<ForecastInfoEntity>
}