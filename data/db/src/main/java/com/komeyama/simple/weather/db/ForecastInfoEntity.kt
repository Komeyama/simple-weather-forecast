package com.komeyama.simple.weather.db

interface ForecastInfoEntity {
    val forecastInfoEntity: ForecastMainInfoEntity
    val detailForecastEntity: List<DetailForecastEntity>
    val pinpointLocationEntity: List<PinpointLocationEntity>
    val detailCopyrightEntity: DetailCopyrightEntity
}