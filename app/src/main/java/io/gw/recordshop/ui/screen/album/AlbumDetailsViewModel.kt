package io.gw.recordshop.ui.screen.album

import io.gw.recordshop.data.Album
import io.gw.recordshop.ui.base.BaseViewModel

data class AlbumDetailsState(
    val album: Album,
)

sealed class AlbumDetailsEvent {
}

class AlbumDetailsViewModel: BaseViewModel() {
}