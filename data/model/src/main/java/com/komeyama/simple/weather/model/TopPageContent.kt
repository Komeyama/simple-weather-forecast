package com.komeyama.simple.weather.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class TopPageContent(
    val prefectureName: String,
    var imgUrl: String,
    val telop: String,
    val minTemperature: String,
    var maxTemperature: String
)

fun Flow<List<ForecastInfo>>.toTopPageContentFlow(): Flow<List<TopPageContent>> {
    return this.map {
        it.toTopPageContentList()
    }
}

fun List<ForecastInfo>.toTopPageContentList(): List<TopPageContent> {
    val forecastPrefectureInfo = mutableListOf<ForecastInfo>()
    this.map { forecastInfo ->
        PrefectureIds.values().forEach {
            if (it.id == linkToPrefectureId(forecastInfo.link)) {
                forecastPrefectureInfo.add(forecastInfo)
            }
        }
    }

    return forecastPrefectureInfo.map {
        it.toTopPageContent()
    }
}

fun ForecastInfo.toTopPageContent(): TopPageContent {

    return TopPageContent(
        this.location?.prefecture ?: "---",
        this.forecasts[0].image?.url!!,
        this.forecasts[0].telop ?: "---",
        this.forecasts[0].temperature?.min?.celsius ?: "---",
        this.forecasts[0].temperature?.max?.celsius ?: "---"
    )
}

/**
 * This method get prefecture id from link.
 * e.g.)
 * from http://weather.livedoor.com/area/forecast/016010 to 016010
 */
fun linkToPrefectureId(link: String?): String? {
    return link?.removePrefix("http://weather.livedoor.com/area/forecast/")
}