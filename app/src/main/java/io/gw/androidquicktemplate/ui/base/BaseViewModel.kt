package io.gw.androidquicktemplate.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {
    protected val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    protected val _networkError = MutableStateFlow<String?>(null)
    val networkError = _networkError.asSharedFlow()

    fun launchNetwork(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                _isLoading.update { true }
                block()
            } catch (e: Exception) {

            } finally {
                _isLoading.update { false }
            }
        }
    }
}