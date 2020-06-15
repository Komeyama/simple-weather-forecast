package com.komeyama.simple.weather.forecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.komeyama.simple.weather.weather_list.*
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
    abstract fun contributeFavoriteSiteFragment(): FavoriteSiteFragment

    @ContributesAndroidInjector(
            modules = [WeatherDetailFragmentModule::class]
    )
    abstract fun contributeWeatherDetailFragment(): WeatherDetailFragment

    @Module
    abstract class MainActivityBuilder {
        @ContributesAndroidInjector(modules = [MainActivityModule::class])
        abstract fun contributeMainActivity(): MainActivity
    }
}