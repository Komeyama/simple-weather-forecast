package com.komeyama.simple.weather.db

interface WeatherDatabase {
    fun weatherDatabaseInfoEntity(): List<WeatherEntity>
}