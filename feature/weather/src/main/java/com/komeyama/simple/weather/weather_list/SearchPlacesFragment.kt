package com.komeyama.simple.weather.weather_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.komeyama.simple.weather.weather_list.databinding.ItemHeadderBinding
import com.komeyama.simple.weather.weather_list.databinding.ItemPlaceNameBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.search_place.*

class SearchPlacesFragment : DaggerFragment() {

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
        section.update(
            listOf(
                ForecastContentItem("test1"),
                ForecastContentItem("test2"),
                ForecastContentItem("test3")
            )
        )
        groupAdapter.add(section)
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