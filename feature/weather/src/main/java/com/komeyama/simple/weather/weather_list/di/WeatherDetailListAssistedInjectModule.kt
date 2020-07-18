package com.komeyama.simple.weather.weather_list.di

import com.komeyama.simple.weather.weather_list.viewmodel.AssistedInject_WeatherDetailListAssistedInjectModule
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@AssistedModule
@Module(includes = [AssistedInject_WeatherDetailListAssistedInjectModule::class])
interface WeatherDetailListAssistedInjectModule