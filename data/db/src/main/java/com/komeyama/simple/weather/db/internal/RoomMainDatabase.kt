package com.komeyama.simple.weather.db.internal

import com.komeyama.simple.weather.db.*
import com.komeyama.simple.weather.db.internal.dao.DetailForecastDao
import com.komeyama.simple.weather.db.internal.dao.DetailLocationDao
import com.komeyama.simple.weather.db.internal.dao.ForecastMainInfoDao
import com.komeyama.simple.weather.db.internal.entity.mapper.toDetailForecastEntities
import com.komeyama.simple.weather.db.internal.entity.mapper.toForecastMainInfoEntities
import com.komeyama.simple.weather.model.DetailForecastResponse
import com.komeyama.simple.weather.model.MainResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RoomMainDatabase @Inject constructor(
    private val cacheDatabase: CacheDatabase,
    private val forecastMainInfoDao: ForecastMainInfoDao,
    private val detailLocationDao: DetailLocationDao,
    private val detailForecastDao: DetailForecastDao
) : ForecastInfoDatabase,
    ForecastMainDatabase,
    DetailDescriptionDatabase,
    DetailForecastDatabase,
    DetailLocationDatabase {

    override fun forecastInfo(): List<ForecastInfoEntity> {
        return cacheDatabase.forecastInfoDao().forecastInfo()
    }

    override fun forecastMainInfoEntity(): List<ForecastMainInfoEntity> {
        return cacheDatabase.forecastMainInfoDao().forecastMainInfo()
    }

    override fun detailDescriptionEntity(): List<DetailDescriptionEntity> {
        return cacheDatabase.detailDescriptionDao().detailDescriptionInfo()
    }

    override fun detailForecastEntity(): List<DetailForecastEntity> {
        return cacheDatabase.detailForecastDao().detailForecast()
    }

    override fun detailLocationEntity(): List<DetailLocationEntity> {
        return cacheDatabase.detailLocationDao().detailLocationInfo()
    }

    override suspend fun save(response: MainResponse, response2: List<DetailForecastResponse>) {
        withContext(Dispatchers.IO) {
            val list: MutableList<MainResponse> = mutableListOf()
            list.add(response)
            forecastMainInfoDao.insert(list.toForecastMainInfoEntities())
            detailForecastDao.insert(response2.toDetailForecastEntities())
        }
    }
}