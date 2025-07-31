package data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Preferencias_IA",
    foreignKeys = [
        ForeignKey(entity = Usuario::class, parentColumns = ["user_id"], childColumns = ["user_id"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [
        Index(value = ["user_id"])
    ]
)
data class PreferenciaIA(
    @PrimaryKey(autoGenerate = true) val preferencia_id: Int = 0,
    val user_id: Int,
    val historial_busqueda: String?,
    val gustos_identificados: String?,
    val alergias_identificadas: String?
)

