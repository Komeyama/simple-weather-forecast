package com.komeyama.simple.weather.weather_list

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import dagger.Module
import dagger.Provides

class FavoriteSiteFragment : Fragment(R.layout.favorite_site) {
}

@Module
abstract class FavoriteSiteFragmentModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providesLifecycleOwnerLiveData(
                favoriteSiteFragment: FavoriteSiteFragment
        ): LiveData<LifecycleOwner> {
            return favoriteSiteFragment.viewLifecycleOwnerLiveData
        }
    }
}