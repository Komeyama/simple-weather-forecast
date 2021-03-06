package com.komeyama.simple.weather.weather_list.viewmodel

import androidx.lifecycle.*
import com.komeyama.simple.weather.core.extentions.combine
import com.komeyama.simple.weather.model.*
import com.komeyama.simple.weather.repository.ForecastRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class FavoriteSiteViewModel @Inject constructor(
    private val weatherRepository: ForecastRepository
) : ViewModel() {

    data class FavoritePlaceListUiData(
        val favoritePlaceTopPageContents: List<FavoritePlaceTopContent>,
        val favoriteIds: List<String>
    ) {
        companion object {
            val EMPTY = FavoritePlaceListUiData(
                listOf(
                    FavoritePlaceTopContent(
                        cityName = "",
                        imgUrl = "",
                        telop = "",
                        minTemperature = "",
                        maxTemperature = "",
                        lat = 0.0F,
                        lon = 0.0F,
                        isFavorite = false
                    )
                ), listOf("")
            )
        }
    }

    private val forecastFavoritePlaceListInfoLiveData: LiveData<List<FavoritePlaceTopContent>> = liveData {
        emitSource(
            weatherRepository.forecastContents().toFavoritePlaceTopContentFlow().asLiveData()
        )
    }

    private val favoriteStateLiveData: LiveData<List<String>> = liveData {
        emitSource(
            weatherRepository.getFavoriteIds().asLiveData()
        )
    }

    val favoritePlaceListUiData: LiveData<FavoritePlaceListUiData> = combine(
        initialValue = FavoritePlaceListUiData.EMPTY,
        liveData1 = forecastFavoritePlaceListInfoLiveData,
        liveData2 = favoriteStateLiveData
    ) { current: FavoritePlaceListUiData,
        favoritePlaceTopContent: List<FavoritePlaceTopContent>,
        favoriteStateList: List<String>
        ->
        FavoritePlaceListUiData(
            favoritePlaceTopPageContents = favoritePlaceTopContent.makeFavoritePlaceTopContents(favoriteStateList),
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
}