package com.komeyama.simple.weather.db.internal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.komeyama.simple.weather.db.internal.dao.DetailDescriptionDao
import com.komeyama.simple.weather.db.internal.dao.DetailLocationDao
import com.komeyama.simple.weather.db.internal.dao.ForecastInfoDao
import com.komeyama.simple.weather.db.internal.entity.DetailDescriptionEntityImpl
import com.komeyama.simple.weather.db.internal.entity.DetailLocationEntityImpl
import com.komeyama.simple.weather.db.internal.entity.ForecastInfoEntityImpl

@Database(entities = [
    ForecastInfoEntityImpl::class,
    DetailDescriptionEntityImpl::class,
    DetailLocationEntityImpl::class
], version = 1)
internal abstract class CacheDatabase : RoomDatabase() {
    abstract fun forecastInfoDao(): ForecastInfoDao
    abstract fun detailDescriptionDao(): DetailDescriptionDao
    abstract fun detailLocationDao(): DetailLocationDao
}