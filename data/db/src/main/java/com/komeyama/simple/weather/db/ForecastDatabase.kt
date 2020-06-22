package com.komeyama.simple.weather.db

interface ForecastDatabase {
    fun forecastInfoEntity(): List<ForecastInfoEntity>
    suspend fun save(response: Response)
}

data class Response (
    var id: String,
    var cityID: String,
    /**
     * String -> SubResponse
     */
    var dummy: String
)

data class SubResponse(
    var area: String,
    var prefecture: String,
    var city: String
)