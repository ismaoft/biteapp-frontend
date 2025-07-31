package com.example.bite.models

data class Product(
    val productoId: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val ingredientes: String?,
    val disponibilidad: Boolean,
    val categoriaId: Int?,
    val restaurantId: Int?,
    val imagenUrl: String?
)

