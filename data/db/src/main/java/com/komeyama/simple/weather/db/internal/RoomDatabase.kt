package com.komeyama.simple.weather.db.internal

import com.komeyama.simple.weather.db.DetailLocationDatabase
import com.komeyama.simple.weather.db.DetailLocationEntity
import com.komeyama.simple.weather.db.ForecastDatabase
import com.komeyama.simple.weather.db.ForecastInfoEntity
import javax.inject.Inject

internal class RoomDatabase @Inject constructor(
    private val cacheDatabase: CacheDatabase
) : ForecastDatabase, DetailLocationDatabase {
    override fun forecastInfoEntity(): List<ForecastInfoEntity> {
        return cacheDatabase.forecastInfoDao().forecastInfo()
    }

    override fun detailLocationEntity(): List<DetailLocationEntity> {
        return cacheDatabase.detailLocation().detailLocationInfo()
    }


}