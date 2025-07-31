package com.example.bite.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.bite.models.Product
import kotlinx.coroutines.launch

@Composable
fun ProductRow(
    product: Product,
    onAddToCartClick: () -> Unit // Callback para manejar la acción de añadir al carrito
) {
    // Recordamos un estado de snackbar para mostrar mensajes
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxWidth()) {
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.White)
                .padding(8.dp)
        ) {
            // Imagen del producto
            Image(
                painter = rememberAsyncImagePainter(product.imagenUrl),
                contentDescription = product.nombre,
                modifier = Modifier
                    .size(80.dp) // Tamaño de la imagen
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Detalles del producto
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = product.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp, // Tamaño del texto del nombre del producto
                    maxLines = 1,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.descripcion,
                    fontSize = 14.sp, // Tamaño del texto de la descripción del producto
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                // Precio del producto
                Text(
                    text = "CRC ${product.precio}",
                    fontSize = 15.sp, // Tamaño del texto del precio
                    color = Color(0xFF4CAF50) // Verde para el precio
                )
            }

            // Botón para añadir al carrito
            IconButton(
                onClick = {
                    onAddToCartClick() // Llamar al callback
                    // Mostrar un mensaje en el snackbar
                    scope.launch {
                        snackbarHostState.showSnackbar("Producto añadido al carrito")
                    }
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add to Cart",
                    tint = Color(0xFF4CAF50), // Verde para el icono de añadir
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
