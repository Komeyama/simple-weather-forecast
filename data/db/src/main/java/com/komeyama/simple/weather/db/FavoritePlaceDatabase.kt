package com.komeyama.simple.weather.db

interface FavoritePlaceDatabase {
    fun favoritePlaceEntity(): List<FavoritePlaceEntity>
}