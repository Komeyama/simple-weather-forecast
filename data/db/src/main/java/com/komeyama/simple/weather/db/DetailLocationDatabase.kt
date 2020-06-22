package com.komeyama.simple.weather.db

interface DetailLocationDatabase {
    fun detailLocationEntity(): List<DetailLocationEntity>
}