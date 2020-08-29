package com.komeyama.simple.weather.model

class SearchResult(
    val forecastInfo: List<CityIds>,
    val query: String?
) {
    fun isEmpty() = this == EMPTY

    companion object {
        val EMPTY = SearchResult(listOf(), null)
    }
}