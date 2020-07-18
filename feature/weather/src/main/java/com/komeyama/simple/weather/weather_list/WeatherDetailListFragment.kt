package com.komeyama.simple.weather.weather_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.navArgs
import com.komeyama.simple.weather.core.extentions.assistedViewModels
import com.komeyama.simple.weather.weather_list.databinding.ItemForecastContentBinding
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

        weatherDetailListViewModel.checkId()
        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        forecast_detail_list_recycler_view.adapter = groupAdapter
        val items: MutableList<BindableItem<ItemForecastContentBinding>> = mutableListOf()
        items.add(
            ForecastContentItem(
                "city name",
                "telop",
                "img",
                "max temperature",
                "min temperature"
            )
        )
        groupAdapter.update(items)
    }

    internal class ForecastContentItem(
        val cityName: String,
        val telop: String,
        var url: String,
        var maxTemperature: String,
        val minTemperature: String
    ) :
        BindableItem<ItemForecastContentBinding>() {
        override fun getLayout() = R.layout.item_forecast_content

        override fun bind(viewBinding: ItemForecastContentBinding, position: Int) {}
    }
}

@Module
abstract class WeatherDetailListFragmentModule {

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