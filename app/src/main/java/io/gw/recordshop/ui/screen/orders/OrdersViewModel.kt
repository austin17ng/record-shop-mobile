package io.gw.recordshop.ui.screen.orders

import androidx.lifecycle.viewModelScope
import io.gw.recordshop.data.Order
import io.gw.recordshop.remote.RecordShopApiService
import io.gw.recordshop.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrdersViewModel(
    private val recordShopApiService: RecordShopApiService,
): BaseViewModel() {
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders = _orders.asStateFlow()

    init {
        getOrders()
    }
    private fun getOrders() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = recordShopApiService.getAllOrders()
            if (response.isSuccessful) {
                response.body()?.let {
                    _orders.value = it
                }
            }
        }
    }

}