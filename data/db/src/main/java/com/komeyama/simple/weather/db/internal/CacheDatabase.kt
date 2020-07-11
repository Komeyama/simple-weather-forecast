package com.komeyama.simple.weather.db.internal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.komeyama.simple.weather.db.internal.dao.*
import com.komeyama.simple.weather.db.internal.dao.DetailDescriptionDao
import com.komeyama.simple.weather.db.internal.dao.DetailForecastDao
import com.komeyama.simple.weather.db.internal.dao.DetailLocationDao
import com.komeyama.simple.weather.db.internal.dao.ForecastInfoDao
import com.komeyama.simple.weather.db.internal.dao.ForecastMainInfoDao
import com.komeyama.simple.weather.db.internal.dao.PinpointLocationDao
import com.komeyama.simple.weather.db.internal.entity.*

@Database(
    entities = [
        FavoritePlaceEntityImpl::class,
        ForecastMainInfoEntityImpl::class,
        DetailCopyrightMainEntityImpl::class,
        DetailDescriptionEntityImpl::class,
        DetailForecastEntityImpl::class,
        DetailImageEntityImpl::class,
        DetailImageEntityImplOfCopyright::class,
        DetailLocationEntityImpl::class,
        DetailTemperatureEntityImpl::class,
        PinpointLocationEntityImpl::class,
        PinpointLocationOfCopyEntityImpl::class,
        TemperatureEntityImpl::class
    ], version = 1
)
internal abstract class CacheDatabase : RoomDatabase() {
    abstract fun favoritePlaceDao(): FavoritePlaceDao
    abstract fun forecastInfoDao(): ForecastInfoDao
    abstract fun forecastMainInfoDao(): ForecastMainInfoDao
    abstract fun detailCopyrightDao(): DetailCopyrightDao
    abstract fun detailCopyrightMainDao(): DetailCopyrightMainDao
    abstract fun detailDescriptionDao(): DetailDescriptionDao
    abstract fun detailForecastDao(): DetailForecastDao
    abstract fun detailImageDao(): DetailImageDao
    abstract fun detailLocationDao(): DetailLocationDao
    abstract fun detailTemperatureDao(): DetailTemperatureDao
    abstract fun pinpointLocationDao(): PinpointLocationDao
    abstract fun temperatureDao(): TemperatureDao
}