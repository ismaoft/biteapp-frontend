package data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Restaurantes")
data class Restaurante(
    @PrimaryKey(autoGenerate = true) val restaurant_id: Int = 0,
    val nombre: String,
    val direccion: String?,
    val telefono: String?,
    val categoria: String?,
    val horario_atencion: String?,
    val descripcion: String?
)
