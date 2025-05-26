package io.gw.recordshop.ui.screen.album

import androidx.lifecycle.viewModelScope
import io.gw.recordshop.data.AddToCartRequest
import io.gw.recordshop.data.Album
import io.gw.recordshop.data.CartItem
import io.gw.recordshop.remote.RecordShopApiService
import io.gw.recordshop.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AlbumDetailsState(
    val album: Album = Album(),
)

sealed class AlbumDetailsEvent {
}

class AlbumDetailsViewModel(
    private val recordShopApiService: RecordShopApiService
): BaseViewModel() {
    private var albumId: Long? = null

    private val _state = MutableStateFlow(AlbumDetailsState())
    val state: StateFlow<AlbumDetailsState> = _state.asStateFlow()

    private val _addedCartItem = MutableSharedFlow<CartItem>()
    val addedCartItem: SharedFlow<CartItem> = _addedCartItem.asSharedFlow()

    fun getAlbum(albumId: Long) {
        this.albumId = albumId
        launchSilent {
            val album = recordShopApiService.getAlbum(albumId)
            _state.update {
                it.copy(album = album)
            }
        }
    }

    fun addToCart() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.value = true
                delay(1000L)
                val response = albumId?.let { recordShopApiService.addToCart(AddToCartRequest(it)) }
                if (response?.isSuccessful == true) {
                    response.body()?.let {
                        _addedCartItem.emit(it)
                    }
                }
            } catch (e: Exception) {

            } finally {
                _isLoading.value = false
            }
        }

    }


}