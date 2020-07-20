package com.komeyama.simple.weather.weather_list.viewmodel

import androidx.lifecycle.*
import com.komeyama.simple.weather.model.TopPageContent
import com.komeyama.simple.weather.model.toTopPageContentFlow
import com.komeyama.simple.weather.repository.ForecastRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class WeatherListViewModel @Inject constructor(
    private val weatherRepository: ForecastRepository
) : ViewModel() {

    val forecastInfoLiveData: LiveData<List<TopPageContent>> = liveData {
        emitSource(
            weatherRepository.forecastContents().toTopPageContentFlow().asLiveData()
        )
    }

    fun callWeatherRepositoryMethod() {
        viewModelScope.launch {
            try {
                weatherRepository.refresh()
            } catch (e: Exception) {
            }
        }
    }

    fun callWeatherRepositoryDbMethod() {
        viewModelScope.launch {
            try {
                weatherRepository.dummySave()
            } catch (e: IOException) {
            }
        }
    }
}