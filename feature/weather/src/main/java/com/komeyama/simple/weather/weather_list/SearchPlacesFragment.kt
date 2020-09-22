package com.komeyama.simple.weather.weather_list

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import coil.api.load
import com.komeyama.simple.weather.core.extentions.assistedActivityViewModels
import com.komeyama.simple.weather.model.CityIds
import com.komeyama.simple.weather.weather_list.databinding.ItemHeadderBinding
import com.komeyama.simple.weather.weather_list.databinding.ItemPlaceNameBinding
import com.komeyama.simple.weather.weather_list.viewmodel.SearchPlacesViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.GroupieViewHolder
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.search_place.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class SearchPlacesFragment : DaggerFragment() {

    @Inject
    lateinit var searchPlacesViewModelProvider: Provider<SearchPlacesViewModel>
    private val searchPlacesViewModel: SearchPlacesViewModel by assistedActivityViewModels {
        searchPlacesViewModelProvider.get()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val customTitle = requireActivity().findViewById<TextView>(R.id.custom_toolbar_title)
        customTitle.text = ""
        return inflater.inflate(
            R.layout.search_place,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<GroupieViewHolder<*>>()
        search_place_recycler_view.adapter = groupAdapter
        val section = Section()
        section.setHeader(HeaderItem())

        searchPlacesViewModel.uiModel.observe(viewLifecycleOwner,
            Observer {
                if (it.searchResult.query == "") {
                    return@Observer
                }

                /**
                 * TODO: fix emergency
                 */
                section.update(
                    it.searchResult.forecastInfo.map { forecastInfo ->
                        forecastInfo.id.let { cityName ->
                            Timber.d("forecast!! %s", cityName)
                            ForecastContentItem(
                                cityName = cityName,
                                forecastImageUrl = "",
                                viewModel = searchPlacesViewModel
                            )
                        }
                    }
                )
            }
        )
        groupAdapter.add(section)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_search_menu, menu)
        val searchView = menu.findItem(R.id.search_view).actionView as SearchView
        searchView.isIconified = false
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                searchPlacesViewModel.updateSearchQuery(s)
                return false
            }
        })
        searchView.setOnCloseListener {
            searchView.setQuery("", true)
            true
        }
    }

    internal data class ForecastContentItem(
        private val cityName: String,
        private val forecastImageUrl: String?,
        private val viewModel: SearchPlacesViewModel
    ) : BindableItem<ItemPlaceNameBinding>() {
        override fun getLayout() = R.layout.item_place_name

        override fun bind(viewBinding: ItemPlaceNameBinding, position: Int) {
            viewBinding.searchPlaceCityName.text = cityName
            viewBinding.searchPlaceForecastImage.load(forecastImageUrl)
            viewBinding.searchPlaceCardTop.setOnClickListener { v ->
                CityIds.values()
                    .firstOrNull { it.id.conversionsInSpecialCases() == cityName.conversionsInSpecialCases() }?.id.apply {
                        if (this != null) {
                            viewModel.refreshRepository(this)
                            val navigateId =
                                SearchPlacesFragmentDirections.actionSearchPlaceToDetailForecast(
                                    cityId = this,
                                    detailTitle = cityName
                                )
                            Navigation.findNavController(v).navigate(navigateId)
                        }
                    }
            }
        }
    }

    internal class HeaderItem() : BindableItem<ItemHeadderBinding>() {
        override fun getLayout() = R.layout.item_headder

        override fun bind(viewBinding: ItemHeadderBinding, position: Int) {}
    }
}

@Module
abstract class SearchPlacesFragmentModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providesLifecycleOwnerLiveData(
            searchPlacesFragment: SearchPlacesFragment
        ): LiveData<LifecycleOwner> {
            return searchPlacesFragment.viewLifecycleOwnerLiveData
        }
    }
}

/**
 * TODO: fix emergency
 */
internal fun String.toIconUrl(): String {
    return "http://openweathermap.org/img/wn/$this.png"
}