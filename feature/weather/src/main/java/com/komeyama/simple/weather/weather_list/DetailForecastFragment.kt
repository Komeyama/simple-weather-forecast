package com.komeyama.simple.weather.weather_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.komeyama.simple.weather.core.di.PageScope
import com.komeyama.simple.weather.core.extentions.assistedViewModels
import com.komeyama.simple.weather.weather_list.viewmodel.DetailForecastViewModel
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class DetailForecastFragment : DaggerFragment() {

    @Inject
    lateinit var detailForecastViewModelFactory: DetailForecastViewModel.Factory
    private val detailForecastViewModel by assistedViewModels {
        detailForecastViewModelFactory.create(navArgs.cityId)
    }

    private val navArgs: DetailForecastFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.detail_forecast,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailForecastViewModel.detailForecastInfoLiveData.observe(viewLifecycleOwner,
            Observer {
                Timber.d("detail forecast data %s", it)
            })
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