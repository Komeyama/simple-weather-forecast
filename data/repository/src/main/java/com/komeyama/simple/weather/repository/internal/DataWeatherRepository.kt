package com.komeyama.simple.weather.repository.internal

import com.komeyama.simple.weather.api.ForecastApi
import com.komeyama.simple.weather.db.DetailForecastDatabase
import com.komeyama.simple.weather.db.ForecastMainDatabase
import com.komeyama.simple.weather.model.*
import com.komeyama.simple.weather.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

internal class DataWeatherRepository @Inject constructor(
    private val forecastApi: ForecastApi,
    private val forecastMainDatabase: ForecastMainDatabase
) : WeatherRepository {

    override fun dummyFunc() {
        val forecastInfo: ForecastInfo? = runBlocking {
            forecastApi.getForecastInfo("410020").body()
        }
        Timber.d(
            "dummyFunc:api: %s\n :db  %s",
            forecastInfo.toString(),
            forecastMainDatabase.toString()
        )
    }

    override suspend fun dummySave() {
        forecastMainDatabase.save(
            MainResponse(
                //DetailForecastResponse("a1","b1","c1","d1","e1"),
                0,
                "a1",
                "b2",
                "c2",
                DetailDescriptionResponse("a3", "b3"),
                DetailLocationResponse("a4", "b4", "b4")
            ),
            listOf(
                DetailForecastResponse(
                    0,0, "aa1", "aa2", "aa3", "aa4", "aa5"
                ),
                DetailForecastResponse(
                    1,0, "aaa1", "aaa2", "aaa3", "aaa4", "aaa5"
                )
            ),
            listOf(
                PinpointLocationResponse(0,0,"bb1","bb2")
            )
        )
    }

}

@Module
internal abstract class RepositoryModule {

    @Binds
    abstract fun weatherRepository(impl: DataWeatherRepository): WeatherRepository
}