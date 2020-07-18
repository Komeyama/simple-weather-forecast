package com.komeyama.simple.weather.weather_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.komeyama.simple.weather.model.TopPageContent
import com.komeyama.simple.weather.model.toTopPageContentFlow
import com.komeyama.simple.weather.repository.ForecastRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class WeatherDetailListViewModel @AssistedInject constructor(
    @Assisted private val prefectureId: String,
    private val weatherRepository: ForecastRepository
) : ViewModel() {

    val forecastDetailListInfoLiveData: LiveData<List<TopPageContent>> = liveData {
        emitSource(
            weatherRepository.forecastContents().toTopPageContentFlow().asLiveData()
        )
    }

}