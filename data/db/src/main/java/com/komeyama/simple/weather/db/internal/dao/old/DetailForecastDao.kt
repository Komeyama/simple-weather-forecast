package com.komeyama.simple.weather.db.internal.dao.old

import androidx.lifecycle.LiveData
import androidx.room.*
//import com.komeyama.simple.weather.db.internal.entity.old.DetailForecastEntityImpl

//@Dao
//internal abstract class DetailForecastDao {
//    @Query("SELECT * FROM detail_forecast")
//    abstract fun detailForecastLiveData(): LiveData<List<DetailForecastEntityImpl>>
//
//    @Query("SELECT * FROM detail_forecast")
//    abstract fun detailForecast(): List<DetailForecastEntityImpl>
//
//    @Query("DELETE FROM detail_forecast")
//    abstract fun deleteAll()
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insert(detailForecasts: List<DetailForecastEntityImpl?>?)
//}