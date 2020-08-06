package com.komeyama.simple.weather.forecast

import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.komeyama.simple.weather.core.di.PageScope
import com.komeyama.simple.weather.forecast.ui.BottomNavigationBehavior
import com.komeyama.simple.weather.weather_list.*
import com.komeyama.simple.weather.weather_list.di.AssistedInjectModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    lateinit var bottomNavigationBehavior: BottomNavigationBehavior

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * TODO: Maybe I'll keep the last instance.
         */
        val navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        setupWithNavController(bottom_navigation_view, navController)

        val layoutParams: CoordinatorLayout.LayoutParams =
            bottom_navigation_view.layoutParams as CoordinatorLayout.LayoutParams
        bottomNavigationBehavior = BottomNavigationBehavior(this)
        layoutParams.behavior = bottomNavigationBehavior


        setSupportActionBar(toolbar)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

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
        modules = [WeatherDetailListFragmentModule::class, AssistedInjectModule::class]
    )
    abstract fun contributeWeatherDetailFragment(): WeatherDetailListFragment

    @ContributesAndroidInjector(
        modules = [SearchPlacesFragmentModule::class]
    )
    abstract fun contributeSearchPlacesFragment(): SearchPlacesFragment

    @ContributesAndroidInjector(
        modules = [DetailForecastFragmentModule::class, AssistedInjectModule::class]
    )
    abstract fun contributeDetailForecastFragment(): DetailForecastFragment

    @Module
    abstract class MainActivityBuilder {
        @ContributesAndroidInjector(modules = [MainActivityModule::class])
        abstract fun contributeMainActivity(): MainActivity
    }
}