package com.komeyama.simple.weather.weather_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.komeyama.simple.weather.repository.WeatherRepository
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class WeatherListViewModel @Inject constructor(
        private val weatherRepository: WeatherRepository
) : ViewModel() {

    fun callWeatherRepositoryMethod() {
        weatherRepository.dummyFunc()
    }

    fun callWeatherRepositoryDbMethod() {
        viewModelScope.launch {
            try {
                weatherRepository.dummySave()
            } catch (error: IOException) {}
        }
    }

}