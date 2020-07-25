package com.komeyama.simple.weather.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class FavoritePlaceTopContent (
    val cityName: String,
    val imgUrl: String,
    val telop: String,
    val minTemperature: String,
    val maxTemperature: String,
    val isFavorite: Boolean = false
)

fun List<FavoritePlaceTopContent>.makeFavoritePlaceTopContents(favoriteList: List<String>): List<FavoritePlaceTopContent> {
    return this.map {
        it.makeFavoritePlaceTopContent(favoriteList)
    }
}

fun FavoritePlaceTopContent.makeFavoritePlaceTopContent(isFavoriteList: List<String>): FavoritePlaceTopContent {
    var favoriteState: Boolean
    CityIds.values().firstOrNull { it.cityName == this.cityName }?.id.apply {
        favoriteState = isFavoriteList.contains(this)
    }

    return FavoritePlaceTopContent(
        cityName = this.cityName,
        imgUrl = this.imgUrl,
        telop = this.telop,
        minTemperature = this.minTemperature,
        maxTemperature = this.maxTemperature,
        isFavorite = favoriteState)
}

fun Flow<List<ForecastInfo>>.toFavoritePlaceTopContentFlow(): Flow<List<FavoritePlaceTopContent>> {
    return this.map {
        it.toFavoritePlaceTopContentList()
    }
}

fun List<ForecastInfo>.toFavoritePlaceTopContentList(): List<FavoritePlaceTopContent> {
    return this.map {
        it.toFavoritePlaceTopContent()
    }
}

fun ForecastInfo.toFavoritePlaceTopContent(): FavoritePlaceTopContent {
    return FavoritePlaceTopContent(
        cityName = this.location?.city ?: "---",
        imgUrl =  this.forecasts[0].image?.url!!,
        telop = this.forecasts[0].telop ?: "---",
        minTemperature = this.forecasts[0].temperature?.min?.celsius ?: "---",
        maxTemperature = this.forecasts[0].temperature?.max?.celsius ?: "---",
        isFavorite = this.isFavorite
    )
}