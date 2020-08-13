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
        /**
         * TODO: fix emergency
         */
//        PrefectureIds.values().forEach {
//
//            if (it.id == linkToForecastId(forecastInfo.link)) {
//                forecastPrefectureInfo.add(forecastInfo)
//            }
//        }
        forecastPrefectureInfo.add(forecastInfo)
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
        prefectureName = this.name ?: "---",
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
        } ?: "" ,
        minTemperature = this.main?.temp_min?.toFromKelvinToCelsius()?.toInt().toString(),
        maxTemperature = this.main?.temp_max?.toFromKelvinToCelsius()?.toInt().toString()
    )
}