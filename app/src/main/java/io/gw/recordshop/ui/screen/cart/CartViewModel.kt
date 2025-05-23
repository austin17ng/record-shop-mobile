package io.gw.recordshop.ui.screen.cart

import io.gw.recordshop.data.CartItem
import io.gw.recordshop.remote.RecordShopApiService
import io.gw.recordshop.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class CartState(
    val cartItems: List<CartItem> = emptyList(),
) {
    fun getTotalPrice(): Double {
        return cartItems.sumOf { (it.album.price ?: 0.0) * it.quantity }
    }
}

sealed class CartEvent {

}



class CartViewModel(
    private val recordShopApiService: RecordShopApiService
): BaseViewModel() {
    private val _state = MutableStateFlow<CartState>(CartState())
    val state = _state.asStateFlow()
}