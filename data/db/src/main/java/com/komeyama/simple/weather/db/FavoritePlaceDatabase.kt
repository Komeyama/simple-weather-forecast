package com.komeyama.simple.weather.db

import kotlinx.coroutines.flow.Flow

interface FavoritePlaceDatabase {
    suspend fun favoriteState(): List<FavoritePlaceEntity>
    fun favoriteStateFlow(): Flow<List<FavoritePlaceEntity>>
    suspend fun saveFavoriteState(favoriteId: String)
}