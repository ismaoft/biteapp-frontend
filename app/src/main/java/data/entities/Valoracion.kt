package data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Valoraciones",
    foreignKeys = [
        ForeignKey(entity = Usuario::class, parentColumns = ["user_id"], childColumns = ["user_id"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Producto::class, parentColumns = ["producto_id"], childColumns = ["producto_id"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [
        Index(value = ["user_id"]),
        Index(value = ["producto_id"])
    ]
)
data class Valoracion(
    @PrimaryKey(autoGenerate = true) val valoracion_id: Int = 0,
    val user_id: Int,
    val producto_id: Int,
    val calificacion: Int,
    val comentario: String?,
    val fecha_reseña: Long?
)
