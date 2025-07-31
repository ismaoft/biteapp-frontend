package com.example.bite.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
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
import com.example.bite.viewmodel.AuthViewModel
import com.example.bite.viewmodel.RestaurantViewModel

@Composable
fun AdminSettingsScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    val restaurantViewModel: RestaurantViewModel = viewModel()
    val restaurant by restaurantViewModel.restaurant.collectAsState()
    val loading by restaurantViewModel.loading.collectAsState()

    // Simulación para obtener el restaurante con ID 1
    LaunchedEffect(Unit) {
        restaurantViewModel.fetchRestaurant(1) // Cambia el ID según sea necesario
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC)) // Fondo beige para coherencia con la app
            .padding(16.dp)
    ) {
        if (loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Cargando...", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        } else if (restaurant != null) {
            // Imagen del restaurante
            Image(
                painter = rememberAsyncImagePainter(restaurant!!.imagenUrl),
                contentDescription = "Restaurant Image",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre del restaurante centrado
            Text(
                text = restaurant!!.nombre,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Divider(color = Color.Gray, thickness = 1.dp)

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de información con paddings y mayor separación
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Dirección:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = restaurant!!.direccion, fontSize = 16.sp, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Teléfono:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = restaurant!!.telefono, fontSize = 16.sp, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Categoría:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = restaurant!!.categoria, fontSize = 16.sp, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Horario de Atención:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = restaurant!!.horarioAtencion, fontSize = 16.sp, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Descripción:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = restaurant!!.descripcion, fontSize = 16.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Divider(color = Color.Gray, thickness = 1.dp)

            Spacer(modifier = Modifier.height(24.dp))

            // Botones adicionales (como editar menú o cambiar contraseña)
            Text(
                text = "Cambiar Email/Contraseña",
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
                text = "Editar Menú",
                fontSize = 14.sp,
                color = Color.Black,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {
                        // Lógica para editar menú
                    }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de Log Out
            ActionButton(
                text = "Log Out",
                onClick = {
                    authViewModel.logout() // Llamar al método logout
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true } // Limpiar el stack de navegación
                    }
                },
                buttonColor = Color.Red,  // Color rojo típico para el log out
                textColor = Color.White,  // Texto blanco
                height = 50,              // Altura del botón
                fontSize = 16,            // Tamaño de la fuente
                width = 0.7f              // 70% del ancho de la pantalla
            )
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error al cargar los datos del restaurante.", color = Color.Red)
            }
        }
    }
}





