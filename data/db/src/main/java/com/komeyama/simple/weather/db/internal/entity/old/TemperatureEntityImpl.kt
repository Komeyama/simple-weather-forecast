package com.komeyama.simple.weather.db.internal.entity.old

//import com.komeyama.simple.weather.db.internal.entity.old.DetailTemperatureEntityImpl
//
//@Entity(tableName = "temperature")
//internal data class TemperatureEntityImpl (
//    @PrimaryKey(autoGenerate = true)
//    val id: Int = 0,
//    @Embedded(prefix = "min_temperature")
//    override var min: DetailTemperatureEntityImpl?,
//    @Embedded(prefix = "max_temperature")
//    override var max: DetailTemperatureEntityImpl?
//): TemperatureEntity