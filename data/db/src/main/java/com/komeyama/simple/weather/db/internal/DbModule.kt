package com.komeyama.simple.weather.db.internal

import android.content.Context
import androidx.room.Room
import com.komeyama.simple.weather.db.*
import com.komeyama.simple.weather.db.internal.dao.*
import com.komeyama.simple.weather.db.internal.dao.ForecastInfoDao
import com.komeyama.simple.weather.db.internal.dao.ForecastMainInfoDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DbModule.Providers::class])
internal abstract class DbModule {
    @Binds
    abstract fun favoritePlaceDatabase(impl: RoomDatabase): FavoritePlaceDatabase

    @Binds
    abstract fun forecastInfoDatabase(impl: RoomDatabase): ForecastInfoDatabase

    @Binds
    abstract fun forecastDatabase(impl: RoomDatabase): ForecastMainDatabase

    @Binds
    abstract fun weatherDatabase(impl: RoomDatabase): WeatherDatabase

    @Module
    internal object Providers {
        @Singleton
        @Provides
        fun cacheDatabase(
            context: Context,
            filename: String?
        ): CacheDatabase {
            return Room.databaseBuilder(
                context,
                CacheDatabase::class.java,
                filename ?: "forecast_info.db"
            ).fallbackToDestructiveMigration().build()
        }

        @Provides
        fun favoritePlaceDao(database: CacheDatabase): FavoritePlaceDao {
            return database.favoritePlaceDao()
        }

        @Provides
        fun forecastInfoDao(database: CacheDatabase): ForecastInfoDao {
            return database.forecastInfoDao()
        }

        @Provides
        fun forecastMainInfoDao(database: CacheDatabase): ForecastMainInfoDao {
            return database.forecastMainInfoDao()
        }

        @Provides
        fun weatherInfoDao(database: CacheDatabase): WeatherDao {
            return database.weatherDao()
        }
    }

}