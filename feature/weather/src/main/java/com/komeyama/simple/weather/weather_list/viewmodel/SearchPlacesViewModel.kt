package com.komeyama.simple.weather.weather_list.viewmodel

import androidx.lifecycle.*
import com.komeyama.simple.weather.core.extentions.combine
import com.komeyama.simple.weather.model.*
import com.komeyama.simple.weather.repository.ForecastRepository
import javax.inject.Inject

class SearchPlacesViewModel @Inject constructor(
    private val weatherRepository: ForecastRepository
) : ViewModel() {

    data class UiModel(val searchResult: SearchResult) {
        companion object {
            val EMPTY = UiModel(SearchResult.EMPTY)
        }
    }

    private val forecastInfoLiveData: LiveData<List<ForecastInfo>> = liveData {
        emitSource(
            weatherRepository.forecastContents().asLiveData()
        )
    }

    private val searchQueryLiveData: MutableLiveData<String> = MutableLiveData("")

    /**
     * TODO: loading state
     */
    val uiModel: LiveData<UiModel> = combine(
        initialValue = UiModel.EMPTY,
        liveData1 = forecastInfoLiveData,
        liveData2 = searchQueryLiveData
    ) { current: UiModel,
        forecastDetail: List<ForecastInfo>,
        searchQuery: String
        ->
        val searchResult = forecastDetail.search(searchQuery)
        UiModel(
            searchResult
        )
    }

    fun updateSearchQuery(query: String) {
        searchQueryLiveData.postValue(query)
    }

    private fun List<ForecastInfo>.search(query: String): SearchResult {
        return SearchResult(
            this.filter {
                find(query, it.location?.city)
            },
            query
        )
    }

    private fun find(query: String, vararg strings: String?): Boolean {
        return strings
            .find { it?.contains(query, true) ?: false } != null
    }
}