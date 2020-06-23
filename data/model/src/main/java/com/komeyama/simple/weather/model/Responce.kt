package com.komeyama.simple.weather.model

data class Response(
    var id: String,
    var cityID: String,
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