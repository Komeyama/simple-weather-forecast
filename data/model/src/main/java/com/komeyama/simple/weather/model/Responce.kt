package com.komeyama.simple.weather.model

import kotlinx.serialization.Serializable

//@Serializable
//data class Response(
//    val mainResponse: MainResponse,
//    val detailForecastResponse: List<DetailForecastResponse>,
//    val pinpointLocationResponse: List<PinpointLocationResponse>,
//    val copyright: DetailCopyrightResponse
//)
//
//@Serializable
//data class MainResponse(
//    var id: Int,
//    var title: String,
//    var link: String,
//    var publicTime: String,
//    var detailDescriptionResponse: DetailDescriptionResponse,
//    var detailLocationResponse: DetailLocationResponse
//)
//
//@Serializable
//data class DetailLocationResponse(
//    var area: String,
//    var prefecture: String,
//    var city: String
//)
//
//@Serializable
//data class DetailDescriptionResponse(
//    var text: String,
//    var publicTime: String
//)
//
//@Serializable
//data class DetailForecastResponse(
//    var date: String,
//    var dateLabel: String,
//    var telop: String,
//    var image: DetailImageResponse,
//    var temperature: TemperatureResponse
//)
//
//@Serializable
//data class PinpointLocationResponse(
//    var link: String,
//    var name: String
//)
//
//@Serializable
//data class DetailCopyrightResponse(
//    var title: String?,
//    var link: String?,
//    var image: DetailImageResponse?,
//    var provider: List<PinpointLocationResponse?>
//)
//
//@Serializable
//data class DetailImageResponse(
//    var title: String,
//    var url: String,
//    var width: String,
//    var height: String
//)
//
//@Serializable
//data class TemperatureResponse(
//    var min: DetailTemperatureResponse?,
//    var max: DetailTemperatureResponse?
//)
//
//@Serializable
//data class DetailTemperatureResponse(
//    var celsius: String,
//    var fahrenheit: String
//)