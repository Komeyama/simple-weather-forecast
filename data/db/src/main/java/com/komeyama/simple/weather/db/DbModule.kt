package com.komeyama.simple.weather.db

import android.content.Context
import androidx.room.Room
import com.komeyama.simple.weather.db.dao.WeatherInfoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DbModule.Providers::class])
internal abstract class DbModule {

    @Module
    internal object Providers {
        @Singleton
        @Provides
        fun cacheDatabase(
            context: Context,
            filename: String?
        ): WeatherInfoDatabase {
            return Room.databaseBuilder(
                context,
                WeatherInfoDatabase::class.java,
                filename ?: "weather_info.db"
            ).fallbackToDestructiveMigration().build()
        }

        @Provides
        fun weatherInfoDao(database: WeatherInfoDatabase): WeatherInfoDao {
            return database.weatherInfoDao()
        }
    }

}