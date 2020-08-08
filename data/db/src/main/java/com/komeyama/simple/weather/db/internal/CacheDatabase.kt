package com.komeyama.simple.weather.db.internal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.komeyama.simple.weather.db.internal.dao.*
import com.komeyama.simple.weather.db.internal.dao.ForecastInfoDao
import com.komeyama.simple.weather.db.internal.dao.ForecastMainInfoDao
import com.komeyama.simple.weather.db.internal.entity.FavoritePlaceEntityImpl
import com.komeyama.simple.weather.db.internal.entity.ForecastMainInfoEntityImpl
import com.komeyama.simple.weather.db.internal.entity.WeatherEntityImpl

@Database(
    entities = [
        FavoritePlaceEntityImpl::class,
        ForecastMainInfoEntityImpl::class,
        WeatherEntityImpl::class
    ], version = 1
)
internal abstract class CacheDatabase : RoomDatabase() {
    abstract fun favoritePlaceDao(): FavoritePlaceDao
    abstract fun forecastInfoDao(): ForecastInfoDao
    abstract fun forecastMainInfoDao(): ForecastMainInfoDao
    abstract fun weatherDao(): WeatherDao
}