package com.example.bite.ui.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bite.ui.components.ActionButton
import com.example.bite.ui.components.InputField
import com.example.bite.viewmodel.AuthViewModel
import com.example.bite.ui.components.AllergiesImageGroup

@Composable
fun RegisterScreen3(
    navController: NavController,
    authViewModel: AuthViewModel,  // Pasa el AuthViewModel como en RegisterScreen2
    modifier: Modifier = Modifier
) {
    val selectedItems = remember { mutableStateOf(listOf<String>()) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF6F7DF))
    ) {
        // Fondo diagonal
        Box(
            modifier = Modifier
                .offset(x = (-85).dp, y = (-40).dp)
                .size(width = 434.31.dp, height = 226.93.dp)
                .background(Color(0xFF41B9B2))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 43.dp)
                .align(Alignment.Center)
        ) {
            // Título
            Text(
                text = "What food allergies do you manage?",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Reutiliza el nuevo componente para las imágenes de alergias
            val allergens = listOf(
                "https://res.cloudinary.com/dlpnivtso/image/upload/v1724875905/gluten_allergen_p8wvrl.png",
                "https://res.cloudinary.com/dlpnivtso/image/upload/v1724875905/fish_allergen_plrv6k.png",
                "https://res.cloudinary.com/dlpnivtso/image/upload/v1724875905/egg_allergen_be0clr.png",
                "https://res.cloudinary.com/dlpnivtso/image/upload/v1724875906/crustaceans_allergen_zfl4wl.png",
                "https://res.cloudinary.com/dlpnivtso/image/upload/v1724875905/mustard_allergen_vq40zz.png",
                "https://res.cloudinary.com/dlpnivtso/image/upload/v1724875906/peanut_allergen_xzbvnb.png",
                "https://res.cloudinary.com/dlpnivtso/image/upload/v1724875906/nuts_allergen_mxac2k.png",
                "https://res.cloudinary.com/dlpnivtso/image/upload/v1724875908/sulfites_allergen_blpsyq.png",
                "https://res.cloudinary.com/dlpnivtso/image/upload/v1724875908/shellfish_allergen_w8vwap.png",
                "https://res.cloudinary.com/dlpnivtso/image/upload/v1724875908/sesame_allergen_umrebd.png",
                "https://res.cloudinary.com/dlpnivtso/image/upload/v1724875905/Group_2_qcy6eo.png",
                "https://res.cloudinary.com/dlpnivtso/image/upload/v1724875905/celery_allergen_yjqaok.png"
            )

            AllergiesImageGroup(
                allergens = allergens,
                selectedItems = selectedItems
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Usamos el componente InputField para el campo de texto "Other"
            InputField(
                label = "Other",
                value = "",
                onValueChange = { /* Aquí manejas el valor del texto */ }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Usamos el componente ActionButton para el botón de "Finish"
            ActionButton(
                text = "Finish",
                onClick = {
                    // Navega a la siguiente pantalla
                    navController.navigate("loading") {
                        popUpTo("register3") { inclusive = true }
                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .width(151.dp)
                    .height(43.dp)
            )
        }
    }
}
