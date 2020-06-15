package com.komeyama.simple.weather.repository.internal

import android.util.Log
import dagger.Module
import javax.inject.Inject

class WeatherRepository @Inject constructor() {

    fun dummyFunc() {
        Log.d("WeatherRepository dummy", "dummy!!")
    }

}

@Module
abstract class RepositoryModule {

    abstract fun weatherRepository(): WeatherRepository
}