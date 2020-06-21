package com.komeyama.simple.weather.db.internal

import android.content.Context
import androidx.room.Room
import com.komeyama.simple.weather.db.ForecastDatabase
import com.komeyama.simple.weather.db.internal.dao.WeatherInfoDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DbModule.Providers::class])
internal abstract class DbModule {
    @Binds
    abstract fun forecastDatabase(impl: RoomDatabase): ForecastDatabase

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
                filename ?: "weather_info.db"
            ).fallbackToDestructiveMigration().build()
        }

        @Provides
        fun weatherInfoDao(database: CacheDatabase): WeatherInfoDao {
            return database.weatherInfoDao()
        }
    }

}