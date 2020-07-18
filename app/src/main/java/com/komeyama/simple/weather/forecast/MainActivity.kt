package com.komeyama.simple.weather.forecast

import android.os.Bundle
import com.komeyama.simple.weather.weather_list.*
import com.komeyama.simple.weather.weather_list.di.WeatherDetailListAssistedInjectModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(
            modules = [WeatherListFragmentModule::class]
    )
    abstract fun contributeWeatherListFragment(): WeatherListFragment

    @ContributesAndroidInjector(
            modules = [FavoriteSiteFragmentModule::class]
    )
    abstract fun contributeFavoritePlaceFragment(): FavoritePlaceFragment

    @ContributesAndroidInjector(
            modules = [WeatherDetailListFragmentModule::class, WeatherDetailListAssistedInjectModule::class]
    )
    abstract fun contributeWeatherDetailFragment(): WeatherDetailListFragment

    @Module
    abstract class MainActivityBuilder {
        @ContributesAndroidInjector(modules = [MainActivityModule::class])
        abstract fun contributeMainActivity(): MainActivity
    }
}