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
    return this.map {
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