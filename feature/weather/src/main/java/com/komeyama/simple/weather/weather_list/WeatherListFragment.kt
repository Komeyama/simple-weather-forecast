package com.komeyama.simple.weather.weather_list

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import dagger.Module
import dagger.Provides

class WeatherListFragment : Fragment(R.layout.weather_list) {
}

@Module
abstract class WeatherListFragmentModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providesLifecycleOwnerLiveData(
                weatherListFragment: WeatherListFragment
        ): LiveData<LifecycleOwner> {
            return weatherListFragment.viewLifecycleOwnerLiveData
        }
    }
}