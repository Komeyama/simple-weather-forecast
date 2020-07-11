package com.komeyama.simple.weather.db

interface ForecastInfoEntity {
    val forecastInfoEntityImpl: ForecastMainInfoEntity
    val detailForecastEntityImpl: List<DetailForecastEntity>
    val pinpointLocationEntityImpl: List<PinpointLocationEntity>
    val detailCopyrightEntity: DetailCopyrightEntity
}