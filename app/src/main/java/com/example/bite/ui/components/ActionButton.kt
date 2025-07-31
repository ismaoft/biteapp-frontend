package com.example.bite.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun ActionButton(
    text: String? = null, // Hacemos que el texto sea opcional
    icon: ImageVector? = null, // Agregamos soporte para íconos opcionales
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColor: Color = Color(0xFF41B9B2), // Color del botón
    textColor: Color = Color.White, // Color del texto
    iconColor: Color = Color.White, // Color del ícono
    height: Int = 50, // Altura del botón
    fontSize: Int = 18, // Tamaño de fuente del texto
    width: Float = 1f, // Ancho del botón
    roundedCorners: Boolean = false // Parámetro para definir si el botón es redondeado
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = textColor
        ),
        modifier = modifier
            .fillMaxWidth(width) // Ancho personalizado
            .height(height.dp) // Altura personalizada
            .clip(if (roundedCorners) RoundedCornerShape(50) else RoundedCornerShape(8.dp)) // Forma redondeada opcional
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
        }
        if (text != null) {
            Text(
                text = text,
                fontSize = fontSize.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(start = if (icon != null) 8.dp else 0.dp)
            )
        }
    }
}
