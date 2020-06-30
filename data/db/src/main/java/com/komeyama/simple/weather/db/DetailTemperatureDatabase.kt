package com.komeyama.simple.weather.db

interface DetailTemperatureDatabase {
    fun detailTemperatureEntity(): List<DetailTemperatureEntity>
}