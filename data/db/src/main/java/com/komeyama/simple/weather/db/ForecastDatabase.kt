package com.komeyama.simple.weather.db

interface ForecastDatabase {
    fun forecastInfoEntity(): List<ForecastInfoEntity>
    suspend fun save(response: Response)
}

data class Response (
    var id: String,
    var cityID: String,
    var dummy: SubResponse
)

data class SubResponse(
    var area: String,
    var prefecture: String,
    var city: String
)