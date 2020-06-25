package com.komeyama.simple.weather.model

data class Response(
    var detailForecastResponse: DetailForecastResponse,
    var title: String,
    var link: String,
    var publicTime: String,
    var detailDescriptionResponse: DetailDescriptionResponse,
    var detailLocationResponse: DetailLocationResponse
)

data class DetailLocationResponse(
    var area: String,
    var prefecture: String,
    var city: String
)

data class DetailDescriptionResponse(
    var text: String,
    var publicTime: String
)

data class DetailForecastResponse(
    var date: String,
    var dateLabel: String,
    var telop: String,
    var image: String,
    var temperature: String
)