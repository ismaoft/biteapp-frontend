package com.example.bite.api

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.auth0.android.jwt.JWT
import com.example.bite.MainActivity
import com.example.bite.R
import com.example.bite.models.TokenRequest
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.example.bite.viewmodel.AuthViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "Nuevo Token FCM: $token")

        val sharedPreferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE)
        val jwt = sharedPreferences.getString("jwt_token", null)

        if (jwt != null) {
            val userId = JWT(jwt).getClaim("userId").asInt()
            if (userId != null) {
                GlobalScope.launch {
                    try {
                        val retrofit = RetrofitInstance.create(ApiRoutes.BASE_URL) { jwt }
                        val apiService = retrofit.create(ApiService::class.java)

                        val tokenRequest = TokenRequest(token)
                        apiService.actualizarTokenFCM(userId, tokenRequest)
                        Log.d("FCM", "Token FCM actualizado en el backend.")
                    } catch (e: Exception) {
                        Log.e("FCM", "Error al actualizar el token FCM: ${e.localizedMessage}")
                    }
                }
            } else {
                Log.d("FCM", "No se pudo extraer el userId del token JWT.")
            }
        } else {
            Log.d("FCM", "El usuario no está autenticado.")
        }
    }




    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Mostrar notificación
        val title = remoteMessage.data["title"] ?: "Sin título"
        val message = remoteMessage.data["message"] ?: "Sin mensaje"
        sendNotification(title, message)
    }

    private fun sendNotification(title: String, message: String) {
        val channelId = "default_channel"
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Default Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )


        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        notificationManager.notify(0, notificationBuilder.build())
    }
}
