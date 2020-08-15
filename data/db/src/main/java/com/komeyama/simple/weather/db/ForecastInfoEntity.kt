package com.komeyama.simple.weather.db

interface ForecastInfoEntity {
    val forecastInfoEntity: ForecastMainInfoEntity
    val weatherEntity: List<WeatherEntity>
}