package com.example.bite.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter


@Composable
fun LoadingScreenContent(
    logoUrl: String,
    loadingText: String = "Loading..."
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Espaciador superior dinámico para balancear el contenido
        Spacer(modifier = Modifier.weight(1f))  // Toma el 50% del espacio vertical disponible antes del contenido

        // Contenido principal centrado
        Image(
            painter = rememberAsyncImagePainter(logoUrl),
            contentDescription = "Loading Logo",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))  // Espacio entre logo e indicador de carga

        CircularProgressIndicator(color = Color(0xFF41B9B2))

        Spacer(modifier = Modifier.height(16.dp))  // Espacio entre indicador y texto

        Text(
            text = loadingText,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF41B9B2)
        )

        // Espaciador inferior dinámico para balancear el contenido
        Spacer(modifier = Modifier.weight(1f))  // Toma el 50% del espacio vertical disponible después del contenido
    }
}
