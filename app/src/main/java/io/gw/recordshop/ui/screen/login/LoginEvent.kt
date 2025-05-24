package io.gw.recordshop.ui.screen.login

sealed class LoginEvent {
    data class UsernameChanged(val username: String): LoginEvent()
    data class PasswordChanged(val password: String): LoginEvent()
    object Login: LoginEvent()
}