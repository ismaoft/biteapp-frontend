// CategoryRow para mostrar categorías
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.bite.models.Categoria

@Composable
fun CategoryRow(category: Categoria, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .padding(4.dp)
            .clickable { onClick(category.nombre) }
    ) {
        Image(
            painter = rememberAsyncImagePainter(category.imagenUrl),
            contentDescription = category.nombre,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = category.nombre,
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 4.dp),
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )

    }
}
