package io.gw.recordshop.ui.screen.cart

import io.gw.recordshop.data.CartItem
import io.gw.recordshop.remote.RecordShopApiService
import io.gw.recordshop.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class CartState(
    val cartItems: List<CartItem> = emptyList(),
) {
    fun getTotalPrice(): Double {
        return cartItems.sumOf { (it.album?.price ?: 0.0) * (it.quantity ?: 0) }
    }
}

sealed class CartEvent {

}



class CartViewModel(
    private val recordShopApiService: RecordShopApiService
): BaseViewModel() {
    private val _state = MutableStateFlow<CartState>(CartState())
    val state = _state.asStateFlow()

    init {
        getCart()
    }

    fun getCart() {
        launchNetwork {
            delay(3_000L)
            val response = recordShopApiService.getCart()
            if (response.isSuccessful) {
                _state.value = _state.value.copy(cartItems = response.body() ?: emptyList())
            } else {
                response.code()
                _networkError.emit(response.code().toString())
            }
        }

    }
}