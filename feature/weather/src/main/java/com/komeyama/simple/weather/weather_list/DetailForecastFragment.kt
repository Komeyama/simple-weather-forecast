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
import com.komeyama.simple.weather.model.DailyWeather
import com.komeyama.simple.weather.model.DetailWeatherInfo
import com.komeyama.simple.weather.model.toFromKelvinToCelsius
import com.komeyama.simple.weather.weather_list.databinding.ItemWeatherDailyBindingImpl
import com.komeyama.simple.weather.weather_list.databinding.ItemWeatherThreeHoursBinding
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
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DetailForecastFragment : DaggerFragment() {

    @Inject
    lateinit var detailForecastViewModelFactory: DetailForecastViewModel.Factory
    private val detailForecastViewModel by assistedViewModels {
        detailForecastViewModelFactory.create(navArgs.cityId, navArgs.cityLat, navArgs.cityLon)
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
        forecast_three_hours_weather_recycler_view.adapter = groupAdapter
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

        val dailyWeatherGroupAdapter = GroupAdapter<GroupieViewHolder<*>>()
        forecast_daily_weather_recycler_view.adapter = dailyWeatherGroupAdapter
        val dailyWeatherSection = Section()

        detailForecastViewModel.dailyForecastInfoLiveData.observe(viewLifecycleOwner,
            Observer {
                dailyWeatherSection.update(it.daily.map { dailyWeather ->
                    DailyWeatherContentItem(dailyWeather)
                })
            })
        dailyWeatherGroupAdapter.add(dailyWeatherSection)

    }

    /**
     * TODO: fix emergency
     */
    private fun String.toIconUrl(): String {
        return "http://openweathermap.org/img/wn/$this.png"
    }

    internal class ForecastContentItem(
        private val detailWeatherInfo: DetailWeatherInfo
    ) : BindableItem<ItemWeatherThreeHoursBinding>(
        detailWeatherInfo.dt_txt.hashCode().toLong()
    ) {
        override fun getLayout() = R.layout.item_weather_three_hours

        override fun bind(viewBinding: ItemWeatherThreeHoursBinding, position: Int) {
            /**
             * TODO: time
             */
            viewBinding.threeHoursWeatherTime.text = "9:00"
            viewBinding.threeHoursWeatherImage.load(detailWeatherInfo.weather[0].icon?.toIconUrl())
            viewBinding.threeHoursWeatherTemp.text =
                detailWeatherInfo.main.temp.toFromKelvinToCelsius().toInt().toString()
        }
    }

    internal class DailyWeatherContentItem(private val dailyWeather: DailyWeather) :
        BindableItem<ItemWeatherDailyBindingImpl>(dailyWeather.dt.hashCode().toLong()) {
        override fun getLayout() = R.layout.item_weather_daily

        override fun bind(viewBinding: ItemWeatherDailyBindingImpl, position: Int) {

            viewBinding.dailyWeatherDate.text = dateToMonthDay(dailyWeather.dt.toLong())
            viewBinding.dailyWeatherDaysOfWeeks.text = dateToDaysOfWeeks(dailyWeather.dt.toLong())
            viewBinding.dailyWeatherImage.load(dailyWeather.weather[0].icon?.toIconUrl())
            viewBinding.dailyWeatherTempMax.text = dailyWeather.temp.max.toFromKelvinToCelsius().toInt().toString()
            viewBinding.dailyWeatherTempMin.text = dailyWeather.temp.min.toFromKelvinToCelsius().toInt().toString()
        }

        private fun dateToMonthDay(dateTime: Long): String {
            val df = SimpleDateFormat("M/dd")
            val date = Date(dateTime * 1000)
            return df.format(date)
        }

        private fun dateToDaysOfWeeks(dateTime: Long): String {
            val dfYear = SimpleDateFormat("yyyy")
            val dfMonth = SimpleDateFormat("MM")
            val dfDay = SimpleDateFormat("dd")

            val date = Date(dateTime * 1000)
            val cal = Calendar.getInstance()
            cal.set(
                dfYear.format(date).toInt(),
                dfMonth.format(date).toInt() - 1,
                dfDay.format(date).toInt()
            )
            return when (cal.get(Calendar.DAY_OF_WEEK)) {
                Calendar.SUNDAY -> "Sun"
                Calendar.MONDAY -> "Mon"
                Calendar.TUESDAY -> "The"
                Calendar.WEDNESDAY -> "Wed"
                Calendar.THURSDAY -> "Thu"
                Calendar.FRIDAY -> "Fri"
                Calendar.SATURDAY -> "Sat"
                else -> ""
            }
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