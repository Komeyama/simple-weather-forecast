package com.komeyama.simple.weather.repository

import com.komeyama.simple.weather.model.DetailForecastInfo
import com.komeyama.simple.weather.model.ForecastInfo
import com.komeyama.simple.weather.model.PrefectureIds
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {
    suspend fun refresh(prefectureId: String)
    suspend fun detailForecastContents(cityIds: String): Flow<DetailForecastInfo>
    suspend fun forecastContents(): Flow<List<ForecastInfo>>
    suspend fun forecastCityContents(cityIds: String): Flow<List<ForecastInfo>>
    suspend fun forecastPrefectureContents(): Flow<List<ForecastInfo>>
    suspend fun forecastFavoriteCityContents(): Flow<List<ForecastInfo>>
    suspend fun getFavoriteIds(): Flow<List<String>>
    suspend fun toggleFavorite(favoriteId: String)
}