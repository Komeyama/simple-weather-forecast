package com.komeyama.simple.weather.weather_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.komeyama.simple.weather.core.di.PageScope
import com.komeyama.simple.weather.core.extentions.assistedViewModels
import com.komeyama.simple.weather.model.CityIds
import com.komeyama.simple.weather.weather_list.databinding.ItemForecastCityContentBinding
import com.komeyama.simple.weather.weather_list.viewmodel.WeatherDetailListViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.weather_detail_list.*
import javax.inject.Inject

class WeatherDetailListFragment : DaggerFragment() {

    @Inject
    lateinit var weatherDetailListViewModelFactory: WeatherDetailListViewModel.Factory
    private val weatherDetailListViewModel by assistedViewModels {
        weatherDetailListViewModelFactory.create(navArgs.prefectureId)
    }

    private val navArgs: WeatherDetailListFragmentArgs by navArgs()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        forecast_detail_list_recycler_view.adapter = groupAdapter
        weatherDetailListViewModel.forecastDetailListInfoLiveData.observe(
            viewLifecycleOwner,
            Observer { forecastInfoList ->
                groupAdapter.update(forecastInfoList.map {
                    ForecastContentItem(
                        requireContext(),
                        weatherDetailListViewModel,
                        it.cityName,
                        it.telop,
                        it.imgUrl,
                        it.maxTemperature,
                        it.minTemperature
                    )
                })
            })
    }

    internal class ForecastContentItem(
        private val context: Context,
        private val viewModel: WeatherDetailListViewModel,
        private val cityName: String,
        private val telop: String,
        private val url: String,
        private val maxTemperature: String,
        private val minTemperature: String
    ) : BindableItem<ItemForecastCityContentBinding>(cityName.hashCode().toLong()) {
        override fun getLayout() = R.layout.item_forecast_city_content

        override fun bind(viewBinding: ItemForecastCityContentBinding, position: Int) {
            viewBinding.topCardCityText.text = cityName
            viewBinding.topCardCityTodayWeatherText.text = telop
            viewBinding.topCardCityTodayWeatherImage.load(url)
            viewBinding.topCardCityTodayMaxTemperatureText.text = maxTemperature
            viewBinding.topCardCityTodayMinTemperatureText.text = minTemperature
            viewBinding.topCardCityFavoritePlace.setColorFilter(
                ContextCompat.getColor(
                    context,
                    R.color.colorLight_d3
                )
            )
            viewBinding.topCardCityFavoritePlace.setOnClickListener {
                viewModel.favorite(
                    CityIds.values().first { it.cityName == cityName }.id
                )
            }
        }
    }
}

@Module
abstract class WeatherDetailListFragmentModule {

    @Module
    companion object {
        @PageScope
        @JvmStatic
        @Provides
        fun providesLifecycleOwnerLiveData(
            weatherDetailFragment: WeatherDetailListFragment
        ): LiveData<LifecycleOwner> {
            return weatherDetailFragment.viewLifecycleOwnerLiveData
        }
    }
}