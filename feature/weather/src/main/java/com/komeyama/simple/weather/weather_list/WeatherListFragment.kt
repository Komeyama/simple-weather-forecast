package com.komeyama.simple.weather.weather_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.komeyama.simple.weather.core.extentions.assistedActivityViewModels
import com.komeyama.simple.weather.weather_list.viewmodel.WeatherListViewModel
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Provider

class WeatherListFragment : DaggerFragment() {

    @Inject
    lateinit var weatherListViewModelProvider: Provider<WeatherListViewModel>
    private val sessionsViewModel: WeatherListViewModel by assistedActivityViewModels {
        weatherListViewModelProvider.get()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        sessionsViewModel.callWeatherRepositoryMethod()
        sessionsViewModel.callWeatherRepositoryDbMethod()
        sessionsViewModel.callWeatherRepositoryDbLoadMethod()
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