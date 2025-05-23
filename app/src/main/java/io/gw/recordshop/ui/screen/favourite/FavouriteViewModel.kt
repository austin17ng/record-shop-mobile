package io.gw.recordshop.ui.screen.favourite

import io.gw.recordshop.data.Album
import io.gw.recordshop.ui.base.BaseViewModel

data class FavouriteState(
    val albums: List<Album> = emptyList(),
)

sealed class FavouriteEvent {

}

class FavouriteViewModel: BaseViewModel() {
}