package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.db.internal.entity.WeatherEntity

interface WeatherDatabase {
    fun weatherDatabaseInfoEntity(): List<WeatherEntity>
}