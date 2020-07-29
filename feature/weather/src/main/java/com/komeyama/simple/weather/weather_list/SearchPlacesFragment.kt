package com.komeyama.simple.weather.weather_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment

class SearchPlacesFragment : DaggerFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.search_place,
            container,
            false
        )
    }
}

@Module
abstract class SearchPlacesFragmentModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providesLifecycleOwnerLiveData(
            searchPlacesFragment: SearchPlacesFragment
        ): LiveData<LifecycleOwner> {
            return searchPlacesFragment.viewLifecycleOwnerLiveData
        }
    }
}