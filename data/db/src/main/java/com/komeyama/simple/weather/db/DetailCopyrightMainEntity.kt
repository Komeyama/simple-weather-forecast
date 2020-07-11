package com.komeyama.simple.weather.db

interface DetailCopyrightMainEntity {
    var parentId: Int
    var title: String?
    var link: String?
    val image: DetailImageEntity?
}