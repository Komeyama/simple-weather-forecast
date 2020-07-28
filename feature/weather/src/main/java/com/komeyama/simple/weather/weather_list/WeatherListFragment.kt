package com.komeyama.simple.weather.weather_list

import android.os.Bundle
import android.view.*
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import coil.api.load
import com.komeyama.simple.weather.core.extentions.assistedActivityViewModels
import com.komeyama.simple.weather.model.PrefectureIds
import com.komeyama.simple.weather.model.TopPageContent
import com.komeyama.simple.weather.weather_list.databinding.ItemForecastPrefectureContentBinding
import com.komeyama.simple.weather.weather_list.databinding.ItemHeadderBinding
import com.komeyama.simple.weather.weather_list.viewmodel.WeatherListViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
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
        setHasOptionsMenu(true)
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
        val section = Section()
        section.setHeader(HeaderItem())
        /**
         * todo:fix update process
         */
        weatherListViewModel.forecastInfoLiveData.observe(
            viewLifecycleOwner,
            Observer { forecastInfoList ->
                section.update(forecastInfoList.map { topPageContent ->
                    ForecastContentItem(topPageContent)
                })
            })
        groupAdapter.add(section)
    }
    
    internal class ForecastContentItem(
        private val topPageContent: TopPageContent
    ) : BindableItem<ItemForecastPrefectureContentBinding>(
        topPageContent.prefectureName.hashCode().toLong()
    ) {
        override fun getLayout() = R.layout.item_forecast_prefecture_content

        override fun bind(viewBinding: ItemForecastPrefectureContentBinding, position: Int) {

            viewBinding.topCardPrefectureText.text = topPageContent.prefectureName
            viewBinding.topCardPrefectureTodayWeatherText.text = topPageContent.telop
            viewBinding.topCardPrefectureTodayWeatherImage.load(topPageContent.imgUrl)
            viewBinding.topCardPrefectureTodayMaxTemperatureText.text =
                topPageContent.maxTemperature
            viewBinding.topCardPrefectureTodayMinTemperatureText.text =
                topPageContent.minTemperature
            viewBinding.forecastPrefectureCardTop.setOnClickListener { v ->
                PrefectureIds.values()
                    .firstOrNull { it.prefectureName == topPageContent.prefectureName }?.id.apply {
                        if (this != null) {
                            val navigateId =
                                WeatherListFragmentDirections.actionWeatherListToWeatherDetailList(
                                    prefectureId = this
                                )
                            findNavController(v).navigate(navigateId)
                        }
                    }
            }
        }
    }

    internal class HeaderItem() : BindableItem<ItemHeadderBinding>() {
        override fun getLayout() = R.layout.item_headder

        override fun bind(viewBinding: ItemHeadderBinding, position: Int) {}
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.toolbar_search -> {
                return false
            }
        }
        return super.onOptionsItemSelected(item)
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