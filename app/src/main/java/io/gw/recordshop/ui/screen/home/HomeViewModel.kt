package io.gw.recordshop.ui.screen.home

import io.gw.recordshop.data.Album
import io.gw.recordshop.data.Product
import io.gw.recordshop.remote.AppApiService
import io.gw.recordshop.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeState(
    val homeSections: List<HomeSection> = emptyList()
)

data class HomeSection(
    val title: String,
    val albums: List<Album> = emptyList()
)

sealed class HomeEvent {

}

class HomeViewModel(

) : BaseViewModel() {


}