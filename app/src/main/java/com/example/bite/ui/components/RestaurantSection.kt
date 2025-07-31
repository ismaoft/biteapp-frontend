package com.example.bite.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import Restaurante

@Composable
fun RestaurantSection(restaurants: List<Restaurante>) {
    Column {
        restaurants.forEach { restaurant ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(restaurant.imagenUrl),
                    contentDescription = restaurant.nombre,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = restaurant.nombre)
            }
        }
    }
}