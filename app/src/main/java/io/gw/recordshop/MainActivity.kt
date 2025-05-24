package io.gw.recordshop

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
import io.gw.recordshop.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                var selectedItem by remember { mutableStateOf(UiBottomNavigationItem.HOME) }
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                LaunchedEffect(navBackStackEntry?.destination) {
                    Log.d("MainActivity", "onCreate: ${navBackStackEntry?.destination}")
                    Log.d("MainActivity", "onCreate: ${navBackStackEntry?.destination?.route}")
                    Log.d("MainActivity", "onCreate: ${navBackStackEntry?.destination?.id}")
                }

                Scaffold(
                    modifier = Modifier
                        .navigationBarsPadding(),
                    bottomBar = {
                        UiBottomNavigation(
                            selectedItem = selectedItem,
                            onClick = {
                                selectedItem = it
                                when (it) {
                                    UiBottomNavigationItem.HOME -> {
                                        navController.navigate(HomeDestination) {
                                            popUpTo(HomeDestination) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                    UiBottomNavigationItem.FAV -> {
                                        navController.navigate(FavouriteDestination) {
                                            popUpTo(FavouriteDestination) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                    UiBottomNavigationItem.CART -> {
                                        navController.navigate(CartDestination) {
                                            popUpTo(CartDestination) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                    UiBottomNavigationItem.PROFILE -> {

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
            CartScreen()
        }
    }
}