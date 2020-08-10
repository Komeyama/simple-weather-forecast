package com.komeyama.simple.weather.db.internal

import android.util.Log
import androidx.room.withTransaction
import com.komeyama.simple.weather.db.*
import com.komeyama.simple.weather.db.internal.dao.ForecastMainInfoDao
import com.komeyama.simple.weather.db.internal.dao.WeatherDao
import com.komeyama.simple.weather.db.internal.entity.FavoritePlaceEntityImpl
import com.komeyama.simple.weather.db.internal.entity.WeatherEntity
import com.komeyama.simple.weather.db.internal.entity.mapper.*
import com.komeyama.simple.weather.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RoomDatabase @Inject constructor(
    private val cacheDatabase: CacheDatabase,
    private val forecastMainInfoDao: ForecastMainInfoDao,
    private val weatherDao: WeatherDao
) : FavoritePlaceDatabase,
    ForecastInfoDatabase,
    ForecastMainDatabase,
    WeatherDatabase {

    override suspend fun favoriteState(): List<FavoritePlaceEntity> {
        return cacheDatabase.favoritePlaceDao().favoritePlaceInfo()
    }

    override fun favoriteStateFlow(): Flow<List<FavoritePlaceEntity>> {
        return cacheDatabase.favoritePlaceDao().favoritePlaceInfoFlow()
    }

    override suspend fun saveFavoriteState(favoriteId: String) {
        cacheDatabase.withTransaction {
            var isForecastId = false
            val favoritePlaceInfo = cacheDatabase.favoritePlaceDao().favoritePlaceInfo()
            favoritePlaceInfo.forEach {
                if (it.forecastId == favoriteId) {
                    cacheDatabase.favoritePlaceDao().delete(favoriteId)
                    isForecastId = true
                }
            }
            if (!isForecastId) {
                cacheDatabase.favoritePlaceDao()
                    .insert(FavoritePlaceEntityImpl(forecastId = favoriteId))
            }
        }
    }

    override suspend fun forecastInfo(): List<ForecastInfoEntity> = withContext(Dispatchers.IO) {
        cacheDatabase.forecastInfoDao().forecastInfo()
    }

    override suspend fun forecastInfoFlow(): Flow<List<ForecastInfoEntity>> {
        return cacheDatabase.forecastInfoDao().forecastInfoFlow()
    }

    override fun forecastMainInfoEntity(): List<ForecastMainInfoEntity> {
        return cacheDatabase.forecastMainInfoDao().forecastMainInfo()
    }

    override fun weatherDatabaseInfoEntity(): List<WeatherEntity> {
        return cacheDatabase.weatherDao().weatherInfo()
    }

    override suspend fun save(
        forecastInfo: List<ForecastInfo?>
    ) {
        cacheDatabase.withTransaction {
//            detailForecastDao.deleteAll()
//            pinpointLocationDao.deleteAll()
//            detailCopyrightMainDao.deleteAll()
//            pinpointLocationOfCopyDao.deleteAll()

            forecastMainInfoDao.deleteAll()
            weatherDao.deleteAll()

            var previousForecastInfoSize = 0
            forecastInfo.forEachIndexed { id, forecastInfo ->

                forecastMainInfoDao.insert(forecastInfo?.toForecastMainInfoEntity(id))
                weatherDao.insert(forecastInfo?.toWeatherEntity(id))

//                detailForecastDao.insert(forecastInfo?.forecasts?.toDetailForecastEntities(id))
//
//                pinpointLocationDao.insert(
//                    forecastInfo?.pinpointLocations?.toPinpointLocationEntities(
//                        id,
//                        previousForecastInfoSize
//                    )
//                )
//                previousForecastInfoSize += forecastInfo?.pinpointLocations?.size ?: 0
//
//                detailCopyrightMainDao.insert(
//                    forecastInfo?.copyright?.toDetailCopyrightMainEntity(
//                        id
//                    )
//                )
//                pinpointLocationOfCopyDao.insertOfCopy(
//                    forecastInfo?.copyright?.provider?.toPinpointLocationOfCopyEntities(
//                        id
//                    )
//                )
            }
        }
    }

}