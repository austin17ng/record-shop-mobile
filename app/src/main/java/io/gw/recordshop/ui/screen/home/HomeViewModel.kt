package io.gw.recordshop.ui.screen.home

import io.gw.recordshop.data.Product
import io.gw.recordshop.remote.AppApiService
import io.gw.recordshop.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val appApiService: AppApiService
) : BaseViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    fun getProducts() {
        launchNetwork {
            _products.value = appApiService.getProducts()
        }
    }

}