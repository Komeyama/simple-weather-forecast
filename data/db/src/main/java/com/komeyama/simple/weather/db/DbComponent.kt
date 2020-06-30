package com.komeyama.simple.weather.db

import android.content.Context
import com.komeyama.simple.weather.db.internal.DbModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
@Component(
    modules = [
        DbModule::class
    ]
)
interface DbComponent {
    fun forecastInfoDatabase(): ForecastInfoDatabase
    fun forecastMainDatabase(): ForecastMainDatabase
    fun detailCopyrightMainDatabase(): DetailCopyrightMainDatabase
    fun detailDescriptionDatabase(): DetailDescriptionDatabase
    fun detailForecastDatabase(): DetailForecastDatabase
    fun detailImageDatabase(): DetailImageDatabase
    fun detailImageOfCopyrightDatabase(): DetailImageOfCopyrightDatabase
    fun detailLocationDatabase(): DetailLocationDatabase
    fun detailTemperatureDatabase(): DetailTemperatureDatabase
    fun pinpointLocationDatabase(): PinpointLocationDatabase
    fun temperatureDatabase(): TemperatureDatabase

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance coroutineContext: CoroutineContext,
            @BindsInstance filename: String?
        ): DbComponent
    }

    companion object {
        fun factory(): Factory = DaggerDbComponent.factory()
    }
}