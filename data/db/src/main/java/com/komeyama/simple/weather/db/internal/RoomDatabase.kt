package com.komeyama.simple.weather.db.internal

import com.komeyama.simple.weather.db.*
import com.komeyama.simple.weather.db.internal.dao.DetailCopyrightMainDao
import com.komeyama.simple.weather.db.internal.dao.DetailForecastDao
import com.komeyama.simple.weather.db.internal.dao.DetailLocationDao
import com.komeyama.simple.weather.db.internal.dao.ForecastMainInfoDao
import com.komeyama.simple.weather.db.internal.dao.PinpointLocationDao
import com.komeyama.simple.weather.db.internal.entity.mapper.*
import com.komeyama.simple.weather.db.internal.entity.mapper.toDetailForecastEntities
import com.komeyama.simple.weather.db.internal.entity.mapper.toForecastMainInfoEntities
import com.komeyama.simple.weather.db.internal.entity.mapper.toPinpointLocationEntities
import com.komeyama.simple.weather.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RoomDatabase @Inject constructor(
    private val cacheDatabase: CacheDatabase,
    private val forecastMainInfoDao: ForecastMainInfoDao,
    private val detailLocationDao: DetailLocationDao,
    private val detailForecastDao: DetailForecastDao,
    private val detailCopyrightMainDao: DetailCopyrightMainDao,
    private val pinpointLocationDao: PinpointLocationDao,
    private val pinpointLocationOfCopyDao: PinpointLocationDao
) : ForecastInfoDatabase,
    ForecastMainDatabase,
    DetailCopyrightDatabase,
    DetailCopyrightMainDatabase,
    DetailDescriptionDatabase,
    DetailForecastDatabase,
    DetailImageDatabase,
    DetailImageOfCopyrightDatabase,
    DetailLocationDatabase,
    DetailTemperatureDatabase,
    PinpointLocationDatabase,
    PinpointLocationOfCopyDatabase,
    TemperatureDatabase {

    override suspend fun forecastInfo(): List<ForecastInfoEntity> = withContext(Dispatchers.IO) {
        cacheDatabase.forecastInfoDao().forecastInfo()
    }

    override fun forecastMainInfoEntity(): List<ForecastMainInfoEntity> {
        return cacheDatabase.forecastMainInfoDao().forecastMainInfo()
    }

    override fun detailCopyrightEntity(): List<DetailCopyrightEntity> {
        return cacheDatabase.detailCopyrightDao().detailCopyLightInfo()
    }

    override fun detailCopyrightMainEntity(): List<DetailCopyrightMainEntity> {
        return cacheDatabase.detailCopyrightMainDao().detailCopyrightMainInfo()
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

    override fun detailImageOfCopyrightEntity(): List<DetailImageEntity> {
        return cacheDatabase.detailImageDao().detailImageOfCopyrightInfo()
    }

    override fun detailLocationEntity(): List<DetailLocationEntity> {
        return cacheDatabase.detailLocationDao().detailLocationInfo()
    }

    override fun detailTemperatureEntity(): List<DetailTemperatureEntity> {
        return cacheDatabase.detailTemperatureDao().detailTemperatureInfo()
    }

    override fun pinpointLocationEntity(): List<PinpointLocationEntity> {
        return cacheDatabase.pinpointLocationDao().pinpointLocations()
    }

    override fun pinpointLocationOfCopyEntity(): List<PinpointLocationEntity> {
        return cacheDatabase.pinpointLocationDao().pinpointLocationsOfCopy()
    }

    override fun temperatureEntity(): List<TemperatureEntity> {
        return cacheDatabase.temperatureDao().temperatureInfo()
    }

    override suspend fun save(
        forecastInfo: ForecastInfo?
    ) {
        withContext(Dispatchers.IO) {
            val list: MutableList<ForecastInfo?> = mutableListOf()

            list.add(forecastInfo)
            forecastMainInfoDao.insert(list.toForecastMainInfoEntities())
            detailForecastDao.insert(forecastInfo?.forecasts?.toDetailForecastEntities())
            pinpointLocationDao.insert(forecastInfo?.pinpointLocations?.toPinpointLocationEntities())
            detailCopyrightMainDao.insert(forecastInfo?.copyright?.toDetailCopyrightMainEntity())
            pinpointLocationOfCopyDao.insertOfCopy(forecastInfo?.copyright?.provider?.toPinpointLocationOfCopyEntities())
        }
    }
}