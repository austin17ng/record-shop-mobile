package io.gw.recordshop.ui.screen.login

data class LoginState (
    val username: String = "",
    val password: String = ""
)

sealed class LoginResult {
    object Loading: LoginResult()
    data class Success(val token: String): LoginResult()
    data class Error(val error: String): LoginResult()
}