package io.gw.recordshop.ui.screen.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import io.gw.recordshop.ui.screen.home.HomeDestination
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = koinViewModel<LoginViewModel>()
) {
    var showBottomSheet by remember { mutableStateOf(false) }


    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(
            content = {
                Text(text = "Login")
            },
            onClick = {
                navController.navigate(HomeDestination)
            }
        )
    }

}