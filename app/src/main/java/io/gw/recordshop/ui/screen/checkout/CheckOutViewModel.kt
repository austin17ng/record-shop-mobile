package io.gw.recordshop.ui.screen.checkout

import io.gw.recordshop.data.CartItem
import io.gw.recordshop.ui.base.BaseViewModel

data class CheckOutState(
    val cartItems: List<CartItem> = emptyList(),
)

sealed class CheckOutEvent {

}

class CheckOutViewModel: BaseViewModel() {
}