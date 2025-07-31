package com.example.bite.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.bite.ui.components.ActionButton

// Estructura de la pantalla de AdminOrders
@Composable
fun AdminOrdersScreen() {
    val orders = listOf(
        Order("Order #12345", listOf(OrderItem("Fusion cake", "1 item - CRC 5,240", "https://res.cloudinary.com/dlpnivtso/image/upload/v1726066975/chococake_ferrcx.png"))),
        Order("Order #12346", listOf(OrderItem("Donuts", "12 items - CRC 15,000", "https://res.cloudinary.com/dlpnivtso/image/upload/v1726066975/donuts_ugz4un.png")))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC)) // Fondo beige como en el resto de la app
            .padding(16.dp)
    ) {
        // Título de la pantalla
        Text(
            text = "Admin Orders",
            fontSize = 24.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Lista de pedidos con LazyColumn para hacer scroll
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            // Iteramos sobre cada pedido
            items(orders.size) { index ->
                val order = orders[index]
                // Mostramos el número de orden
                Text(
                    text = order.number,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Muestra solo un resumen del primer producto del pedido
                if (order.items.isNotEmpty()) {
                    OrderItemRow(order.items.first())
                }

                Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

// Estructura de cada item en el pedido
@Composable
fun OrderItemRow(orderItem: OrderItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Imagen del producto
        Image(
            painter = rememberAsyncImagePainter(orderItem.imageUrl),
            contentDescription = orderItem.name,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Descripción del producto
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = orderItem.name,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = orderItem.description,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

// Botón de opciones usando ActionButton con un ícono
        ActionButton(
            text = "", // Dejar vacío ya que no queremos texto
            onClick = { /* Acciones adicionales para cada producto */ },
            icon = Icons.Filled.MoreVert, // Icono para las opciones
            width = 0.2f, // Ajuste de tamaño
            height = 40, // Tamaño reducido
            buttonColor = Color(0xFFF5F5DC), // Coincidir con el color de fondo
            textColor = Color.Black // Asegúrate de que el texto (o íconos) contrasten
        )

    }
}

// Data class para representar un pedido y sus items
data class Order(
    val number: String,
    val items: List<OrderItem>
)

data class OrderItem(
    val name: String,
    val description: String,
    val imageUrl: String
)
