package com.example.bite.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.bite.models.Product
import com.example.bite.navigation.Screen
import com.example.bite.ui.components.ActionButton
import com.example.bite.viewmodel.CartViewModel
import com.example.bite.ui.components.ProductRow
import com.example.bite.viewmodel.CartViewModelFactory


@Composable
fun CartScreenWithoutNavBar(
    navController: NavHostController,
    tokenProvider: () -> String?
) {
    val factory = CartViewModelFactory(tokenProvider)
    val cartViewModel: CartViewModel = viewModel(factory = factory)

    LaunchedEffect(Unit) {
        cartViewModel.loadCartProducts()
    }

    val cartProducts by cartViewModel.cartProducts.collectAsState()
    val cartTotal by cartViewModel.cartTotal.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC)) // Fondo beige
    ) {
        if (cartProducts.isEmpty()) {
            // Mostrar diseño del carrito vacío
            EmptyCart(navController)
        } else {
            // Mostrar carrito con productos
            FilledCart(navController, cartProducts, cartTotal, cartViewModel)
        }
    }
}

@Composable
fun EmptyCart(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Shopping Carts",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Image(
            painter = rememberAsyncImagePainter("https://res.cloudinary.com/dlpnivtso/image/upload/v1725934145/Carrito_xtiuyk.png"),
            contentDescription = "Empty Cart",
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Add items to start filling a cart",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )

        Text(
            text = "When you add items your cart will appear here",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        ActionButton(
            text = "Buy now",
            onClick = { navController.navigate(Screen.Home.route) },
            buttonColor = Color.Black,
            textColor = Color.White,
            height = 40,
            fontSize = 14,
            width = 0.5f
        )
    }
}

@Composable
fun FilledCart(
    navController: NavHostController,
    cartProducts: List<Product>,
    cartTotal: Double,
    cartViewModel: CartViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Shopping Cart",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Lista de productos en el carrito
        cartProducts.forEach { product ->
            ProductRow(product = product) {
                cartViewModel.addProductToCart(product, 1) // Incrementar cantidad en 1
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Total del carrito
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "CRC $cartTotal",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50) // Verde
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón para confirmar el carrito
        ActionButton(
            text = "Confirm Purchase",
            onClick = {
                cartViewModel.confirmCart(navController)
            },
            buttonColor = Color(0xFF00C3BE),
            textColor = Color.White,
            height = 48,
            fontSize = 16
        )
    }
}

