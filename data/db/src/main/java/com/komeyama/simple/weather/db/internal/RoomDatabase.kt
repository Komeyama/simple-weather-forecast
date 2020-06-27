package com.komeyama.simple.weather.db.internal

import com.komeyama.simple.weather.db.*
import com.komeyama.simple.weather.db.internal.dao.DetailCopyrightMainDao
import com.komeyama.simple.weather.db.internal.dao.DetailForecastDao
import com.komeyama.simple.weather.db.internal.dao.DetailLocationDao
import com.komeyama.simple.weather.db.internal.dao.ForecastMainInfoDao
import com.komeyama.simple.weather.db.internal.dao.PinpointLocationDao
import com.komeyama.simple.weather.db.internal.entity.mapper.toDetailForecastEntities
import com.komeyama.simple.weather.db.internal.entity.mapper.toForecastMainInfoEntities
import com.komeyama.simple.weather.db.internal.entity.mapper.toPinpointLocationEntities
import com.komeyama.simple.weather.model.DetailForecastResponse
import com.komeyama.simple.weather.model.MainResponse
import com.komeyama.simple.weather.model.PinpointLocationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RoomDatabase @Inject constructor(
    private val cacheDatabase: CacheDatabase,
    private val forecastMainInfoDao: ForecastMainInfoDao,
    private val detailLocationDao: DetailLocationDao,
    private val detailForecastDao: DetailForecastDao,
    private val detailCopyrightEntityMainDao: DetailCopyrightMainDao,
    private val pinpointLocationDao: PinpointLocationDao
) : ForecastInfoDatabase,
    ForecastMainDatabase,
    DetailDescriptionDatabase,
    DetailForecastDatabase,
    DetailImageDatabase,
    DetailLocationDatabase,
    DetailCopyrightMainDatabase,
    PinpointLocationDatabase
{

    override suspend fun forecastInfo(): List<ForecastInfoEntity> = withContext(Dispatchers.IO) {
        cacheDatabase.forecastInfoDao().forecastInfo()
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

    override fun detailImageEntity(): List<DetailImageEntity> {
        return cacheDatabase.detailImageDao().detailImageInfo()
    }

    override fun detailLocationEntity(): List<DetailLocationEntity> {
        return cacheDatabase.detailLocationDao().detailLocationInfo()
    }

    override fun detailCopyrightEntity(): List<DetailCopyrightMainEntity> {
        return cacheDatabase.detailCopyrightDao().detailCopyright()
    }

    override fun pinpointLocationEntity(): List<PinpointLocationEntity> {
        return cacheDatabase.pinpointLocation().pinpointLocations()
    }

    override suspend fun save(mainResponse: MainResponse, detailForecastResponse: List<DetailForecastResponse>, pinpointLocationResponse: List<PinpointLocationResponse>) {
        withContext(Dispatchers.IO) {
            val list: MutableList<MainResponse> = mutableListOf()
            list.add(mainResponse)
            forecastMainInfoDao.insert(list.toForecastMainInfoEntities())
            detailForecastDao.insert(detailForecastResponse.toDetailForecastEntities())
            pinpointLocationDao.insert(pinpointLocationResponse.toPinpointLocationEntities())
        }
    }

}