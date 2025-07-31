package com.example.bite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.auth0.android.jwt.JWT
import com.example.bite.api.ApiRoutes
import com.example.bite.api.RetrofitInstance
import com.example.bite.api.ApiService
import com.example.bite.models.DetalleTransaccion
import com.example.bite.models.Product
import com.example.bite.models.Transaccion
import com.example.bite.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val tokenProvider: () -> String?) : ViewModel() {
    private val _cartProducts = MutableStateFlow<List<Product>>(emptyList())
    val cartProducts: StateFlow<List<Product>> = _cartProducts

    private val _cartTotal = MutableStateFlow(0.0)
    val cartTotal: StateFlow<Double> = _cartTotal

    private var currentTransactionId: Int? = null

    init {
        viewModelScope.launch {
            val userId = getUserId()
            if (userId != null) {
                try {
                    val retrofit = RetrofitInstance.create(ApiRoutes.BASE_URL, tokenProvider)
                    val apiService = retrofit.create(ApiService::class.java)

                    // Asegurarse de que haya un carrito activo
                    val activeCart = apiService.getOrCreateCart(userId)
                    currentTransactionId = activeCart.transaccionId

                    // Cargar los productos en el carrito
                    loadCartProducts()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }



    fun loadCartProducts() {
        viewModelScope.launch {
            try {
                val retrofit = RetrofitInstance.create(ApiRoutes.BASE_URL, tokenProvider)
                val apiService = retrofit.create(ApiService::class.java)

                val userId = getUserId() ?: throw IllegalStateException("No se pudo obtener el ID del usuario")
                println("User ID obtenido: $userId")

                val activeCart = apiService.getOrCreateCart(userId)
                currentTransactionId = activeCart.transaccionId
                println("Transacción activa obtenida: $currentTransactionId")

                // Utilizar el nuevo endpoint para obtener detalles por transacción
                val transactionDetails = apiService.getTransactionDetailsByTransactionId(currentTransactionId!!)
                println("Detalles de la transacción obtenidos: $transactionDetails")

                // Obtener los productos relacionados con los detalles
                val productsInCart = transactionDetails.map { detail ->
                    apiService.getProductById(detail.productoId!!)
                }
                println("Productos en el carrito obtenidos: $productsInCart")

                // Actualizar los estados del carrito
                _cartProducts.value = productsInCart
                _cartTotal.value = transactionDetails.sumOf { it.subtotal }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }



    fun addProductToCart(product: Product, cantidad: Int) {
        viewModelScope.launch {
            try {
                val retrofit = RetrofitInstance.create(ApiRoutes.BASE_URL, tokenProvider)
                val apiService = retrofit.create(ApiService::class.java)

                // Verificar si hay una transacción activa
                if (currentTransactionId == null) {
                    val userId = getUserId() ?: throw IllegalStateException("No se pudo obtener el ID del usuario")
                    val activeCart = apiService.getOrCreateCart(userId)
                    currentTransactionId = activeCart.transaccionId
                }

                // Crear detalle del producto
                val detalle = DetalleTransaccion(
                    detalleId = null,
                    cantidad = cantidad,
                    precioUnitario = product.precio,
                    subtotal = cantidad * product.precio,
                    transaccionId = currentTransactionId!!, // Asegurado que no es nulo
                    productoId = product.productoId
                )
                apiService.addProductToCart(detalle)

                // Recargar el carrito
                loadCartProducts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }




    fun confirmCart(navController: NavHostController) {
        viewModelScope.launch {
            try {
                val retrofit = RetrofitInstance.create(ApiRoutes.BASE_URL, tokenProvider)
                val apiService = retrofit.create(ApiService::class.java)

                currentTransactionId?.let {
                    val confirmedTransaction = apiService.confirmCart(it)
                    println("Transacción confirmada: $confirmedTransaction")

                    // Redirigir al usuario a una pantalla de confirmación
                    navController.navigate(Screen.OrderConfirmation.route)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Mostrar mensaje de error al usuario
                println("Error al confirmar el carrito: ${e.message}")
            }
        }
    }


    private fun getUserId(): Int? {
        return tokenProvider()?.let { JWT(it).getClaim("userId").asInt() }
    }
}
