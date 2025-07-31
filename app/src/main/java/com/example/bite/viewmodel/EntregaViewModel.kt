package com.example.bite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bite.api.ApiRoutes
import com.example.bite.api.ApiService
import com.example.bite.api.RetrofitInstance
import com.example.bite.models.Entrega
import com.example.bite.models.Ubicacion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EntregaViewModel(private val tokenProvider: () -> String?) : ViewModel() {

    private val _entrega = MutableStateFlow<Entrega?>(null)
    val entrega: StateFlow<Entrega?> = _entrega

    private val _ubicaciones = MutableStateFlow<List<Ubicacion>>(emptyList())
    val ubicaciones: StateFlow<List<Ubicacion>> = _ubicaciones

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchEntregaDetails(entregaId: Int) {
        viewModelScope.launch {
            try {
                val retrofit = RetrofitInstance.create(ApiRoutes.BASE_URL) { tokenProvider() }
                val apiService = retrofit.create(ApiService::class.java)
                val entregaDetails = apiService.getEntregaById(entregaId)
                _entrega.value = entregaDetails // Asignar el único objeto
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }


    fun updateEntregaStatus(entregaId: Int, nuevoEstado: String) {
        viewModelScope.launch {
            try {
                val retrofit = RetrofitInstance.create(ApiRoutes.BASE_URL) { tokenProvider() }
                val apiService = retrofit.create(ApiService::class.java)
                apiService.updateEntregaStatus(entregaId, nuevoEstado)
                fetchEntregaDetails(entregaId) // Refrescar datos
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun fetchUbicacionesRecientes(entregaId: Int) {
        viewModelScope.launch {
            try {
                val retrofit = RetrofitInstance.create(ApiRoutes.BASE_URL) { tokenProvider() }
                val apiService = retrofit.create(ApiService::class.java)
                val ubicacionesList = apiService.getUbicacionesRecientes(entregaId)
                _ubicaciones.value = ubicacionesList
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun fetchEntregas() {
        viewModelScope.launch {
            println("Fetching entregas...")
            try {
                val retrofit = RetrofitInstance.create(ApiRoutes.BASE_URL) { tokenProvider() }
                val apiService = retrofit.create(ApiService::class.java)
                val entregasList = apiService.getAllEntregas()
                _entrega.value = entregasList.firstOrNull() // Asignar solo el primer objeto de la lista
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }



}
