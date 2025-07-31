package com.example.bite.models

data class Transaccion(
    val transaccionId: Int?,
    val fechaTransaccion: String?, // ISO 8601 timestamp
    val estado: String,
    val total: Double,
    val userId: Int?,
    val restaurantId: Int?
)
