package io.gw.recordshop.ui.screen.album

import io.gw.recordshop.data.Album
import io.gw.recordshop.remote.RecordShopApiService
import io.gw.recordshop.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AlbumDetailsState(
    val album: Album = Album(),
)

sealed class AlbumDetailsEvent {
}

class AlbumDetailsViewModel(
    private val recordShopApiService: RecordShopApiService
): BaseViewModel() {
    private val _state = MutableStateFlow(AlbumDetailsState())
    val state: StateFlow<AlbumDetailsState> = _state.asStateFlow()

    fun getAlbum(albumId: Long) {
        launchNetwork {

        }
    }
}