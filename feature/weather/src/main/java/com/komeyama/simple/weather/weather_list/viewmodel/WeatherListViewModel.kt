package com.komeyama.simple.weather.weather_list.viewmodel

import androidx.lifecycle.*
import com.komeyama.simple.weather.model.TopPageContent
import com.komeyama.simple.weather.model.toTopPageContentFlow
import com.komeyama.simple.weather.repository.ForecastRepository
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class WeatherListViewModel @Inject constructor(
    private val weatherRepository: ForecastRepository
) : ViewModel() {

    val forecastInfoLiveData: LiveData<List<TopPageContent>> = liveData {
        emitSource(
            weatherRepository.forecastContents().toTopPageContentFlow().asLiveData()
        )
        try {
            weatherRepository.refresh()
        } catch (e: Exception) {
            Timber.d("Fail weatherRepository.refresh(): %s", e.toString())
        }
    }
}