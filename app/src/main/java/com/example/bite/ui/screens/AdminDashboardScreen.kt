package com.example.bite.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.bite.models.Product
import com.example.bite.ui.components.ActionButton
import com.example.bite.viewmodel.AdminDashboardViewModel

@Composable
fun AdminDashboardScreen(viewModel: AdminDashboardViewModel = viewModel()) {
    // Obteniendo la lista de productos desde el ViewModel
    val products by viewModel.products.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC))
            .padding(16.dp)
    ) {
        // Lista de productos con LazyColumn
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(products) { product ->
                ProductRow(product = product)
                Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
            }
        }

        // Botón de "Add Item" redondeado
        ActionButton(
            text = null,  // Sin texto, solo ícono
            icon = Icons.Filled.Add,  // Ícono de añadir
            onClick = { /* Navega a la pantalla de agregar producto */ },
            buttonColor = Color(0xFF00C3BE), // Color del botón
            textColor = Color.White,         // Color del texto
            height = 40,                     // Tamaño más pequeño
            fontSize = 14,                   // Tamaño de texto reducido
            width = 0.2f,                    // Ancho más pequeño
            roundedCorners = true,           // Botón redondeado
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 16.dp)
        )
    }
}

@Composable
fun ProductRow(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Imagen del producto
        Image(
            painter = rememberAsyncImagePainter(product.imagenUrl ?: ""),
            contentDescription = product.nombre,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Información del producto
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = product.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = product.descripcion,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
    }
}
