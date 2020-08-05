package com.komeyama.simple.weather.weather_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.navArgs
import com.komeyama.simple.weather.core.di.PageScope
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment
import timber.log.Timber

class DetailForecastFragment : DaggerFragment() {

    private val navArgs: DetailForecastFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Timber.d("nav args: %s", navArgs.cityId)

        return inflater.inflate(
            R.layout.detail_forecast,
            container,
            false
        )
    }

}

@Module
abstract class DetailForecastFragmentModule {

    @Module
    companion object {
        @PageScope
        @JvmStatic
        @Provides
        fun providesLifecycleOwnerLiveData(
            detailForecastFragment: DetailForecastFragment
        ): LiveData<LifecycleOwner> {
            return detailForecastFragment.viewLifecycleOwnerLiveData
        }
    }
}