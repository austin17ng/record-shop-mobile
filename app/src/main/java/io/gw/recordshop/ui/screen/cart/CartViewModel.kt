package io.gw.recordshop.ui.screen.cart

import androidx.lifecycle.viewModelScope
import io.gw.recordshop.data.CartItem
import io.gw.recordshop.data.Order
import io.gw.recordshop.remote.RecordShopApiService
import io.gw.recordshop.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class CartState {
    object Init: CartState()
    object Loading: CartState()
    data class LoggedIn(val cartItems: List<CartItem>): CartState() {
        fun getTotalPrice(): Double {
            return cartItems.sumOf { (it.album?.price ?: 0.0) * (it.quantity ?: 0) }
        }
    }
    object LoggedOut: CartState()
}

sealed class CartEvent {
    object PayForCart: CartEvent()
}



class CartViewModel(
    private val recordShopApiService: RecordShopApiService
): BaseViewModel() {
    private val _state = MutableStateFlow<CartState>(CartState.Init)
    val state = _state.asStateFlow()

    private val _order = MutableSharedFlow<Order>()
    val order = _order.asSharedFlow()

    init {
        getCart()
    }

    fun onEvent(event: CartEvent) {
        when (event) {
            CartEvent.PayForCart -> {
                payForCart()
            }
        }
    }

    fun getCart() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.update { CartState.Loading }
                delay(1000L)
                val response = recordShopApiService.getCart()
                if (response.isSuccessful) {
                    _state.value = CartState.LoggedIn(response.body() ?: emptyList())
                } else if (response.code() == 401) {
                    _state.value = CartState.LoggedOut
                } else {
                    _networkError.emit(response.code().toString())
                }

            } catch (e: Exception) {
                _state.update { CartState.Init }
            } finally {

            }

        }
    }

    fun payForCart() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.value = true
                delay(1500L)
                val response = recordShopApiService.payForCart()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _order.emit(it)
                        _state.update { CartState.Init }
                    }
                } else {
                    _networkError.emit(response.code().toString())
                }
            } catch (e: Exception) {
                _networkError.emit(e.message ?: "Unknown error")
            } finally {
                _isLoading.value = false
            }
        }
    }
}