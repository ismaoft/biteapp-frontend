package com.example.bite.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bite.api.ApiRoutes
import com.example.bite.api.RetrofitInstance
import com.example.bite.api.ApiService
import com.example.bite.models.AppUser
import com.example.bite.models.LoginRequest
import com.example.bite.models.LoginResponse
import com.example.bite.models.TokenRequest
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import com.auth0.android.jwt.JWT

class AuthViewModel(private val context: Context) : ViewModel() {

    private val _currentUser = MutableStateFlow<AppUser?>(null)
    val currentUser: StateFlow<AppUser?> = _currentUser

    private val _loginError = MutableStateFlow<String?>(null)
    val loginError: StateFlow<String?> = _loginError

    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> = _token

    fun getToken(): String? = token.value

    private fun saveTokenToPreferences(jwt: String) {
        val sharedPreferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("jwt_token", jwt).apply()
    }

    fun getTokenFromPreferences(): String? {
        val sharedPreferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("jwt_token", null)
    }

    fun getUserIdFromToken(): Int? {
        val jwt = _token.value?.let { JWT(it) } ?: return null
        return jwt.getClaim("userId").asInt()
    }

    private fun getFirebaseToken(onTokenReceived: (String) -> Unit) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                onTokenReceived(token)
            } else {
                println("Error al obtener el token FCM: ${task.exception?.localizedMessage}")
            }
        }
    }

    fun loginWithFirebase(nombre: String, password: String) {
        getFirebaseToken { tokenFCM ->
            Log.d("Auth", "Token FCM obtenido: $tokenFCM")
            login(nombre, password, tokenFCM)
        }
    }

    fun login(nombre: String, password: String, tokenFCM: String) {
        viewModelScope.launch {
            try {
                val retrofit = RetrofitInstance.create(ApiRoutes.BASE_URL) { _token.value }
                val apiService = retrofit.create(ApiService::class.java)

                val response: LoginResponse = apiService.login(ApiRoutes.LOGIN, LoginRequest(nombre, password, tokenFCM))

                if (response.token != null) {
                    _token.value = response.token
                    saveTokenToPreferences(response.token)
                    fetchUserProfile()
                    _loginError.update { null }
                } else {
                    _loginError.update { "Credenciales incorrectas" }
                }
            } catch (e: HttpException) {
                _loginError.update { "Error de conexión o servidor" }
            } catch (e: Exception) {
                _loginError.update { "Error de conexión o servidor" }
            }
        }
    }

    private fun fetchUserProfile() {
        viewModelScope.launch {
            try {
                val retrofit = RetrofitInstance.create(ApiRoutes.BASE_URL) { _token.value }
                val apiService = retrofit.create(ApiService::class.java)

                val userProfile = apiService.getUserProfile()
                _currentUser.update { userProfile }

            } catch (e: Exception) {
                println("Error al obtener el perfil del usuario: ${e.localizedMessage}")
            }
        }
    }

    fun logout() {
        // Limpiar el usuario actual y el token en memoria
        _currentUser.update { null }
        _token.update { null }

        // Limpiar el token persistido en SharedPreferences
        val sharedPreferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().remove("jwt_token").apply()

        Log.d("Auth", "Sesión cerrada y token eliminado.")
    }

}
