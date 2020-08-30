package com.komeyama.simple.weather.repository

import com.komeyama.simple.weather.model.*
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {
    suspend fun refresh(prefectureId: String)
    suspend fun detailForecastContents(cityIds: String): Flow<DetailForecastInfo>
    suspend fun forecastContents(): Flow<List<ForecastInfo>>
    suspend fun forecastCityContents(cityIds: String): Flow<List<ForecastInfo>>
    suspend fun dailyForecastContent(lat: Float, lon: Float): Flow<WeeklyForecastInfo>
    suspend fun forecastPrefectureContents(): Flow<List<ForecastInfo>>
    suspend fun forecastFavoriteCityContents(): Flow<List<ForecastInfo>>
    suspend fun getFavoriteIds(): Flow<List<String>>
    suspend fun toggleFavorite(favoriteId: String)
}