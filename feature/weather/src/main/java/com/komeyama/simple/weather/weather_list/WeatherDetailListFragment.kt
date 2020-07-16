package com.komeyama.simple.weather.weather_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import dagger.Module
import dagger.Provides
import timber.log.Timber

class WeatherDetailListFragment : Fragment(R.layout.weather_detail_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = WeatherDetailListFragmentArgs.fromBundle(requireArguments())
        Timber.d("args: %s", args.prefectureId)
    }
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