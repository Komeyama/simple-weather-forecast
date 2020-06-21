package com.komeyama.simple.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.komeyama.simple.weather.db.dao.WeatherInfoDao
import com.komeyama.simple.weather.db.entity.WeatherInfoEntityImpl

@Database(entities = [WeatherInfoEntityImpl::class], version = 1)
internal abstract class WeatherInfoDatabase : RoomDatabase() {
    abstract fun weatherInfoDao(): WeatherInfoDao
}