package com.komeyama.simple.weather.db

interface FavoritePlaceDatabase {
    fun favoriteState(): List<FavoritePlaceEntity>
    suspend fun saveFavoriteState(favoriteId: String)
}