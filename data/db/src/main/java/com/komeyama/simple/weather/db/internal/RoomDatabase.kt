package com.komeyama.simple.weather.db.internal

import com.komeyama.simple.weather.db.*
import com.komeyama.simple.weather.db.internal.dao.DetailLocationDao
import com.komeyama.simple.weather.db.internal.dao.ForecastInfoDao
import com.komeyama.simple.weather.db.internal.entity.ForecastInfoEntityImpl
import com.komeyama.simple.weather.db.internal.entity.toForecastInfoEntities
import com.komeyama.simple.weather.db.internal.entity.toForecastInfoEntityImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RoomDatabase @Inject constructor(
    private val cacheDatabase: CacheDatabase,
    private val forecastInfoDao: ForecastInfoDao,
    private val detailLocationDao: DetailLocationDao
) : ForecastDatabase, DetailLocationDatabase {
    override fun forecastInfoEntity(): List<ForecastInfoEntity> {
        return cacheDatabase.forecastInfoDao().forecastInfo()
    }

    override fun detailLocationEntity(): List<DetailLocationEntity> {
        return cacheDatabase.detailLocation().detailLocationInfo()
    }

    override suspend fun save(response: Response) {
        withContext(Dispatchers.IO) {
            val list: MutableList<Response> = mutableListOf()
            list.add(response)
            forecastInfoDao.insert(list.toForecastInfoEntities())
        }
    }

}