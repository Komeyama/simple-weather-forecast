package com.komeyama.simple.weather.repository

interface ForecastRepository {
    suspend fun refresh()
    suspend fun dummySave()
    suspend fun dummyLoad()
}