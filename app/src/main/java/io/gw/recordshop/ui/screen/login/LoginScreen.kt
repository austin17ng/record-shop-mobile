package io.gw.recordshop.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.gw.recordshop.ui.component.UiButton
import io.gw.recordshop.ui.screen.home.HomeDestination
import io.gw.recordshop.ui.theme.LocalColor
import io.gw.recordshop.ui.theme.LocalTypography
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel<LoginViewModel>(),
    onLoginSuccess: (token: String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loginResult.collect {
            when (it) {
                is LoginResult.Error -> {
                    Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                }
                LoginResult.Loading -> {

                }
                is LoginResult.Success -> {
                    Toast.makeText(context, "Login success", Toast.LENGTH_LONG).show()
                    onLoginSuccess(it.token)
                }
            }
        }

    }
    LoginScreen(
        state = state,
        onEvent = {
            viewModel.onEvent(it)
        }
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = LocalColor.current.colorSoftScream)
    ) {
        Spacer(Modifier.height(64.dp))
        Text(
            text = "Login",
            style = LocalTypography.current.displaySmall,
            color = LocalColor.current.colorBlack,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(32.dp))
        OutlinedTextField(
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            label = { Text(text = "Username") },
            value = state.username,
            onValueChange = {
                onEvent(LoginEvent.UsernameChanged(it))
            },
            colors = OutlinedTextFieldDefaults.colors().copy(
                focusedIndicatorColor = LocalColor.current.colorOrange,
                focusedLabelColor = LocalColor.current.colorOrange
            )
        )
        Spacer(Modifier.height(32.dp))
        OutlinedTextField(
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            label = { Text(text = "Password") },
            value = state.password,
            onValueChange = {
                onEvent(LoginEvent.PasswordChanged(it))
            },
            colors = OutlinedTextFieldDefaults.colors().copy(
                focusedIndicatorColor = LocalColor.current.colorOrange,
                focusedLabelColor = LocalColor.current.colorOrange
            )
        )
        Spacer(Modifier.height(32.dp))
        UiButton(
            text = "Login",
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            onEvent(LoginEvent.Login)
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    val state = LoginState(username = "username", password = "password")
    LoginScreen(state) {}
}