package com.example.bite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bite.api.ApiService
import com.example.bite.api.RetrofitInstance
import com.example.bite.api.ApiRoutes
import com.example.bite.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AdminDashboardViewModel(
    private val tokenProvider: () -> String? // Añadimos tokenProvider como parámetro
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                // Crear instancia de Retrofit con el tokenProvider
                val retrofit = RetrofitInstance.create(ApiRoutes.BASE_URL, tokenProvider)
                val apiService = retrofit.create(ApiService::class.java)

                val productResponse = apiService.getProducts()
                _products.value = productResponse
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}


//val adminDashboardViewModel = AdminDashboardViewModel { authViewModel.token }