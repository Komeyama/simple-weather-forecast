package com.komeyama.simple.weather.weather_list

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import dagger.Module
import dagger.Provides

class WeatherDetailFragment : Fragment(R.layout.weather_detail) {
}

@Module
abstract class WeatherDetailFragmentModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providesLifecycleOwnerLiveData(
            weatherDetailFragment: WeatherDetailFragment
        ): LiveData<LifecycleOwner> {
            return weatherDetailFragment.viewLifecycleOwnerLiveData
        }
    }
}