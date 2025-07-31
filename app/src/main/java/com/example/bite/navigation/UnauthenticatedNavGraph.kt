package com.example.bite.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bite.ui.screens.*
import com.example.bite.ui.screens.register.RegisterScreen1
import com.example.bite.ui.screens.register.RegisterScreen2
import com.example.bite.ui.screens.register.RegisterScreen3
import com.example.bite.viewmodel.AuthViewModel

@Composable
fun UnauthenticatedNavGraph(navController: NavHostController, authViewModel: AuthViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController, authViewModel = authViewModel)
        }
        composable(Screen.Register1.route) {
            RegisterScreen1(navController = navController, authViewModel = authViewModel)
        }
        composable(Screen.Register2.route) {
            RegisterScreen2(navController = navController, authViewModel = authViewModel)
        }
        composable(Screen.Register3.route) {
            RegisterScreen3(navController = navController, authViewModel = authViewModel)
        }
        composable(Screen.Loading.route) {
            LoadingScreen(navController = navController, authViewModel = authViewModel)
        }
    }
}
