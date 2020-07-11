package com.komeyama.simple.weather.db

interface TemperatureEntity {
    val min: DetailTemperatureEntity?
    val max: DetailTemperatureEntity?
}