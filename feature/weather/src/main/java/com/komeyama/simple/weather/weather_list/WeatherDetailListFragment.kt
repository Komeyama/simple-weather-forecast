package com.komeyama.simple.weather.weather_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.komeyama.simple.weather.weather_list.databinding.ItemForecastContentBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.weather_detail_list.*
import timber.log.Timber

class WeatherDetailListFragment : Fragment(R.layout.weather_detail_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = WeatherDetailListFragmentArgs.fromBundle(requireArguments())
        Timber.d("args: %s", args.prefectureId)

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
abstract class WeatherDetailFragmentModule {

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