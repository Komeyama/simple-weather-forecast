package com.komeyama.simple.weather.weather_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.komeyama.simple.weather.core.di.PageScope
import com.komeyama.simple.weather.core.extentions.assistedViewModels
import com.komeyama.simple.weather.model.CityIds
import com.komeyama.simple.weather.model.SubPageContent
import com.komeyama.simple.weather.weather_list.databinding.ItemForecastCityContentBinding
import com.komeyama.simple.weather.weather_list.databinding.ItemHeadderBinding
import com.komeyama.simple.weather.weather_list.viewmodel.WeatherDetailListViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.weather_detail_list.*
import timber.log.Timber
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
        val section = Section()
        section.setHeader(HeaderItem())

        weatherDetailListViewModel.detailListUiData.observe(
            viewLifecycleOwner,
            Observer { forecastInfoList ->
                forecastInfoList.subPageContents.map {
                    if (it.cityName == "") {
                        return@Observer
                    }
                }
                section.update(forecastInfoList.subPageContents.map { subPageContent ->
                    ForecastContentItem(
                        weatherDetailListViewModel,
                        subPageContent
                    )
                })
            })
        groupAdapter.add(section)
    }

    internal data class ForecastContentItem(
        private val viewModel: WeatherDetailListViewModel,
        private val subPageContent: SubPageContent
    ) : BindableItem<ItemForecastCityContentBinding>(subPageContent.cityName.hashCode().toLong()) {
        override fun getLayout() = R.layout.item_forecast_city_content

        /**
         * todo: Register the forecast id in the database so that it can be retrieved here.
         * todo: loading
         * todo: livedata process
         */
        override fun bind(viewBinding: ItemForecastCityContentBinding, position: Int) {

            viewBinding.topCardCityText.text = subPageContent.cityName
            viewBinding.topCardCityTodayWeatherText.text = subPageContent.telop
            viewBinding.topCardCityTodayWeatherImage.load(subPageContent.imgUrl)
            viewBinding.topCardCityTodayMaxTemperatureText.text = subPageContent.maxTemperature
            viewBinding.topCardCityTodayMinTemperatureText.text = subPageContent.minTemperature
            viewBinding.topCardCityFavoritePlace.setOnClickListener {
                CityIds.values().firstOrNull { it.id == subPageContent.cityName }?.id.apply {
                    this?.let { id ->
                        viewModel.favorite(id)
                    }
                }
            }

            viewBinding.forecastCityCardTop.setOnClickListener { v ->
                CityIds.values()
                    .firstOrNull { it.id == subPageContent.cityName }?.id.apply {
                        if (this != null) {
                            val navigateId =
                                WeatherDetailListFragmentDirections.actionWeatherDetailListToDetailForecast(
                                    cityId = this
                                )
                            Navigation.findNavController(v).navigate(navigateId)
                        }
                    }
            }

            viewBinding.content = subPageContent
        }
    }

    internal class HeaderItem() : BindableItem<ItemHeadderBinding>() {
        override fun getLayout() = R.layout.item_headder

        override fun bind(viewBinding: ItemHeadderBinding, position: Int) {}
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