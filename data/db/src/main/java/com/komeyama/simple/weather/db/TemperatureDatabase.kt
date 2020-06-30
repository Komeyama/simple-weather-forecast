package com.komeyama.simple.weather.db

interface TemperatureDatabase {
    fun temperatureEntity(): List<TemperatureEntity>
}