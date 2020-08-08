package com.komeyama.simple.weather.repository.internal

import com.komeyama.simple.weather.api.ForecastApi
import com.komeyama.simple.weather.db.FavoritePlaceDatabase
import com.komeyama.simple.weather.db.ForecastInfoDatabase
import com.komeyama.simple.weather.model.*
import com.komeyama.simple.weather.repository.ForecastRepository
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DataWeatherRepository @Inject constructor(
    private val favoritePlaceDatabase: FavoritePlaceDatabase,
    private val forecastApi: ForecastApi,
    private val forecastInfoDatabase: ForecastInfoDatabase
) : ForecastRepository {

    /**
     *  todo: very rate!
     */
    override suspend fun refresh() {
        val forecastInfoList = forecastApi.getAllCityForecastList()
        forecastInfoDatabase.save(forecastInfoList)
    }

    override suspend fun forecastContents(): Flow<List<ForecastInfo>> {
        return forecastInfoDatabase.forecastInfoFlow().map { forecastInfoList ->
            forecastInfoList.toForecastInfoList()
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