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
    abstract fun favoritePlaceDatabase(impl: RoomDatabase): FavoritePlaceDatabase

    @Binds
    abstract fun forecastInfoDatabase(impl: RoomDatabase): ForecastInfoDatabase

    @Binds
    abstract fun forecastDatabase(impl: RoomDatabase): ForecastMainDatabase

    @Binds
    abstract fun detailCopyrightDatabase(impl: RoomDatabase): DetailCopyrightDatabase

    @Binds
    abstract fun detailCopyrightMainDatabase(impl: RoomDatabase): DetailCopyrightMainDatabase

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
    abstract fun detailTemperatureDatabase(impl: RoomDatabase): DetailTemperatureDatabase

    @Binds
    abstract fun pinpointLocationDatabase(impl: RoomDatabase): PinpointLocationDatabase

    @Binds
    abstract fun pinpointLocationOfCopyDatabase(impl: RoomDatabase): PinpointLocationOfCopyDatabase

    @Binds
    abstract fun temperatureDatabase(impl: RoomDatabase): TemperatureDatabase

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
        fun detailCopyrightEntityDao(database: CacheDatabase): DetailCopyrightDao {
            return database.detailCopyrightDao()
        }

        @Provides
        fun detailCopyrightMainEntityDao(database: CacheDatabase): DetailCopyrightMainDao {
            return database.detailCopyrightMainDao()
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
        fun detailTemperatureDao(database: CacheDatabase): DetailTemperatureDao {
            return database.detailTemperatureDao()
        }

        @Provides
        fun pinpointLocationDao(database: CacheDatabase): PinpointLocationDao {
            return database.pinpointLocationDao()
        }

        @Provides
        fun temperatureDao(database: CacheDatabase): TemperatureDao {
            return database.temperatureDao()
        }
    }

}