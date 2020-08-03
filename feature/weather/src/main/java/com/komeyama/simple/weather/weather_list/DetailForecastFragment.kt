package com.komeyama.simple.weather.weather_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.komeyama.simple.weather.core.di.PageScope
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment

class DetailForecastFragment : DaggerFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.weather_detail_list,
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