package com.komeyama.simple.weather.weather_list.viewmodel

import androidx.lifecycle.*
import com.komeyama.simple.weather.model.SubPageContent
import com.komeyama.simple.weather.model.toSubPageContentFlow
import com.komeyama.simple.weather.repository.ForecastRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch
import java.lang.Exception

class WeatherDetailListViewModel @AssistedInject constructor(
    @Assisted private val prefectureId: String,
    private val weatherRepository: ForecastRepository
) : ViewModel() {

    val forecastDetailListInfoLiveData: LiveData<List<SubPageContent>> = liveData {
        emitSource(
            weatherRepository.forecastContents().toSubPageContentFlow(prefectureId).asLiveData()
        )
    }


    fun favorite(favoriteId: String) {
        viewModelScope.launch {
            try {
                weatherRepository.toggleFavorite(favoriteId)
            } catch (e: Exception) {
            }
        }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(prefectureId: String): WeatherDetailListViewModel
    }

}