package com.komeyama.simple.weather.db.internal

import com.komeyama.simple.weather.db.*
import com.komeyama.simple.weather.db.internal.dao.DetailLocationDao
import com.komeyama.simple.weather.db.internal.dao.ForecastInfoDao
import com.komeyama.simple.weather.db.internal.entity.mapper.toForecastInfoEntities
import com.komeyama.simple.weather.model.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RoomDatabase @Inject constructor(
    private val cacheDatabase: CacheDatabase,
    private val forecastInfoDao: ForecastInfoDao,
    private val detailLocationDao: DetailLocationDao
) : ForecastDatabase, DetailDescriptionDatabase, DetailLocationDatabase {
    override fun forecastInfoEntity(): List<ForecastInfoEntity> {
        return cacheDatabase.forecastInfoDao().forecastInfo()
    }

    override fun detailDescriptionEntity(): List<DetailDescriptionEntity> {
        return cacheDatabase.detailDescriptionDao().detailDescriptionInfo()
    }

    override fun detailLocationEntity(): List<DetailLocationEntity> {
        return cacheDatabase.detailLocationDao().detailLocationInfo()
    }

    override suspend fun save(response: Response) {
        withContext(Dispatchers.IO) {
            val list: MutableList<Response> = mutableListOf()
            list.add(response)
            forecastInfoDao.insert(list.toForecastInfoEntities())
        }
    }
}