package com.example.bite.api


import Restaurante
import com.example.bite.models.AppUser
import com.example.bite.models.Categoria
import com.example.bite.models.DetalleTransaccion
import com.example.bite.models.Entrega
import com.example.bite.models.LoginRequest
import com.example.bite.models.LoginResponse
import com.example.bite.models.Product
import com.example.bite.models.TokenRequest
import com.example.bite.models.Transaccion
import com.example.bite.models.Ubicacion
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @POST
    suspend fun login(@Url url: String, @Body request: LoginRequest): LoginResponse

    @GET("/api/v1/categorias")
    suspend fun getCategories(): List<Categoria>

    @GET("/api/v1/restaurantes")
    suspend fun getRestaurants(): List<Restaurante>

    @GET("/api/v1/restaurantes/{id}")
    suspend fun getRestaurantById(@retrofit2.http.Path("id") restaurantId: Int): Restaurante


    @GET("/api/v1/user/profile")
    suspend fun getUserProfile(): AppUser


    @GET("/api/v1/productos")
    suspend fun getProducts(): List<Product>

    @GET("/api/v1/restaurantes/categoria/{categoria}")
    suspend fun getRestaurantsByCategory(@retrofit2.http.Path("categoria") categoria: String): List<Restaurante>

    @PUT("/{id}/token")
    suspend fun actualizarTokenFCM(@Path("id") userId: Int, @Body tokenRequest: TokenRequest)

    @GET("/api/v1/transacciones/carrito")
    suspend fun getOrCreateCart(@retrofit2.http.Query("userId") userId: Int): Transaccion

    @POST("/api/v1/detalles-transaccion")
    suspend fun addProductToCart(@Body detalleTransaccionDTO: DetalleTransaccion): DetalleTransaccion

    @PUT("/api/v1/transacciones/carrito/confirmar/{id}")
    suspend fun confirmCart(@Path("id") transaccionId: Int): Transaccion

    @GET("/api/v1/transacciones/usuario/activa")
    suspend fun getTransactionForUser(): Transaccion


    @GET("/api/v1/detalles-transaccion/{transaccionId}")
    suspend fun getTransactionDetails(@Path("transaccionId") transaccionId: Int): List<DetalleTransaccion>

    @GET("/api/v1/productos/{id}")
    suspend fun getProductById(@Path("id") productId: Int): Product

    @POST("/api/v1/transacciones")
    suspend fun createTransaction(@Body transaccion: Transaccion): Transaccion


    @GET("/api/v1/detalles-transaccion/transaccion/{transaccionId}")
    suspend fun getTransactionDetailsByTransactionId(@Path("transaccionId") transaccionId: Int): List<DetalleTransaccion>


    @GET("/api/v1/entregas/{id}")
    suspend fun getEntregaById(@Path("id") entregaId: Int): Entrega

    @PUT("/api/v1/entregas/{id}/estado")
    suspend fun updateEntregaStatus(@Path("id") entregaId: Int, @Query("estado") nuevoEstado: String)

    @GET("/api/v1/ubicaciones/{entregaId}/recientes")
    suspend fun getUbicacionesRecientes(@Path("entregaId") entregaId: Int): List<Ubicacion>

    @GET("/api/v1/entregas")
    suspend fun getAllEntregas(): List<Entrega>



}

