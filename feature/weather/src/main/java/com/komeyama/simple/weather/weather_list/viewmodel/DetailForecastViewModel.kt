package com.komeyama.simple.weather.weather_list.viewmodel

import androidx.lifecycle.*
import com.komeyama.simple.weather.model.DetailForecastInfo
import com.komeyama.simple.weather.model.WeeklyForecastInfo
import com.komeyama.simple.weather.repository.ForecastRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch
import java.lang.Exception

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

    fun hasLatLong(): Boolean {
        if (cityLat == 0F && cityLon == 0F) {
            return false
        }
        return true
    }

    val dailyForecastInfoLiveData: LiveData<WeeklyForecastInfo> = liveData {
        emitSource(
            weatherRepository.dailyForecastContent(cityLat, cityLon).asLiveData()
        )
    }

    val favoriteIdLiveData: LiveData<List<String>> = liveData {
        emitSource(
            weatherRepository.getFavoriteIds().asLiveData()
        )
    }

    fun dailyForecastInfoLiveDataWithLatLon(lat: Float, lon: Float): LiveData<WeeklyForecastInfo>? {
        var weeklyForecastInfo : LiveData<WeeklyForecastInfo>? = null
        viewModelScope.launch {
            weeklyForecastInfo = weatherRepository.dailyForecastContent(lat, lon).asLiveData()
        }
        return weeklyForecastInfo
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
        fun create(cityId: String, cityLat: Float, cityLon: Float): DetailForecastViewModel
    }
}