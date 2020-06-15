package com.komeyama.simple.weather.repository.internal

import android.util.Log
import dagger.Binds
import dagger.Module
import javax.inject.Inject

internal class DataWeatherRepository @Inject constructor() : WeatherRepository {

    override fun dummyFunc() {
        Log.d("WeatherRepository dummy", "dummy!!")
    }

}

@Module
internal abstract class RepositoryModule {

    @Binds abstract fun weatherRepository(impl: DataWeatherRepository): WeatherRepository
}