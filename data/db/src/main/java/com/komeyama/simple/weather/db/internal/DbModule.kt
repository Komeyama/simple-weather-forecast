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
    abstract fun forecastInfoDatabase(impl: RoomMainDatabase): ForecastInfoDatabase

    @Binds
    abstract fun forecastDatabase(impl: RoomMainDatabase): ForecastMainDatabase

    @Binds
    abstract fun detailDescriptionDatabase(impl: RoomMainDatabase): DetailDescriptionDatabase

    @Binds
    abstract fun detailForecastDatabase(impl: RoomMainDatabase): DetailForecastDatabase

    @Binds
    abstract fun detailLocationDatabase(impl: RoomMainDatabase): DetailLocationDatabase

    @Binds
    abstract fun pinpointLocationDatabase(impl: RoomMainDatabase): PinpointLocationDatabase

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
        fun detailLocationDao(database: CacheDatabase): DetailLocationDao {
            return database.detailLocationDao()
        }

        @Provides
        fun pinpointLocationDao(database: CacheDatabase): PinpointLocationDao {
             return database.pinpointLocation()
        }
    }

}