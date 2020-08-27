package com.komeyama.simple.weather.weather_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import coil.api.load
import com.komeyama.simple.weather.core.extentions.assistedActivityViewModels
import com.komeyama.simple.weather.model.CityIds
import com.komeyama.simple.weather.model.FavoritePlaceTopContent
import com.komeyama.simple.weather.weather_list.databinding.ItemForecastFavoritePlaceContentBinding
import com.komeyama.simple.weather.weather_list.databinding.ItemHeadderBinding
import com.komeyama.simple.weather.weather_list.viewmodel.FavoriteSiteViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.GroupieViewHolder
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.favorite_place.*
import javax.inject.Inject
import javax.inject.Provider

class FavoritePlaceFragment : DaggerFragment() {

    @Inject
    lateinit var favoriteSiteViewModelProvider: Provider<FavoriteSiteViewModel>
    private val favoriteSiteViewModel: FavoriteSiteViewModel by assistedActivityViewModels {
        favoriteSiteViewModelProvider.get()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.favorite_place,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<GroupieViewHolder<*>>()
        forecast_favorite_place_recycler_view.adapter = groupAdapter
        val section = Section()
        section.setHeader(HeaderItem())

        favoriteSiteViewModel.favoritePlaceListUiData.observe(
            viewLifecycleOwner,
            Observer { forecastInfoList ->
                forecastInfoList.favoritePlaceTopPageContents.map {
                    if (it.cityName == "") {
                        return@Observer
                    }
                }
                section.update(forecastInfoList.favoritePlaceTopPageContents.filter { favoritePlaceTopPageContent ->
                    favoritePlaceTopPageContent.isFavorite
                }.map { favoritePlaceTopPageContent ->
                    ForecastContentItem(
                        favoriteSiteViewModel,
                        favoritePlaceTopPageContent
                    )
                })
            })
        groupAdapter.add(section)
    }

    internal data class ForecastContentItem(
        private val viewModel: FavoriteSiteViewModel,
        private val favoritePlaceTopContent: FavoritePlaceTopContent
    ) : BindableItem<ItemForecastFavoritePlaceContentBinding>(
        favoritePlaceTopContent.cityName.hashCode().toLong()
    ) {
        override fun getLayout() = R.layout.item_forecast_favorite_place_content

        /**
         * todo: Register the forecast id in the database so that it can be retrieved here.
         * todo: loading
         * todo: livedata process
         */
        override fun bind(viewBinding: ItemForecastFavoritePlaceContentBinding, position: Int) {

            viewBinding.topCardFavoritePlaceText.text = favoritePlaceTopContent.cityName
            viewBinding.topCardFavoritePlaceTodayWeatherText.text = favoritePlaceTopContent.telop
            viewBinding.topCardFavoritePlaceTodayWeatherImage.load(favoritePlaceTopContent.imgUrl)
            viewBinding.topCardFavoritePlaceTodayMaxTemperatureText.text =
                favoritePlaceTopContent.maxTemperature
            viewBinding.topCardFavoritePlaceTodayMinTemperatureText.text =
                favoritePlaceTopContent.minTemperature
            viewBinding.topCardFavoritePlaceFavoritePlace.setOnClickListener {
                CityIds.values()
                    .firstOrNull { it.id.conversionsInSpecialCases() == favoritePlaceTopContent.cityName.conversionsInSpecialCases() }?.id.apply {
                        this?.let { id ->
                            viewModel.favorite(id)
                        }
                    }
            }
            viewBinding.forecastFavoritePlaceCardTop.setOnClickListener { v ->
                CityIds.values()
                    .firstOrNull { it.id.conversionsInSpecialCases() == favoritePlaceTopContent.cityName.conversionsInSpecialCases() }?.id.apply {
                        if (this != null) {
                            val navigateId =
                                FavoritePlaceFragmentDirections.actionFavoriteSiteToDetailForecast(
                                    cityId = this,
                                    cityLat = favoritePlaceTopContent.lat ?: 0.0F,
                                    cityLon = favoritePlaceTopContent.lon ?: 0.0F
                                )
                            Navigation.findNavController(v).navigate(navigateId)
                        }
                    }
            }

            viewBinding.content = favoritePlaceTopContent
        }
    }

    internal class HeaderItem() : BindableItem<ItemHeadderBinding>() {
        override fun getLayout() = R.layout.item_headder

        override fun bind(viewBinding: ItemHeadderBinding, position: Int) {}
    }
}

@Module
abstract class FavoriteSiteFragmentModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providesLifecycleOwnerLiveData(
            favoriteSiteFragment: FavoritePlaceFragment
        ): LiveData<LifecycleOwner> {
            return favoriteSiteFragment.viewLifecycleOwnerLiveData
        }
    }
}