package com.komeyama.simple.weather.weather_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.komeyama.simple.weather.core.di.PageScope
import com.komeyama.simple.weather.core.extentions.assistedViewModels
import com.komeyama.simple.weather.model.DetailWeatherInfo
import com.komeyama.simple.weather.model.toFromKelvinToCelsius
import com.komeyama.simple.weather.weather_list.databinding.ItemWeatherFiveHoursBinding
import com.komeyama.simple.weather.weather_list.viewmodel.DetailForecastViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.GroupieViewHolder
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.detail_forecast.*
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

        val groupAdapter = GroupAdapter<GroupieViewHolder<*>>()
        forecast_five_hours_weather_recycler_view.adapter = groupAdapter
        val section = Section()

        detailForecastViewModel.detailForecastInfoLiveData.observe(viewLifecycleOwner,
            Observer {
                Timber.d("detail forecast data %s", it)
                detail_today_weather_image.load(it.list[0].weather[0].icon?.toIconUrl())
                detail_today_weather_main.text = it.list[0].weather[0].main
                detail_today_temp.text =
                    it.list[0].main.temp.toFromKelvinToCelsius().toInt().toString()
                detail_today_temp_max_value.text =
                    it.list[0].main.temp_max.toFromKelvinToCelsius().toInt().toString()
                detail_today_temp_min_value.text =
                    it.list[0].main.temp_min.toFromKelvinToCelsius().toInt().toString()
                section.update(it.list.map { detailWeatherInfo ->
                    ForecastContentItem(detailWeatherInfo)
                })
            })
        groupAdapter.add(section)
    }

    /**
     * TODO: fix emergency
     */
    private fun String.toIconUrl(): String {
        return "http://openweathermap.org/img/wn/$this.png"
    }

    internal class ForecastContentItem(
        private val detailWeatherInfo: DetailWeatherInfo
    ) : BindableItem<ItemWeatherFiveHoursBinding>(
        detailWeatherInfo.dt_txt.hashCode().toLong()
    ) {
        override fun getLayout() = R.layout.item_weather_five_hours

        override fun bind(viewBinding: ItemWeatherFiveHoursBinding, position: Int) {
            /**
             * TODO: time
             */
            viewBinding.fiveHoursWeatherTime.text = "9:00"
            viewBinding.fiveHoursWeatherImage.load(detailWeatherInfo.weather[0].icon?.toIconUrl())
            viewBinding.fiveHoursWeatherTemp.text =
                detailWeatherInfo.main.temp.toFromKelvinToCelsius().toInt().toString()
        }
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