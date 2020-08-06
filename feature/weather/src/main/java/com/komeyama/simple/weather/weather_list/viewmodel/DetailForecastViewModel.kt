package com.komeyama.simple.weather.weather_list.viewmodel

import androidx.lifecycle.ViewModel
import com.komeyama.simple.weather.repository.ForecastRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import timber.log.Timber

class DetailForecastViewModel @AssistedInject constructor(
    @Assisted private val cityId: String,
    private val weatherRepository: ForecastRepository
) : ViewModel() {

    fun dummyFunc() {
        Timber.d("dummy func city id: %s", cityId)
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(cityId: String): DetailForecastViewModel
    }
}