package com.komeyama.simple.weather.weather_list.viewmodel

import androidx.lifecycle.*
import com.komeyama.simple.weather.core.extentions.combine
import com.komeyama.simple.weather.model.*
import com.komeyama.simple.weather.repository.ForecastRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class SearchPlacesViewModel @Inject constructor(
    private val weatherRepository: ForecastRepository
) : ViewModel() {

    data class UiModel(val searchResult: SearchResult) {
        companion object {
            val EMPTY = UiModel(SearchResult.EMPTY)
        }
    }

    var cityList: MutableLiveData<List<CityIds>> = MutableLiveData()

    private val cityInfoLiveData: LiveData<List<CityIds>> = liveData {
        emitSource(
            cityList
        )
    }

    fun refreshRepository(prefectureId: String) {
        viewModelScope.launch {
            try {
                weatherRepository.refresh(prefectureId)
            } catch (e: Exception) {
                Timber.d("Fail weatherRepository.refresh(): %s", e.toString())
            }
        }
    }

    private val searchQueryLiveData: MutableLiveData<String> = MutableLiveData("")

    /**
     * TODO: loading state
     */
    val uiModel: LiveData<UiModel> = combine(
        initialValue = UiModel.EMPTY,
        liveData1 = cityInfoLiveData,
        liveData2 = searchQueryLiveData
    ) { current: UiModel,
        cityList: List<CityIds>,
        searchQuery: String
        ->
        val searchResult = cityList.search(searchQuery)
        UiModel(
            searchResult
        )
    }

    fun updateSearchQuery(query: String) {
        cityList.postValue(CityIds.values().toList())
        searchQueryLiveData.postValue(query)
    }

    private fun List<CityIds>.search(query: String): SearchResult {
        return SearchResult(
            this.filter {
                find(query, it.id)
            },
            query
        )
    }

    private fun find(query: String, vararg strings: String?): Boolean {
        return strings
            .find { it?.contains(query, true) ?: false } != null
    }
}