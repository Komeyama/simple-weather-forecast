package com.komeyama.simple.weather.weather_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.komeyama.simple.weather.repository.ForecastRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class WeatherListViewModel @Inject constructor(
    private val weatherRepository: ForecastRepository
) : ViewModel() {

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

    fun callWeatherRepositoryDbLoadMethod() {
        viewModelScope.launch {
            try {
                weatherRepository.dummyLoad()
            } catch (e: IOException) {
            }
        }
    }

}