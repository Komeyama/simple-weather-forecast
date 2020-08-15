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
    CityIds.values().firstOrNull { it.id.conversionsInSpecialCases() == this.cityName.conversionsInSpecialCases() }?.id.apply {
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

/**
 * TODO: fix emergency
 */
fun ForecastInfo.toFavoritePlaceTopContent(): FavoritePlaceTopContent {
    return FavoritePlaceTopContent(
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
        } ?: "" ,
        minTemperature = this.main?.temp_min.toString(),
        maxTemperature = this.main?.temp_max.toString(),
        isFavorite = this.isFavorite
    )
}

/**
 * TODO: fix emergency
 */
internal fun String.conversionsInSpecialCases(): String {

    return when {
        this.contains("Ō") -> {
            return this.replace("Ō", "O")
        }
        this.contains("ō") -> {
            return this.replace("ō", "o")
        }
        this.contains("Kochi-shi") -> {
            return this.replace("Kochi-shi", "Kochi")
        }
        else -> {
            this
        }
    }
}