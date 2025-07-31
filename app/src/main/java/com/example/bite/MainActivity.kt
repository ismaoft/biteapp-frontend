package com.example.bite

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bite.navigation.MainNavigation
import com.example.bite.ui.theme.BiteTheme
import com.example.bite.viewmodel.AuthViewModel
import com.example.bite.viewmodel.AuthViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar Firebase
        FirebaseApp.initializeApp(this)

        // Solicitar permisos de notificaciones para Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
        }

        // Configuración de la interfaz de usuario
        setContent {
            BiteTheme {
                // Crear el Factory con el contexto de la aplicación
                val authViewModelFactory = AuthViewModelFactory(applicationContext)

                // Usar el Factory para inicializar el AuthViewModel
                val authViewModel: AuthViewModel = viewModel(factory = authViewModelFactory)

                // Cargar la navegación principal
                MainNavigation(authViewModel = authViewModel)
            }
        }
    }
}
