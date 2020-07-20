package com.komeyama.simple.weather.weather_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import coil.api.load
import com.komeyama.simple.weather.core.extentions.assistedActivityViewModels
import com.komeyama.simple.weather.model.PrefectureIds
import com.komeyama.simple.weather.weather_list.databinding.ItemForecastPrefectureContentBinding
import com.komeyama.simple.weather.weather_list.viewmodel.WeatherListViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.weather_list.*
import javax.inject.Inject
import javax.inject.Provider

class WeatherListFragment : DaggerFragment() {

    @Inject
    lateinit var weatherListViewModelProvider: Provider<WeatherListViewModel>
    private val weatherListViewModel: WeatherListViewModel by assistedActivityViewModels {
        weatherListViewModelProvider.get()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        weatherListViewModel.callWeatherRepositoryMethod()
        //weatherListViewModel.callWeatherRepositoryDbMethod()
        return inflater.inflate(
            R.layout.weather_list,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        forecast_list_recycler_view.adapter = groupAdapter

        /**
         * todo:fix update process
         */
        weatherListViewModel.forecastInfoLiveData.observe(
            viewLifecycleOwner,
            Observer { forecastInfoList ->
                val items: MutableList<BindableItem<ItemForecastPrefectureContentBinding>> = mutableListOf()
                forecastInfoList.forEach {
                    items.add(
                        ForecastContentItem(
                            it.prefectureName,
                            it.telop,
                            it.imgUrl,
                            it.maxTemperature,
                            it.minTemperature
                        )
                    )
                }
                groupAdapter.update(items)
            })
    }

    /**
     * todo
     */
    internal class ForecastContentItem(
        val prefectureName: String,
        val telop: String,
        var url: String,
        var maxTemperature: String,
        val minTemperature: String
    ) : BindableItem<ItemForecastPrefectureContentBinding>() {
        override fun getLayout() = R.layout.item_forecast_prefecture_content

        override fun bind(viewBinding: ItemForecastPrefectureContentBinding, position: Int) {
            viewBinding.topCardPrefectureText.text = prefectureName
            viewBinding.topCardPrefectureTodayWeatherText.text = telop
            viewBinding.topCardPrefectureTodayWeatherImage.load(url)
            viewBinding.topCardPrefectureTodayMaxTemperatureText.text = maxTemperature
            viewBinding.topCardPrefectureTodayMinTemperatureText.text = minTemperature

            viewBinding.forecastPrefectureCardTop.setOnClickListener { v ->
                val prefectureId =
                    PrefectureIds.values().first { it.prefectureName == prefectureName }.id
                val navigateId = WeatherListFragmentDirections.actionWeatherListToWeatherDetailList(
                    prefectureId = prefectureId
                )

                findNavController(v).navigate(navigateId)
            }
        }
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