// models/AppUser.kt
package com.example.bite.models

data class AppUser(
    val user_id: Int,
    val nombre: String,
    val email: String,
    val password: String,
    val direccion: String?,
    val telefono: String?,
    val preferencias: String?,
    val alergias: String?,
    val fecha_creacion: String?,
    val ultimo_login: String?,
    val imagen_url: String?,
    val Roles: List<Role>
)

data class Role(
    val id: Int,
    val name: String
)
