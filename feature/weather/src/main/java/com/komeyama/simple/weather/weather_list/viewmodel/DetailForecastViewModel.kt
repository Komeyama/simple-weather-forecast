package com.komeyama.simple.weather.weather_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.komeyama.simple.weather.model.DetailForecastInfo
import com.komeyama.simple.weather.repository.ForecastRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import timber.log.Timber

class DetailForecastViewModel @AssistedInject constructor(
    @Assisted private val cityId: String,
    @Assisted private val cityLat: Float,
    @Assisted private val cityLon: Float,
    private val weatherRepository: ForecastRepository
) : ViewModel() {

    val detailForecastInfoLiveData: LiveData<DetailForecastInfo> = liveData {
        emitSource(
            weatherRepository.detailForecastContents(cityId).asLiveData()
        )
    }

    fun dummyLatLong() {
        Timber.d("lat lon: %s %s", cityLat, cityLon)
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(cityId: String, cityLat: Float, cityLon: Float): DetailForecastViewModel
    }
}