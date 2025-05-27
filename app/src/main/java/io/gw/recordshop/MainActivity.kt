package io.gw.recordshop

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.gw.recordshop.ui.component.UiBottomNavigation
import io.gw.recordshop.ui.component.UiBottomNavigationItem
import io.gw.recordshop.ui.screen.album.AlbumDetailsDestination
import io.gw.recordshop.ui.screen.album.AlbumDetailsScreen
import io.gw.recordshop.ui.screen.cart.CartDestination
import io.gw.recordshop.ui.screen.cart.CartScreen
import io.gw.recordshop.ui.screen.favourite.FavouriteDestination
import io.gw.recordshop.ui.screen.home.HomeDestination
import io.gw.recordshop.ui.screen.home.HomeScreen
import io.gw.recordshop.ui.screen.login.LoginDestination
import io.gw.recordshop.ui.screen.login.LoginScreen
import io.gw.recordshop.ui.theme.AppTheme
import kotlinx.coroutines.launch
import androidx.core.content.edit
import io.gw.recordshop.ui.screen.orders.OrdersDestination
import io.gw.recordshop.ui.screen.orders.OrdersScreen
import io.gw.recordshop.ui.screen.profile.ProfileDestination
import io.gw.recordshop.ui.screen.profile.ProfileScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                var selectedBottomNavItem by remember { mutableStateOf(UiBottomNavigationItem.HOME) }
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier
                        .navigationBarsPadding(),
                    bottomBar = {
                        UiBottomNavigation(
                            selectedItem = selectedBottomNavItem,
                            onClick = {
                                selectedBottomNavItem = it
                                when (it) {
                                    UiBottomNavigationItem.HOME -> {
                                        navController.navigate(HomeDestination) {
                                            popUpTo(HomeDestination) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                    UiBottomNavigationItem.FAV -> {
//                                        navController.navigate(FavouriteDestination) {
//                                            popUpTo(FavouriteDestination) {
//                                                inclusive = true
//                                            }
//                                        }
                                    }
                                    UiBottomNavigationItem.CART -> {
                                        navController.navigate(CartDestination) {
                                            popUpTo(CartDestination) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                    UiBottomNavigationItem.PROFILE -> {
                                        navController.navigate(ProfileDestination) {
                                            popUpTo(ProfileDestination) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                }
                            }
                        )
                    }
                ) { padding ->
                    AppGraph(navController)
                }
            }
        }
    }
}

@Composable
fun AppGraph(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    NavHost(
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, tween(300)
            )
        },
        navController = navController,
        startDestination = HomeDestination
    ) {
        composable<HomeDestination> {
            HomeScreen(
                onAlbumClicked = {
                    it.id?.let { albumId -> navController.navigate(AlbumDetailsDestination(albumId)) }
                }
            )
        }
        composable<AlbumDetailsDestination> {
            val albumId = it.toRoute<AlbumDetailsDestination>().albumId
            AlbumDetailsScreen(albumId = albumId)
        }
        composable<CartDestination> {
            CartScreen(
                onLoginRequest = {
                    navController.navigate(LoginDestination)
                }
            )
        }
        composable<ProfileDestination> {
            ProfileScreen(
                onOrdersClicked = {
                    navController.navigate(OrdersDestination)
                }
            )
        }
        composable<OrdersDestination> {
            OrdersScreen()
        }
        composable<LoginDestination> {
            LoginScreen(
                onLoginSuccess = { token ->
                    navController.popBackStack()
                    scope.launch {
                        val sharedPref = context.getSharedPreferences(Tags.SHARED_PREF_NAME, Context.MODE_PRIVATE)
                        sharedPref.edit() {
                            putString(Tags.SHARED_PREF_TOKEN_KEY, token)
                        }
                    }
                }
            )
        }
    }
}