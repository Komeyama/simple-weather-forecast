package com.komeyama.simple.weather.repository.internal

import android.util.Log
import com.komeyama.simple.weather.api.ForecastInfo
import com.komeyama.simple.weather.api.ForecastService
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class DataWeatherRepository @Inject constructor(
        private val forecastService: ForecastService
) : WeatherRepository {

    override fun dummyFunc() {
        val forecastInfo: ForecastInfo? = runBlocking {
            forecastService.getForecastInfo("410020").body()
        }
        Log.d("DataWeatherRepository", forecastInfo.toString())
    }
}

@Module
internal abstract class RepositoryModule {

    @Binds
    abstract fun weatherRepository(impl: DataWeatherRepository): WeatherRepository
}