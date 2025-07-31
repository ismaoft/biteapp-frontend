package com.example.bite.models

data class DetalleTransaccion(
    val detalleId: Int?,
    val cantidad: Int,
    val precioUnitario: Double,
    val subtotal: Double,
    val transaccionId: Int?,
    val productoId: Int?
)
