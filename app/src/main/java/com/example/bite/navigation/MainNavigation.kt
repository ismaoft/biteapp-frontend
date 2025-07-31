package com.example.bite.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.bite.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation(authViewModel: AuthViewModel = viewModel()) {
    val navController = rememberNavController()
    val isAuthenticated by authViewModel.token.collectAsState(initial = null) // Escucha los cambios en el token

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (isAuthenticated != null) { // El usuario está autenticado si hay token
        val currentUser = authViewModel.currentUser.collectAsState().value
        val userRole = currentUser?.Roles?.firstOrNull()?.name ?: "user"  // Rol por defecto "user"

        when (userRole) {
            "admin" -> {
                // Configuración de navegación para el administrador
                AdminNavGraph(navController = navController, authViewModel = authViewModel)
            }
            "user" -> {
                // Carga el Scaffold solo una vez para las rutas del usuario regular
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter("https://res.cloudinary.com/dlpnivtso/image/upload/v1724861579/BiteLogo_kkgbbc.png"),
                                        contentDescription = "Logo",
                                        modifier = Modifier.size(100.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        "Home",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        modifier = Modifier.weight(1f)
                                    )
                                    IconButton(onClick = { navController.navigate(Screen.Notifications.route) }) {
                                        Icon(
                                            imageVector = Icons.Filled.Notifications,
                                            contentDescription = "Notificaciones",
                                            tint = Color.White
                                        )
                                    }
                                    IconButton(onClick = { navController.navigate(Screen.Cart.route) }) {
                                        Icon(
                                            imageVector = Icons.Filled.ShoppingCart,
                                            contentDescription = "Carrito de compras",
                                            tint = Color.White
                                        )
                                    }
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF00C3BE))
                        )
                    },
                    bottomBar = {
                        if (currentRoute !in listOf(Screen.Cart.route, Screen.Notifications.route)) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        UserNavGraph(navController = navController, authViewModel = authViewModel)
                    }
                }
            }
            else -> {
                Text("No role assigned")
            }
        }
    } else {
        // Redirige al UnauthenticatedNavGraph si no hay token (usuario no autenticado)
        UnauthenticatedNavGraph(navController = navController, authViewModel = authViewModel)
    }
}
