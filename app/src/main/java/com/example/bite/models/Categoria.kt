package com.example.bite.models

data class Categoria(
    val categoria_id: Int,
    val nombre: String,
    val imagenUrl: String?  // El campo de la imagen puede ser opcional
)