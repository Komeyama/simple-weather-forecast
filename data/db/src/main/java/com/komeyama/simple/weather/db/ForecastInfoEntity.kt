package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.db.internal.entity.WeatherEntity

interface ForecastInfoEntity {
    val forecastInfoEntity: ForecastMainInfoEntity
    val weatherEntity: List<WeatherEntity>
}