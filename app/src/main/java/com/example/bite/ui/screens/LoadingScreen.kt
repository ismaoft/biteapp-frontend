package com.example.bite.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.example.bite.navigation.Screen
import com.example.bite.ui.components.LoadingScreenContent
import com.example.bite.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    // Simulación de tiempo de carga
    LaunchedEffect(Unit) {
        delay(3000) // Ajusta este tiempo según tus necesidades

        // Verificar el estado del usuario
        val currentUser = authViewModel.currentUser.value
        if (currentUser != null) {
            // Navegar según el primer rol del usuario
            val userRole = currentUser.Roles.firstOrNull()?.name
            when (userRole) {
                "admin" -> navController.navigate(Screen.AdminHome.route)
                "user" -> navController.navigate(Screen.Home.route)
                else -> navController.navigate(Screen.Login.route)
            }
        } else {
            navController.navigate(Screen.Login.route) // Redirigir al login si no hay usuario
        }
    }

    // Mostrar el contenido visual de la pantalla de carga
    LoadingScreenContent(
        logoUrl = "https://tu-logo-url-aqui.com/logo.png", // Reemplaza con la URL de tu logo
        loadingText = "Ajustando preferencias..." // Texto durante la carga
    )
}
