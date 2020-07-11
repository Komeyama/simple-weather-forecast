package com.komeyama.simple.weather.db

interface DetailCopyrightEntity {
    val detailCopyrightMainEntity: DetailCopyrightMainEntity
    val pinpointLocationEntity: List<PinpointLocationEntity?>
}