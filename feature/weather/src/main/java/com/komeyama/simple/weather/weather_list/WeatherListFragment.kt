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
import androidx.navigation.Navigation.findNavController
import coil.api.load
import com.komeyama.simple.weather.core.extentions.assistedActivityViewModels
import com.komeyama.simple.weather.model.PrefectureIds
import com.komeyama.simple.weather.weather_list.databinding.ItemForecastContentBinding
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

        val items: MutableList<BindableItem<ItemForecastContentBinding>> = mutableListOf()
        weatherListViewModel.forecastInfoLiveData.observe(
            viewLifecycleOwner,
            Observer { forecastInfoList ->
                forecastInfoList.forEach {
                    items.add(
                        ForecastContentItem(
                            requireContext(),
                            weatherListViewModel,
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
     *
     */
    internal class ForecastContentItem(
        var context: Context,
        var viewModel: WeatherListViewModel,
        val prefectureName: String,
        val telop: String,
        var url: String,
        var maxTemperature: String,
        val minTemperature: String
    ) :
        BindableItem<ItemForecastContentBinding>() {
        override fun getLayout() = R.layout.item_forecast_content

        override fun bind(viewBinding: ItemForecastContentBinding, position: Int) {
            viewBinding.topCardPrefectureText.text = prefectureName
            viewBinding.topCardTodayWeatherText.text = telop
            viewBinding.topCardTodayWeatherImage.load(url)
            viewBinding.topCardTodayMaxTemperatureText.text = maxTemperature
            viewBinding.topCardTodayMinTemperatureText.text = minTemperature
            viewBinding.topCardFavoritePlace.setColorFilter(
                ContextCompat.getColor(
                    context,
                    R.color.colorLight_d3
                )
            )
            viewBinding.topCardFavoritePlace.setOnClickListener {
                viewModel.favorite(
                    PrefectureIds.values().first { it.prefectureName == prefectureName }.id
                )
            }
            viewBinding.forecastCardTop.setOnClickListener { v ->
                val navigateId = WeatherListFragmentDirections.actionWeatherListToWeatherDetailList(
                    prefectureId = PrefectureIds.values()
                        .first { it.prefectureName == prefectureName }.id
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