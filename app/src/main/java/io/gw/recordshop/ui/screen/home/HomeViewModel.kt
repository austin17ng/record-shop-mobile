package io.gw.recordshop.ui.screen.home

import androidx.lifecycle.viewModelScope
import io.gw.recordshop.data.Album
import io.gw.recordshop.remote.RecordShopApiService
import io.gw.recordshop.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

    fun getAlbums() {
        launchNetwork {
            val newArrivalAlbums = recordShopApiService.getNewArrivalAlbums()
            val newArrivalsSection = HomeSection(title = "New arrivals", albums = newArrivalAlbums)
            _state.update {
                it.copy(homeSections = listOf(newArrivalsSection))
            }
        }
    }


}