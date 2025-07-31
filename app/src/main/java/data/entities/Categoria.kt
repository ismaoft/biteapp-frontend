package data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categorias")
data class Categoria(
    @PrimaryKey(autoGenerate = true) val categoria_id: Int = 0,
    val nombre: String
)
