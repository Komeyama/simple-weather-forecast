package com.komeyama.simple.weather.db.internal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.komeyama.simple.weather.db.internal.dao.WeatherInfoDao
import com.komeyama.simple.weather.db.internal.entity.WeatherInfoEntityImpl

@Database(entities = [WeatherInfoEntityImpl::class], version = 1)
internal abstract class CacheDatabase : RoomDatabase() {
    abstract fun weatherInfoDao(): WeatherInfoDao
}