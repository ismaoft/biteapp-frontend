package com.example.bite.viewmodel

import Restaurante
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bite.api.ApiService
import com.example.bite.api.RetrofitInstance
import com.example.bite.api.ApiRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RestaurantViewModel(
    private val tokenProvider: () -> String? // Añadimos tokenProvider como parámetro
) : ViewModel() {

    private val _restaurant = MutableStateFlow<Restaurante?>(null)
    val restaurant: StateFlow<Restaurante?> = _restaurant

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun fetchRestaurant(restaurantId: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                // Crear instancia de Retrofit con el tokenProvider
                val retrofit = RetrofitInstance.create(ApiRoutes.BASE_URL, tokenProvider)
                val apiService = retrofit.create(ApiService::class.java)

                val response = apiService.getRestaurantById(restaurantId)
                _restaurant.value = response
            } catch (e: Exception) {
                // Manejo de errores
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }
}


//val restaurantViewModel = RestaurantViewModel { authViewModel.token }