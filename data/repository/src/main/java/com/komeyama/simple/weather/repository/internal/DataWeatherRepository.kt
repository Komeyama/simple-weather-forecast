package com.komeyama.simple.weather.repository.internal

import com.komeyama.simple.weather.model.ForecastInfo
import com.komeyama.simple.weather.api.ForecastApi
import com.komeyama.simple.weather.db.ForecastDatabase
import com.komeyama.simple.weather.model.DetailDescriptionResponse
import com.komeyama.simple.weather.model.DetailLocationResponse
import com.komeyama.simple.weather.model.Response
import com.komeyama.simple.weather.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

internal class DataWeatherRepository @Inject constructor(
    private val forecastApi: ForecastApi,
    private val forecastDatabase: ForecastDatabase
) : WeatherRepository {

    override fun dummyFunc() {
        val forecastInfo: ForecastInfo? = runBlocking {
            forecastApi.getForecastInfo("410020").body()
        }
        Timber.d(
            "dummyFunc:api: %s\n :db  %s",
            forecastInfo.toString(),
            forecastDatabase.toString()
        )
    }

    override suspend fun dummySave() {
        forecastDatabase.save(
            Response(
                "a1",
                "b1",
                DetailDescriptionResponse("a2", "b2"),
                DetailLocationResponse("a3", "b3", "b3")
            )
        )
    }

}

@Module
internal abstract class RepositoryModule {

    @Binds
    abstract fun weatherRepository(impl: DataWeatherRepository): WeatherRepository
}