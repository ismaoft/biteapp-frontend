package com.example.bite.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.bite.models.AppUser

@Composable
fun LoginScreen(navController: NavHostController, authViewModel: AuthViewModel = viewModel()) {
    var nombre by remember { mutableStateOf("") }  // Cambiado de 'username' a 'nombre'
    var password by remember { mutableStateOf("") }
    var tokenFCM by remember { mutableStateOf("") }

    val currentUser by authViewModel.currentUser.collectAsState()
    val loginError by authViewModel.loginError.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F7DF))
    ) {

        Image(
            painter = rememberAsyncImagePainter("https://res.cloudinary.com/dlpnivtso/image/upload/v1724861579/BiteLogo_kkgbbc.png"),
            contentDescription = "Bite Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.TopStart)
                .padding(start = 20.dp, top = 20.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 43.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.Start
        ) {

            InputField(label = "Nombre", value = nombre, onValueChange = { nombre = it })  // Cambiado 'Email' a 'Nombre'
            Spacer(modifier = Modifier.height(16.dp))
            InputField(label = "Password", value = password, onValueChange = { password = it }, isPassword = true)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Register",
                color = Color.Black,
                fontSize = 20.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.Register1.route)
                    }
                    .padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ActionButton(
                text = "Sign In",
                onClick = {
                    authViewModel.loginWithFirebase(nombre, password)
                },
                buttonColor = Color(0xFF41B9B2),
                textColor = Color.White,
                height = 50,
                fontSize = 16,
                width = 0.9f
            )

            loginError?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it, color = Color.Red)
            }
        }

        currentUser?.let { user: AppUser ->
            LaunchedEffect(user) {
                if (user.Roles.firstOrNull()?.name == "admin") {
                    navController.navigate(Screen.AdminHome.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                        launchSingleTop = true
                    }
                } else {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }
    }
}
