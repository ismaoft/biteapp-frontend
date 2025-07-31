package data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Productos",
    foreignKeys = [
        ForeignKey(entity = Categoria::class, parentColumns = ["categoria_id"], childColumns = ["categoria_id"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Restaurante::class, parentColumns = ["restaurant_id"], childColumns = ["restaurant_id"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [
        Index(value = ["categoria_id"]),
        Index(value = ["restaurant_id"])
    ]
)
data class Producto(
    @PrimaryKey(autoGenerate = true) val producto_id: Int = 0,
    val nombre: String,
    val descripcion: String?,
    val precio: Double,
    val categoria_id: Int,
    val restaurant_id: Int,
    val ingredientes: String?,
    val disponibilidad: Boolean
)

