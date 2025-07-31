package com.example.bite.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bite.navigation.Screen
import com.example.bite.viewmodel.EntregaViewModel
import com.example.bite.viewmodel.EntregaViewModelFactory

@Composable
fun NotificationsScreen(
    navController: NavHostController,
    entregaViewModelFactory: EntregaViewModelFactory
) {
    val entregaViewModel: EntregaViewModel = viewModel(factory = entregaViewModelFactory)

    // Observar los estados
    val entrega by entregaViewModel.entrega.collectAsState()
    val error by entregaViewModel.error.collectAsState()

    // Llama al método para cargar entregas al iniciar la pantalla
    LaunchedEffect(Unit) {
        entregaViewModel.fetchEntregas()
    }

    // Interfaz
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.navigate(Screen.Home.route) }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
                Text(
                    text = "Notifications",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            error?.let {
                Text(text = it, color = Color.Red, fontSize = 16.sp)
            }

            entrega?.let { entregaDetails ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            navController.navigate("${Screen.OrderTracking.route}/${entregaDetails.entregaId}")
                        },
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Order ${entregaDetails.transaccionId}: ${entregaDetails.estado}",
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Estimated delivery: ${entregaDetails.fechaEntregaEstimada}",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}
