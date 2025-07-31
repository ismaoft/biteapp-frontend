package data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Transacciones",
    foreignKeys = [
        ForeignKey(entity = Usuario::class, parentColumns = ["user_id"], childColumns = ["user_id"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Restaurante::class, parentColumns = ["restaurant_id"], childColumns = ["restaurant_id"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [
        Index(value = ["user_id"]),
        Index(value = ["restaurant_id"])
    ]
)
data class Transaccion(
    @PrimaryKey(autoGenerate = true) val transaccion_id: Int = 0,
    val user_id: Int,
    val restaurant_id: Int,
    val fecha_transaccion: Long?,
    val estado: String,
    val total: Double
)
