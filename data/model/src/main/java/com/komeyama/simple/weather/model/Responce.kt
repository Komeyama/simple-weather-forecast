package com.komeyama.simple.weather.model

data class MainResponse(
    var id: Long,
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
    var id: Long,
    var parentId: Long,
    var date: String,
    var dateLabel: String,
    var telop: String,
    var image: String,
    var temperature: String
)

data class PinpointLocationResponse(
    var id: Long,
    var parentId: Long,
    var link: String,
    var name: String
)

data class DetailCopyrightResponse(
    var copyrightID: Long,
    var parentId: Long,
    var title: String,
    var link: String,
    var image: DetailImageResponse
)

data class DetailImageResponse(
    var title: String,
    var link: String,
    var url: String,
    var width: String,
    var height: String
)