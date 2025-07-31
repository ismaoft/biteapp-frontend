package com.example.bite.navigation

import CategoryDetailScreen
import HomeScreen
import HomeViewModel
import RestaurantDetailScreen
import SearchScreen
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bite.ui.screens.CartScreenWithoutNavBar
import com.example.bite.ui.screens.LoginScreen
import com.example.bite.ui.screens.NotificationsScreen
import com.example.bite.ui.screens.OrderConfirmationScreen
import com.example.bite.ui.screens.OrderTrackingScreen
import com.example.bite.ui.screens.ProfileScreen
import com.example.bite.viewmodel.AuthViewModel
import com.example.bite.viewmodel.EntregaViewModel
import com.example.bite.viewmodel.EntregaViewModelFactory
import com.example.bite.viewmodel.HomeViewModelFactory

@Composable
fun UserNavGraph(navController: NavHostController, authViewModel: AuthViewModel) {
    val homeViewModelFactory = remember {
        HomeViewModelFactory { authViewModel.token.value }
    }
    val homeViewModel: HomeViewModel = viewModel(factory = homeViewModelFactory)


    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                authViewModel = authViewModel,
                homeViewModel = homeViewModel
            )
        }
        composable(Screen.Search.route) {
            SearchScreen(navController = navController, homeViewModel = homeViewModel)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController, authViewModel = authViewModel)
        }
        composable(Screen.Cart.route) {
            CartScreenWithoutNavBar(
                navController = navController,
                tokenProvider = { authViewModel.getToken() }
            )
        }
        composable(Screen.Notifications.route) {
            val entregaViewModelFactory = remember {
                EntregaViewModelFactory { authViewModel.token.value }
            }
            NotificationsScreen(navController = navController, entregaViewModelFactory = entregaViewModelFactory)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController = navController, authViewModel = authViewModel)
        }

        // Ruta para CategoryDetailScreen
        composable(
            route = "category_detail/{categoryName}",
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
            CategoryDetailScreen(navController = navController, categoryName = categoryName, homeViewModel = homeViewModel)
        }


        // Ruta para RestaurantDetailScreen
        composable(
            route = "restaurant_detail/{restaurantId}",
            arguments = listOf(navArgument("restaurantId") { type = NavType.IntType })
        ) { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getInt("restaurantId") ?: 0
            RestaurantDetailScreen(
                navController = navController,
                restaurantId = restaurantId,
                homeViewModel = homeViewModel,
                tokenProvider = { authViewModel.getToken() } // Pasa el tokenProvider aquí
            )
        }

        composable(Screen.OrderConfirmation.route) { OrderConfirmationScreen(navController) }

        composable(
            route = "${Screen.OrderTracking.route}/{entregaId}",
            arguments = listOf(navArgument("entregaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val entregaId = backStackEntry.arguments?.getInt("entregaId") ?: 0
            val entregaViewModelFactory = remember {
                EntregaViewModelFactory { authViewModel.token.value }
            }
            val entregaViewModel: EntregaViewModel = viewModel(factory = entregaViewModelFactory)
            OrderTrackingScreen(navController = navController, entregaViewModel = entregaViewModel, entregaId = entregaId)
        }




    }
}


