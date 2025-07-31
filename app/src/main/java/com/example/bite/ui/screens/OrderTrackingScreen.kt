package com.example.bite.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bite.viewmodel.EntregaViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun OrderTrackingScreen(
    navController: NavHostController,
    entregaViewModel: EntregaViewModel,
    entregaId: Int
) {
    // Observa las ubicaciones y errores
    val ubicaciones by entregaViewModel.ubicaciones.collectAsState()
    val error by entregaViewModel.error.collectAsState()

    // Llama a fetchUbicacionesRecientes cuando se carga la pantalla
    LaunchedEffect(entregaId) {
        entregaViewModel.fetchUbicacionesRecientes(entregaId)
    }

    // Define una ubicación predeterminada si no hay ubicaciones recientes
    val defaultLocation = LatLng(9.9986, -84.1165) // Ejemplo: UNA Heredia

    // Configura la posición inicial de la cámara
    val cameraPositionState = rememberCameraPositionState {
        position = ubicaciones.firstOrNull()?.let {
            CameraPosition.fromLatLngZoom(LatLng(it.latitud, it.longitud), 15f)
        } ?: CameraPosition.fromLatLngZoom(defaultLocation, 15f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // Marcadores para las ubicaciones obtenidas
        ubicaciones.forEach { ubicacion ->
            Marker(
                state = MarkerState(position = LatLng(ubicacion.latitud, ubicacion.longitud)),
                title = "Location Update",
                snippet = "Time: ${ubicacion.timestamp}"
            )
        }

        // Si no hay ubicaciones, muestra un marcador en la ubicación predeterminada
        if (ubicaciones.isEmpty()) {
            Marker(
                state = MarkerState(position = defaultLocation),
                title = "Default Location",
                snippet = "Waiting for updates..."
            )
        }
    }

    // Mostrar errores, si los hay
    error?.let {
        Text(
            text = it,
            color = Color.Red,
            modifier = Modifier.padding(16.dp)
        )
    }
}


