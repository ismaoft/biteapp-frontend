package data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val user_id: Int = 0,
    val nombre: String,
    val email: String,
    val password: String,
    val direccion: String?,
    val telefono: String?,
    val preferencias: String?,
    val alergias: String?,
    val fecha_creacion: Long?,
    val ultimo_login: Long?
)
