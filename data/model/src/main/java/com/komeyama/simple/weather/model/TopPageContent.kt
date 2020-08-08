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
            /**
             * TODO: fix emergency
             */
//            if (it.id == linkToForecastId(forecastInfo.link)) {
//                forecastPrefectureInfo.add(forecastInfo)
//            }
        }
    }

    return forecastPrefectureInfo.map {
        it.toTopPageContent()
    }
}

/**
 * TODO: fix emergency
 */
fun ForecastInfo.toTopPageContent(): TopPageContent {

    return TopPageContent(
        this.name ?: "---",
        "",
        "---",
        this.main?.temp_min.toString(),
        this.main?.temp_max.toString()
    )
}