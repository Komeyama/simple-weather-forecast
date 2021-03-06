package com.komeyama.simple.weather.weather_list.viewmodel

import androidx.lifecycle.*
import com.komeyama.simple.weather.model.TopPageContent
import com.komeyama.simple.weather.model.toTopPageContentFlow
import com.komeyama.simple.weather.repository.ForecastRepository
import javax.inject.Inject

class WeatherListViewModel @Inject constructor(
    private val weatherRepository: ForecastRepository
) : ViewModel() {

    val forecastInfoLiveData: LiveData<List<TopPageContent>> = liveData {
        emitSource(
            weatherRepository.forecastPrefectureContents().toTopPageContentFlow().asLiveData()
        )
    }
}