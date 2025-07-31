package com.example.bite.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.bite.navigation.Screen
import com.example.bite.ui.components.ActionButton
import com.example.bite.ui.components.InputField
import com.example.bite.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = viewModel()
) {
    // Observa el token y el usuario actual
    val token by authViewModel.token.collectAsState()
    val currentUser by authViewModel.currentUser.collectAsState()

    // Redirige al Login si el usuario no está autenticado
    LaunchedEffect(token) {
        if (token == null) {
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Profile.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Foto de perfil
        Image(
            painter = rememberAsyncImagePainter(currentUser?.imagen_url ?: "https://res.cloudinary.com/dlpnivtso/image/upload/v1615506816/qndvuubdsw8yfc1uuqqg.jpg"),
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Nombre de usuario
        Text(
            text = currentUser?.nombre ?: "Nombre no disponible",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campos editables reutilizando el componente InputField
        InputField(label = "Email", value = currentUser?.email ?: "", onValueChange = {})
        InputField(label = "Dirección", value = currentUser?.direccion ?: "", onValueChange = {})
        InputField(label = "Teléfono", value = currentUser?.telefono ?: "", onValueChange = {})
        InputField(label = "Preferencias", value = currentUser?.preferencias ?: "", onValueChange = {})
        InputField(label = "Alergias", value = currentUser?.alergias ?: "", onValueChange = {})

        Spacer(modifier = Modifier.height(16.dp))

        // Botones estilizados como hipervínculos
        Text(
            text = "Change Email/Password",
            fontSize = 14.sp,
            color = Color.Black,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable {
                    // Lógica para cambiar email/contraseña
                }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Add/Change Food Allergens",
            fontSize = 14.sp,
            color = Color.Black,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable {
                    // Lógica para cambiar alergias alimentarias
                }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de Log Out
        ActionButton(
            text = "Log Out",
            onClick = {
                authViewModel.logout()
                navController.navigate(Screen.Login.route) {
                    popUpTo(0) { inclusive = true }
                }
            },
            buttonColor = Color.Red,
            textColor = Color.White,
            height = 50,
            fontSize = 16,
            width = 0.7f
        )
    }
}

