package com.komeyama.simple.weather.db

import com.komeyama.simple.weather.db.internal.entity.DetailImageEntityImplOfCopyright

interface DetailCopyrightMainEntity {
    var id: Int
    var parentId: Int
    var title: String?
    var link: String?
    var image: DetailImageEntityImplOfCopyright?
}