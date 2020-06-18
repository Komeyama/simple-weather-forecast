package com.komeyama.simple.weather.model

import java.io.Serializable

data class ForecastInfo(
        var location: DetailLocation,
        var title: String,
        var link: String,
        var publicTime: String,
        var description: DetailDescription,
        var forecasts: List<DetailForecasts>,
        var pinpointLocations: List<PinpointLocation>,
        var copyright: DetailCopyright
) : Serializable

data class DetailLocation(
        var area: String,
        var prefecture: String,
        var city: String
) : Serializable

data class DetailDescription(
        var text: String,
        var publicTime: String
) : Serializable

data class DetailForecasts(
        var date: String,
        var dateLabel: String,
        var telop: String,
        var image: DetailImage,
        var temperature: DetailTemperature
) : Serializable

data class PinpointLocation(
        var link: String,
        var name: String
)

data class DetailImage(
        var title: String,
        var link: String,
        var url: String,
        var width: String,
        var height: String
) : Serializable

data class DetailTemperature(
        var celsius: String,
        var fahrenheit: String
) : Serializable

data class DetailCopyright(
        var title: String,
        var link: String,
        var image: DetailImage,
        var provider: List<PinpointLocation>
) : Serializable