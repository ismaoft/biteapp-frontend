package com.example.bite.models

import java.sql.Timestamp

data class Ubicacion(
    val ubicacionId: Int,
    val entregaId: Int,
    val latitud: Double,
    val longitud: Double,
    val timestamp: Timestamp
)
