package com.komeyama.simple.weather.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class SubPageContent (
    val cityName: String,
    val imgUrl: String,
    val telop: String,
    val minTemperature: String,
    val maxTemperature: String,
    val isFavorite: Boolean = false
)

fun List<SubPageContent>.makeSubPageContents(favoriteList: List<String>): List<SubPageContent> {
    return this.map {
        it.makeSubSubPageContent(favoriteList)
    }
}

fun SubPageContent.makeSubSubPageContent(isFavoriteList: List<String>): SubPageContent {
    var favoriteState: Boolean
    CityIds.values().firstOrNull { it.cityName == this.cityName }?.id.apply {
        favoriteState = isFavoriteList.contains(this)
    }

    return SubPageContent(
        cityName = this.cityName,
        imgUrl = this.imgUrl,
        telop = this.telop,
        minTemperature = this.minTemperature,
        maxTemperature = this.maxTemperature,
        isFavorite = favoriteState)
}

fun Flow<List<ForecastInfo>>.toSubPageContentFlow(prefectureId: String): Flow<List<SubPageContent>> {
    return this.map {
        it.toSubPageContentList(prefectureId)
    }
}

fun List<ForecastInfo>.toSubPageContentList(prefectureId: String): List<SubPageContent> {
    val cityIdList= CityIds.values().filter {
        it.prefectureId == prefectureId
    }

    val forecastCityInfo = mutableListOf<ForecastInfo>()
    this.map { forecastInfo ->
        cityIdList.forEach {
            if (it.id == linkToForecastId(forecastInfo.link)) {
                forecastCityInfo.add(forecastInfo)
            }
        }
    }

    return forecastCityInfo.map {
        it.toSubPageContent()
    }
}

fun ForecastInfo.toSubPageContent(): SubPageContent {
    return SubPageContent(
        cityName = this.location?.city ?: "---",
        imgUrl =  this.forecasts[0].image?.url!!,
        telop = this.forecasts[0].telop ?: "---",
        minTemperature = this.forecasts[0].temperature?.min?.celsius ?: "---",
        maxTemperature = this.forecasts[0].temperature?.max?.celsius ?: "---",
        isFavorite = this.isFavorite
    )
}