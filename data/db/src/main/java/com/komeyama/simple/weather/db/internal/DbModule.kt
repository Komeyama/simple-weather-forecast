package com.komeyama.simple.weather.db.internal

import android.content.Context
import androidx.room.Room
import com.komeyama.simple.weather.db.*
import com.komeyama.simple.weather.db.internal.dao.*
import com.komeyama.simple.weather.db.internal.dao.DetailDescriptionDao
import com.komeyama.simple.weather.db.internal.dao.DetailForecastDao
import com.komeyama.simple.weather.db.internal.dao.DetailLocationDao
import com.komeyama.simple.weather.db.internal.dao.ForecastInfoDao
import com.komeyama.simple.weather.db.internal.dao.ForecastMainInfoDao
import com.komeyama.simple.weather.db.internal.dao.PinpointLocationDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DbModule.Providers::class])
internal abstract class DbModule {
    @Binds
    abstract fun forecastInfoDatabase(impl: RoomDatabase): ForecastInfoDatabase

    @Binds
    abstract fun forecastDatabase(impl: RoomDatabase): ForecastMainDatabase

    @Binds
    abstract fun detailDescriptionDatabase(impl: RoomDatabase): DetailDescriptionDatabase

    @Binds
    abstract fun detailForecastDatabase(impl: RoomDatabase): DetailForecastDatabase

    @Binds
    abstract fun detailImageDatabase(impl: RoomDatabase): DetailImageDatabase

    @Binds
    abstract fun detailImageOfCopyrightDatabase(impl: RoomDatabase): DetailImageOfCopyrightDatabase

    @Binds
    abstract fun detailLocationDatabase(impl: RoomDatabase): DetailLocationDatabase

    @Binds
    abstract fun detailCopyrightMainDatabase(impl: RoomDatabase): DetailCopyrightMainDatabase

    @Binds
    abstract fun pinpointLocationDatabase(impl: RoomDatabase): PinpointLocationDatabase

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
        fun forecastInfoDao(database: CacheDatabase): ForecastInfoDao {
            return database.forecastInfoDao()
        }

        @Provides
        fun forecastMainInfoDao(database: CacheDatabase): ForecastMainInfoDao {
            return database.forecastMainInfoDao()
        }

        @Provides
        fun detailDescriptionDao(database: CacheDatabase): DetailDescriptionDao {
            return database.detailDescriptionDao()
        }

        @Provides
        fun detailForecastDao(database: CacheDatabase): DetailForecastDao {
            return database.detailForecastDao()
        }

        @Provides
        fun detailImageDao(database: CacheDatabase): DetailImageDao {
            return database.detailImageDao()
        }

        @Provides
        fun detailLocationDao(database: CacheDatabase): DetailLocationDao {
            return database.detailLocationDao()
        }

        @Provides
        fun detailCopyrightEntityDao(database: CacheDatabase): DetailCopyrightMainDao {
            return database.detailCopyrightMainDao()
        }

        @Provides
        fun pinpointLocationDao(database: CacheDatabase): PinpointLocationDao {
             return database.pinpointLocationDao()
        }
    }

}