package com.komeyama.simple.weather.db.internal

import com.komeyama.simple.weather.db.ForecastDatabase
import com.komeyama.simple.weather.db.ForecastInfoEntity
import javax.inject.Inject

internal class RoomDatabase @Inject constructor(
    private val cacheDatabase: CacheDatabase
): ForecastDatabase {
    override fun weatherInfoEntity(): List<ForecastInfoEntity> {
        return cacheDatabase.weatherInfoDao().weatherInfo()
    }

}