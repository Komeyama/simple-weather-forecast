package com.komeyama.simple.weather.weather_list

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.google.android.material.tabs.TabLayout
import com.komeyama.simple.weather.core.di.PageScope
import com.komeyama.simple.weather.core.extentions.assistedViewModels
import com.komeyama.simple.weather.model.CityIds
import com.komeyama.simple.weather.model.DailyWeather
import com.komeyama.simple.weather.model.DetailWeatherInfo
import com.komeyama.simple.weather.model.toFromKelvinToCelsius
import com.komeyama.simple.weather.weather_list.databinding.ItemWeatherDailyBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().findViewById<Toolbar>(R.id.toolbar).title = ""
        requireActivity().findViewById<TextView>(R.id.custom_toolbar_title).text =
            navArgs.detailTitle

        return inflater.inflate(
            R.layout.detail_forecast,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * date info of every three hours
         */
        val dateInfo: MutableList<String> = mutableListOf()
        detailForecastViewModel.detailForecastInfoLiveData.observe(viewLifecycleOwner,
            Observer {
                it.list.map { detailWeatherInfo ->
                    detailWeatherInfo.dt
                    dateInfo.add(
                        timeStampToMonthDay(detailWeatherInfo.dt.toLong()) + " (" + timeStampToDaysOfWeeks(
                            detailWeatherInfo.dt.toLong()
                        ) + ")"
                    )
                }
                dateInfo.distinct().map { datetime ->
                    date_tab_layout.addTab(date_tab_layout.newTab().setText(datetime))
                }
            }
        )

        date_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}

            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (ForecastContentItem.twelvePosition != -1) {}
            }
        })

        /**
         *  weather info
         */
        val groupAdapter = GroupAdapter<GroupieViewHolder<*>>()
        forecast_three_hours_weather_recycler_view.adapter = groupAdapter
        val section = Section()

        val dailyWeatherGroupAdapter = GroupAdapter<GroupieViewHolder<*>>()
        forecast_daily_weather_recycler_view.adapter = dailyWeatherGroupAdapter
        val dailyWeatherSection = Section()

        initForecastContentItem()
        detailForecastViewModel.detailForecastInfoLiveData.observe(viewLifecycleOwner,
            Observer {
                detail_today_weather_image.load(it.list[0].weather[0].icon?.toIconUrl())
                detail_today_weather_main.text = it.list[0].weather[0].main
                detail_today_temp.text =
                    it.list[0].main.temp.toFromKelvinToCelsius().toInt().toString()
                detail_today_temp_max_value.text =
                    it.list[0].main.temp_max.toFromKelvinToCelsius().toInt().toString()
                detail_today_temp_min_value.text =
                    it.list[0].main.temp_min.toFromKelvinToCelsius().toInt().toString()
                section.update(it.list.map { detailWeatherInfo ->
                    ForecastContentItem(detailWeatherInfo, date_tab_layout)
                })

                /**
                 *  Because longitude and latitude cannot be acquired by transitioning from search view.
                 */
                if (!detailForecastViewModel.hasLatLong()) {
                    it.city.coord.let { coordInfo ->
                        detailForecastViewModel.dailyForecastInfoLiveDataWithLatLon(
                            coordInfo.lat!!,
                            coordInfo.lon!!
                        )?.observe(viewLifecycleOwner,
                            Observer { weeklyForecastInfo ->
                                dailyWeatherSection.update(weeklyForecastInfo.daily.map { dailyWeather ->
                                    DailyWeatherContentItem(dailyWeather)
                                })
                            })
                        dailyWeatherGroupAdapter.add(dailyWeatherSection)
                    }

                }
            })
        groupAdapter.add(section)

        /**
         * Used for transitions from other than search view.
         */
        if (detailForecastViewModel.hasLatLong()) {
            detailForecastViewModel.dailyForecastInfoLiveData.observe(viewLifecycleOwner,
                Observer {
                    dailyWeatherSection.update(it.daily.map { dailyWeather ->
                        DailyWeatherContentItem(dailyWeather)
                    })
                })
            dailyWeatherGroupAdapter.add(dailyWeatherSection)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_favorite_meun, menu)

        val favoriteButton = menu.findItem(R.id.detail_favorite_button)
        var anim = (favoriteButton.icon as Animatable)

        detailForecastViewModel.favoriteIdLiveData.observe(
            viewLifecycleOwner,
            Observer {
                it.forEach { cityId ->
                    anim = favoriteButton.setIcon(R.drawable.favorite_thrust_on).icon as Animatable
                    if (cityId.conversionsInSpecialCases() == navArgs.cityId.conversionsInSpecialCases()) {
                        anim =
                            favoriteButton.setIcon(R.drawable.favorite_thrust_off).icon as Animatable
                        return@Observer
                    }
                }
            }
        )

        favoriteButton.setOnMenuItemClickListener {
            CityIds.values()
                .firstOrNull { it.id.conversionsInSpecialCases() == navArgs.cityId.conversionsInSpecialCases() }?.id.apply {
                    this?.let { id ->
                        detailForecastViewModel.favorite(id)
                    }
                }
            anim.start()
            false
        }

    }

    private fun initForecastContentItem() {
        ForecastContentItem.currentPos = 0
        ForecastContentItem.twelvePosition = -1
        ForecastContentItem.count = 0
    }

    /**
     * TODO: fix emergency
     */
    private fun String.toIconUrl(): String {
        return "http://openweathermap.org/img/wn/$this.png"
    }

    internal class ForecastContentItem(
        private val detailWeatherInfo: DetailWeatherInfo,
        private val tabLayout: TabLayout
    ) : BindableItem<ItemWeatherThreeHoursBinding>(
        detailWeatherInfo.dt_txt.hashCode().toLong()
    ) {
        companion object {
            var count = 0
            var currentPos = 0
            var twelvePosition = -1
        }

        override fun getLayout() = R.layout.item_weather_three_hours

        override fun bind(viewBinding: ItemWeatherThreeHoursBinding, position: Int) {
            viewBinding.threeHoursWeatherTime.text = timeStampToTime(detailWeatherInfo.dt.toLong())
            viewBinding.threeHoursWeatherImage.load(detailWeatherInfo.weather[0].icon?.toIconUrl())
            viewBinding.threeHoursWeatherTemp.text =
                detailWeatherInfo.main.temp.toFromKelvinToCelsius().toInt().toString()

            /**
             * TODO：refactor
             */
            if (twelvePosition == -1 && timeStampToTime(detailWeatherInfo.dt.toLong()) == "12:00") {
                twelvePosition = 4 - position
            }

            Timber.d(
                "pos: %s, time: %s currentPos %s, twelvePos %s, count %s",
                position,
                timeStampToTime(detailWeatherInfo.dt.toLong()),
                currentPos,
                twelvePosition,
                count
            )

            if (currentPos < position && ((position + twelvePosition) % 4) == 0 && timeStampToTime(
                    detailWeatherInfo.dt.toLong()
                ) == "12:00"
            ) {
                count += 1

            } else if (currentPos > position && ((position + twelvePosition) % 4) == 0 && timeStampToTime(
                    detailWeatherInfo.dt.toLong()
                ) == "12:00"
            ) {
                count -= 1
            }
            Handler().postDelayed(
                {
                    tabLayout.isSmoothScrollingEnabled = true
                    tabLayout.setScrollPosition(count - 1, 0f, true)
                }, 100
            )
            currentPos = position
        }

        private fun timeStampToTime(dateTime: Long): String {
            val df = SimpleDateFormat("H:mm")
            val date = Date(dateTime * 1000)
            return df.format(date)
        }
    }

    internal class DailyWeatherContentItem(private val dailyWeather: DailyWeather) :
        BindableItem<ItemWeatherDailyBinding>(dailyWeather.dt.hashCode().toLong()) {
        override fun getLayout() = R.layout.item_weather_daily

        override fun bind(viewBinding: ItemWeatherDailyBinding, position: Int) {

            viewBinding.dailyWeatherDate.text = timeStampToMonthDay(dailyWeather.dt.toLong())
            viewBinding.dailyWeatherDaysOfWeeks.text =
                timeStampToDaysOfWeeks(dailyWeather.dt.toLong())
            viewBinding.dailyWeatherImage.load(dailyWeather.weather[0].icon?.toIconUrl())
            viewBinding.dailyWeatherTempMax.text =
                dailyWeather.temp.max.toFromKelvinToCelsius().toInt().toString()
            viewBinding.dailyWeatherTempMin.text =
                dailyWeather.temp.min.toFromKelvinToCelsius().toInt().toString()
        }
    }
}

/**
 * TODO:
 */
private fun timeStampToMonthDay(dateTime: Long): String {
    val df = SimpleDateFormat("M/dd")
    val date = Date(dateTime * 1000)
    return df.format(date)
}

/**
 * TODO:
 */
private fun timeStampToDaysOfWeeks(dateTime: Long): String {
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