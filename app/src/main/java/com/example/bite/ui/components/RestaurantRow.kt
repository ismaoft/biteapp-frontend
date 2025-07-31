package com.example.bite.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import Restaurante

@Composable
fun RestaurantRow(restaurant: Restaurante, onClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(8.dp)
            .clickable { onClick(restaurant.restaurantId) }
    ) {
        Image(
            painter = rememberAsyncImagePainter(restaurant.imagenUrl),
            contentDescription = restaurant.nombre,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
        )
        Text(
            text = restaurant.nombre,
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = "${restaurant.categoria} • ${restaurant.horarioAtencion}",
            color = Color.Black,
            fontSize = 12.sp,
            maxLines = 1,
            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
        )
    }
}

