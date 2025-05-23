package io.gw.recordshop.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.SocketTimeoutException

abstract class BaseViewModel: ViewModel() {
    protected val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    protected val _networkError = MutableSharedFlow<String>()
    val networkError = _networkError.asSharedFlow()

    private val defaultExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is SocketTimeoutException -> {
                viewModelScope.launch {
                    _networkError.emit("Time out")
                }
            }
            else -> {

            }
        }
    }

    fun launchNetwork(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.IO + defaultExceptionHandler) {
            try {
                _isLoading.update { true }
                block()
            } catch (e: Exception) {
                _isLoading.update { false }

            } finally {
                _isLoading.update { false }
            }
        }
    }

    fun launchSilent(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.IO + defaultExceptionHandler) {
            block()
        }
    }
}