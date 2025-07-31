package com.example.bite.models

import java.sql.Timestamp

data class Entrega(
    val entregaId: Int,
    val transaccionId: Int,
    val repartidorId: Int,
    val estado: String,
    val fechaCreacion: Timestamp,
    val fechaEntregaEstimada: Timestamp
)
