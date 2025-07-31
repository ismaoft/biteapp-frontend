package data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Detalles_Transaccion",
    foreignKeys = [
        ForeignKey(entity = Transaccion::class, parentColumns = ["transaccion_id"], childColumns = ["transaccion_id"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Producto::class, parentColumns = ["producto_id"], childColumns = ["producto_id"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [
        Index(value = ["transaccion_id"]),
        Index(value = ["producto_id"])
    ]
)
data class DetalleTransaccion(
    @PrimaryKey(autoGenerate = true) val detalle_id: Int = 0,
    val transaccion_id: Int,
    val producto_id: Int,
    val cantidad: Int,
    val precio_unitario: Double,
    val subtotal: Double
)
