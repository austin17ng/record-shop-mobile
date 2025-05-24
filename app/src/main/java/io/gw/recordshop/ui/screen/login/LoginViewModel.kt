package io.gw.recordshop.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.gw.recordshop.data.LoginRequest
import io.gw.recordshop.remote.RecordShopApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val recordShopApiService: RecordShopApiService
): ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _loginResult = MutableSharedFlow<LoginResult>()
    val loginResult = _loginResult.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.Login -> {
                login(state.value.username, state.value.password)
            }
            is LoginEvent.PasswordChanged -> {
                _state.update {
                    it.copy(password = event.password)
                }
            }
            is LoginEvent.UsernameChanged -> {
                _state.update {
                    it.copy(username = event.username)
                }
            }
        }
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = LoginRequest(
                    username = username.trim(),
                    password = password.trim(),
                )
                val response = recordShopApiService.login(request)
                if (response.isSuccessful) {
                    _loginResult.emit(LoginResult.Success(token = response.body()!!.token))
                } else {
                    _loginResult.emit(LoginResult.Error("Login failed"))
                }
            } catch (e: Exception) {
                _loginResult.emit(LoginResult.Error("Login failed"))
            }


        }
    }
}