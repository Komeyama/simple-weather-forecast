package com.komeyama.simple.weather.db.internal

import android.content.Context
import androidx.room.Room
import com.komeyama.simple.weather.db.DetailDescriptionDatabase
import com.komeyama.simple.weather.db.DetailLocationDatabase
import com.komeyama.simple.weather.db.ForecastDatabase
import com.komeyama.simple.weather.db.internal.dao.DetailDescriptionDao
import com.komeyama.simple.weather.db.internal.dao.DetailLocationDao
import com.komeyama.simple.weather.db.internal.dao.ForecastInfoDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DbModule.Providers::class])
internal abstract class DbModule {
    @Binds
    abstract fun forecastDatabase(impl: RoomDatabase): ForecastDatabase

    @Binds
    abstract fun detailDescriptionDatabase(impl: RoomDatabase): DetailDescriptionDatabase

    @Binds
    abstract fun detailLocationDatabase(impl: RoomDatabase): DetailLocationDatabase

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
        fun weatherInfoDao(database: CacheDatabase): ForecastInfoDao {
            return database.forecastInfoDao()
        }

        @Provides
        fun detailDescriptionDao(database: CacheDatabase): DetailDescriptionDao {
            return database.detailDescriptionDao()
        }

        @Provides
        fun detailLocationDao(database: CacheDatabase): DetailLocationDao {
            return database.detailLocationDao()
        }
    }

}