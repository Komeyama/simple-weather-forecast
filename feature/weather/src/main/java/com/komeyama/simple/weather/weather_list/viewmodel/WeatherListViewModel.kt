package com.komeyama.simple.weather.weather_list.viewmodel

import androidx.lifecycle.ViewModel
import com.komeyama.simple.weather.repository.WeatherRepository
import javax.inject.Inject

class WeatherListViewModel @Inject constructor(
        private val weatherRepository: WeatherRepository
) : ViewModel() {

    fun callWeatherRepositoryMethod() {
        weatherRepository.dummyFunc()
    }

}