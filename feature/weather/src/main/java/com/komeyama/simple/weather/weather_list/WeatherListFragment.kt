package com.komeyama.simple.weather.weather_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.komeyama.simple.weather.repository.internal.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class WeatherListFragment : DaggerFragment() {

    @Inject
    lateinit var weatherRepository: WeatherRepository

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        weatherRepository.dummyFunc()
        return inflater.inflate(
                R.layout.weather_list,
                container,
                false
        )
    }
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