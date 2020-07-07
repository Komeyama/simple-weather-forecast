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
import com.komeyama.simple.weather.core.extentions.assistedActivityViewModels
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
    private val sessionsViewModel: WeatherListViewModel by assistedActivityViewModels {
        weatherListViewModelProvider.get()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //sessionsViewModel.callWeatherRepositoryMethod()
        //sessionsViewModel.callWeatherRepositoryDbMethod()
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
        sessionsViewModel.ForecastInfoLiveData.observe(viewLifecycleOwner, Observer { forecastInfoList ->
            forecastInfoList.forEach {
                items.add(ForecastContentItem(requireContext()))
            }
            groupAdapter.update(items)
        })


    }

    /**
     * todo
     */
    internal class ForecastContentItem(var context: Context) :
        BindableItem<ItemForecastContentBinding>() {
        override fun getLayout() = R.layout.item_forecast_content

        override fun bind(viewBinding: ItemForecastContentBinding, position: Int) {
            viewBinding.topCardFavoritePlace.setColorFilter(
                ContextCompat.getColor(
                    context,
                    R.color.colorLight_d3
                )
            )
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