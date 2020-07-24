package com.komeyama.simple.weather.forecast

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.komeyama.simple.weather.core.di.PageScope
import com.komeyama.simple.weather.weather_list.*
import com.komeyama.simple.weather.weather_list.di.WeatherDetailListAssistedInjectModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * TODO: Maybe I'll keep the last instance.
         */
        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        setupWithNavController(bottom_navigation_view, navController)
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

    @PageScope
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