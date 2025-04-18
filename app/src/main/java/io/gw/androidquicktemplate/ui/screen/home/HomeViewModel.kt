package io.gw.androidquicktemplate.ui.screen.home

import io.gw.androidquicktemplate.data.Product
import io.gw.androidquicktemplate.remote.AppApiService
import io.gw.androidquicktemplate.ui.base.BaseViewModel
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