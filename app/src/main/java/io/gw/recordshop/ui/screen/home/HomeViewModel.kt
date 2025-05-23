package io.gw.recordshop.ui.screen.home

import androidx.lifecycle.viewModelScope
import io.gw.recordshop.data.Album
import io.gw.recordshop.remote.RecordShopApiService
import io.gw.recordshop.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

data class HomeState(
    val homeSections: List<HomeSection> = emptyList()
)

data class HomeSection(
    val title: String,
    val albums: List<Album> = emptyList()
)

sealed class HomeEvent {
    data class OnAlbumClicked(val album: Album): HomeEvent()
}

class HomeViewModel(
    private val recordShopApiService: RecordShopApiService
) : BaseViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        getAlbums()
    }

    fun getAlbums() {
        launchSilent {
            val newArrivalAlbumsDeferred = async { recordShopApiService.getNewArrivalAlbums() }
            val trendingAlbumsDeferred = async { recordShopApiService.getTrendingAlbums() }
            val staffPicksAlbumsDeferred = async { recordShopApiService.getStaffPicksAlbums() }
            val saleAlbumsDeferred = async { recordShopApiService.getSaleAlbums() }

            val newArrivalAlbums = newArrivalAlbumsDeferred.await()
            val trendingAlbums = trendingAlbumsDeferred.await()
            val staffPicksAlbums = staffPicksAlbumsDeferred.await()
            val saleAlbums = saleAlbumsDeferred.await()

            val newArrivalsSection = HomeSection(title = "New arrivals", albums = newArrivalAlbums)
            val trendingSection = HomeSection(title = "Trending", albums = trendingAlbums)
            val staffPicksSection = HomeSection(title = "Staff picks", albums = staffPicksAlbums)
            val saleSection = HomeSection(title = "Sale", albums = saleAlbums)

            _state.update {
                it.copy(homeSections = listOf(newArrivalsSection, trendingSection, staffPicksSection, saleSection))
            }
        }
    }


}