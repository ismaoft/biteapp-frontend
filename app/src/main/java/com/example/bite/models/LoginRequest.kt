package com.example.bite.models

data class LoginRequest(
    val nombre: String,
    val password: String,
    val tokenFCM: String
)
