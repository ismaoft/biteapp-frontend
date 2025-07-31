package com.example.bite.api

object ApiRoutes {
    const val BASE_URL = "http://10.0.2.2:8080"

    const val USERS = "$BASE_URL/api/usuarios"
    const val PRODUCTS = "$BASE_URL/api/productos"
    const val TRANSACTIONS = "$BASE_URL/api/transacciones"
    const val TRANSACTION_DETAILS = "$BASE_URL/api/detalles_transaccion"

    // Ruta para el login
    const val LOGIN = "$BASE_URL/api/v1/login"
}
