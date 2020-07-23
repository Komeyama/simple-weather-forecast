package com.komeyama.simple.weather.db.internal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.komeyama.simple.weather.db.internal.entity.FavoritePlaceEntityImpl
import kotlinx.coroutines.flow.Flow

@Dao
internal abstract class FavoritePlaceDao {
    @Query("SELECT * FROM favorite_place")
    abstract fun favoritePlaceLiveData(): LiveData<List<FavoritePlaceEntityImpl>>

    @Query("SELECT * FROM favorite_place")
    abstract fun favoritePlaceInfo(): List<FavoritePlaceEntityImpl>

    @Query("SELECT * FROM favorite_place")
    abstract fun favoritePlaceInfoFlow(): Flow<List<FavoritePlaceEntityImpl>>

    @Query("DELETE FROM favorite_place")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(favoritePlaceEntityImpl: FavoritePlaceEntityImpl)

    @Query("DELETE FROM favorite_place WHERE favorite_forecast_id = :forecastId")
    abstract fun delete(forecastId: String)
}