package com.komeyama.simple.weather.weather_list.viewmodel

import androidx.lifecycle.*
import com.komeyama.simple.weather.core.extentions.combine
import com.komeyama.simple.weather.model.SubPageContent
import com.komeyama.simple.weather.model.makeSubPageContents
import com.komeyama.simple.weather.model.toSubPageContentFlow
import com.komeyama.simple.weather.repository.ForecastRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class WeatherDetailListViewModel @AssistedInject constructor(
    @Assisted private val prefectureId: String,
    private val weatherRepository: ForecastRepository
) : ViewModel() {

    data class DetailListUiData(
        val subPageContents: List<SubPageContent>,
        val favoriteIds: List<String>
    ) {
        companion object {
            val EMPTY = DetailListUiData(
                listOf(
                    SubPageContent(
                        cityName = "",
                        imgUrl = "",
                        telop = "",
                        minTemperature = "",
                        maxTemperature = "",
                        isFavorite = false
                    )
                ), listOf("")
            )
        }
    }

    private val forecastDetailListInfoLiveData: LiveData<List<SubPageContent>> = liveData {
        emitSource(
            weatherRepository.forecastCityContents(prefectureId).toSubPageContentFlow().asLiveData()
        )
        try {
            weatherRepository.refresh(prefectureId)
        } catch (e: Exception) {
            Timber.d("Fail weatherRepository.refresh(): %s", e.toString())
        }
    }

    private val favoriteStateLiveData: LiveData<List<String>> = liveData {
        emitSource(
            weatherRepository.getFavoriteIds().asLiveData()
        )
    }

    val detailListUiData: LiveData<DetailListUiData> = combine(
        initialValue = DetailListUiData.EMPTY,
        liveData1 = forecastDetailListInfoLiveData,
        liveData2 = favoriteStateLiveData
    ) { current: DetailListUiData,
        forecastDetail: List<SubPageContent>,
        favoriteStateList: List<String>
        ->
        DetailListUiData(
            subPageContents = forecastDetail.makeSubPageContents(favoriteStateList),
            favoriteIds = favoriteStateList
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