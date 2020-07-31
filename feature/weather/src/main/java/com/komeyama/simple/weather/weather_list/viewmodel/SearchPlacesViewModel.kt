package com.komeyama.simple.weather.weather_list.viewmodel

import androidx.lifecycle.ViewModel
import com.komeyama.simple.weather.repository.ForecastRepository
import timber.log.Timber
import javax.inject.Inject

class SearchPlacesViewModel @Inject constructor(
    private val weatherRepository: ForecastRepository
) : ViewModel() {

    fun updateSearchQuery(query: String) {
        Timber.d("update search query: %s", query)
    }
}