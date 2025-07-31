package com.example.bite.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bite.viewmodel.AuthViewModel

@Composable
fun AdminHomeScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome, Admin!", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        // Puedes agregar más componentes específicos del administrador aquí
        Button(onClick = { /* Agrega la lógica para la acción de administrador */ }) {
            Text("Go to Reports")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { /* Agrega la lógica para otra acción */ }) {
            Text("Go to Settings")
        }
    }
}
