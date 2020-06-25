package com.komeyama.simple.weather.db

interface DetailForecastDatabase {
    fun detailForecastEntity(): List<DetailForecastEntity>
}