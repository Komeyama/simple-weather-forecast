package com.komeyama.simple.weather.repository.internal

import com.komeyama.simple.weather.api.ForecastApi
import com.komeyama.simple.weather.db.FavoritePlaceDatabase
import com.komeyama.simple.weather.db.ForecastInfoDatabase
import com.komeyama.simple.weather.model.*
import com.komeyama.simple.weather.repository.ForecastRepository
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

internal class DataWeatherRepository @Inject constructor(
    private val favoritePlaceDatabase: FavoritePlaceDatabase,
    private val forecastApi: ForecastApi,
    private val forecastInfoDatabase: ForecastInfoDatabase
) : ForecastRepository {

    /**
     *  todo: very rate!
     */
//    override suspend fun refresh() {
//        val forecastInfoList = forecastApi.getAllCityForecastList()
//        forecastInfoDatabase.save(forecastInfoList)
//    }

    override suspend fun refresh(prefectureId: String) {
        val forecastInfoList: MutableList<ForecastInfo> = mutableListOf()
        CityIds.values().map {
            if (it.prefectureId == prefectureId) {
                forecastInfoList.add(forecastApi.getForecastListFromName(it.id))
            }
        }
        Timber.d("refresh! %s", forecastInfoList)
        forecastInfoDatabase.save(forecastInfoList)
    }

    override suspend fun forecastContents(): Flow<List<ForecastInfo>> {
        return forecastInfoDatabase.forecastInfoFlow().map { forecastInfoList ->
            forecastInfoList.toForecastInfoList()
        }
    }

    override suspend fun forecastPrefectureContents(): Flow<List<ForecastInfo>> {
        val forecastInfoList: MutableList<ForecastInfo> = mutableListOf()
        return forecastApi.getAllPrefectureForecastList().map {
            forecastInfoList.add(it)
            forecastInfoList as List<ForecastInfo>
        }.asFlow()
    }

    override suspend fun forecastCityContents(prefectureIds: String): Flow<List<ForecastInfo>> {
        val forecastInfoList: MutableList<ForecastInfo> = mutableListOf()
        return CityIds.values().map {
            if (it.prefectureId == prefectureIds) {
                forecastInfoList.add(forecastApi.getForecastListFromName(it.id))
            }
            forecastInfoList as List<ForecastInfo>
        }.asFlow()
    }

    override suspend fun forecastFavoriteCityContents(): Flow<List<ForecastInfo>> {
        val forecastInfoList: MutableList<ForecastInfo> = mutableListOf()
        return favoritePlaceDatabase.favoriteStateFlow().map { favoriteStateList ->
            favoriteStateList.map {
                Timber.d("favorite!!! %s", it.forecastId)
                forecastInfoList.add(forecastApi.getForecastListFromName(it.forecastId))
            }
            forecastInfoList as List<ForecastInfo>
        }
    }

    override suspend fun getFavoriteIds(): Flow<List<String>> {
        return favoritePlaceDatabase.favoriteStateFlow().map { favoriteStateList ->
            favoriteStateList.toStringList()
        }
    }

    override suspend fun toggleFavorite(favoriteId: String) {
        favoritePlaceDatabase.saveFavoriteState(favoriteId)
    }
}

@Module
internal abstract class RepositoryModule {

    @Binds
    abstract fun forecastRepository(impl: DataWeatherRepository): ForecastRepository
}