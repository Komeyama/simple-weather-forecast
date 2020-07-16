package com.komeyama.simple.weather.weather_list

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import dagger.Module
import dagger.Provides

class WeatherDetailListFragment : Fragment(R.layout.weather_detail_list) {
}

@Module
abstract class WeatherDetailFragmentModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providesLifecycleOwnerLiveData(
            weatherDetailFragment: WeatherDetailListFragment
        ): LiveData<LifecycleOwner> {
            return weatherDetailFragment.viewLifecycleOwnerLiveData
        }
    }
}