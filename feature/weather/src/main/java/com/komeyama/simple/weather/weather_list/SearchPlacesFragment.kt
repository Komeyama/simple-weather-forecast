package com.komeyama.simple.weather.weather_list

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.komeyama.simple.weather.core.extentions.assistedActivityViewModels
import com.komeyama.simple.weather.weather_list.databinding.ItemHeadderBinding
import com.komeyama.simple.weather.weather_list.databinding.ItemPlaceNameBinding
import com.komeyama.simple.weather.weather_list.viewmodel.SearchPlacesViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.search_place.*
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
        return inflater.inflate(
            R.layout.search_place,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        search_place_recycler_view.adapter = groupAdapter
        val section = Section()
        section.setHeader(HeaderItem())

        searchPlacesViewModel.uiModel.observe(viewLifecycleOwner,
            Observer {
                if (it.searchResult.query == "") {
                    return@Observer
                }

                section.update(
                    it.searchResult.forecastInfo.map { forecastInfo ->
                        forecastInfo.location?.city?.let { cityName ->
                            ForecastContentItem(cityName)
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
        private val text: String
    ) : BindableItem<ItemPlaceNameBinding>() {
        override fun getLayout() = R.layout.item_place_name

        override fun bind(viewBinding: ItemPlaceNameBinding, position: Int) {
            viewBinding.cityName.text = text
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