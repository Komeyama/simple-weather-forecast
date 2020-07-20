package com.komeyama.simple.weather.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class SubPageContent (
    val cityName: String,
    var imgUrl: String,
    val telop: String,
    val minTemperature: String,
    var maxTemperature: String
)

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
            if (it.id == linkToPrefectureId(forecastInfo.link)) {
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
        this.location?.city ?: "---",
        this.forecasts[0].image?.url!!,
        this.forecasts[0].telop ?: "---",
        this.forecasts[0].temperature?.min?.celsius ?: "---",
        this.forecasts[0].temperature?.max?.celsius ?: "---"
    )
}