package com.komeyama.simple.weather.db

interface ForecastMainInfoEntity {
    var forecastId: Int
    var title: String?
    var link: String?
    var publicTime: String?
    val detailLocation: DetailLocationEntity?
    val description: DetailDescriptionEntity?
}