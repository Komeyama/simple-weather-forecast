package com.komeyama.simple.weather.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class SubPageContent(
    val cityName: String,
    val imgUrl: String,
    val telop: String,
    val minTemperature: String,
    val maxTemperature: String,
    val cityLatLon: List<Float?>,
    val isFavorite: Boolean = false
)

fun List<SubPageContent>.makeSubPageContents(favoriteList: List<String>): List<SubPageContent> {
    return this.map {
        it.makeSubSubPageContent(favoriteList)
    }
}

fun SubPageContent.makeSubSubPageContent(isFavoriteList: List<String>): SubPageContent {
    var favoriteState: Boolean
    CityIds.values()
        .firstOrNull { it.id.conversionsInSpecialCases() == this.cityName.conversionsInSpecialCases() }?.id.apply {
            favoriteState = isFavoriteList.contains(this)
        }

    return SubPageContent(
        cityName = this.cityName,
        imgUrl = this.imgUrl,
        telop = this.telop,
        minTemperature = this.minTemperature,
        maxTemperature = this.maxTemperature,
        cityLatLon = this.cityLatLon,
        isFavorite = favoriteState
    )
}

fun Flow<List<ForecastInfo>>.toSubPageContentFlow(): Flow<List<SubPageContent>> {
    return this.map {
        it.toSubPageContentList()
    }
}

fun List<ForecastInfo>.toSubPageContentList(): List<SubPageContent> {
    return this.map {
        it.toSubPageContent()
    }
}

/**
 * TODO: fix emergency
 */
fun ForecastInfo.toSubPageContent(): SubPageContent {
    return SubPageContent(
        cityName = this.name ?: "---",
        imgUrl = this.weather?.let {
            if (it.isNotEmpty()) {
                it[0].icon?.toIconUrl()
            } else {
                ""
            }
        } ?: "",
        telop = this.weather?.let {
            if (it.isNotEmpty()) {
                it[0].main
            } else {
                ""
            }
        } ?: "",
        minTemperature = this.main?.temp_min?.toFromKelvinToCelsius()?.toInt().toString(),
        maxTemperature = this.main?.temp_max?.toFromKelvinToCelsius()?.toInt().toString(),
        cityLatLon = listOf(this.coord?.lat, this.coord?.lon),
        isFavorite = this.isFavorite
    )
}

/**
 * TODO: fix emergency
 */
internal fun String.toIconUrl(): String {
    return "http://openweathermap.org/img/wn/$this.png"
}