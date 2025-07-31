package data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import data.entities.Producto

@Dao
interface ProductoDao {

    @Query("SELECT * FROM Productos")
    fun getAllProductos(): LiveData<List<Producto>>

    @Query("SELECT * FROM Productos WHERE producto_id = :productoId LIMIT 1")
    fun getProductoById(productoId: Int): LiveData<Producto?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(producto: Producto): Long

    @Update
    suspend fun update(producto: Producto): Int

    @Delete
    suspend fun delete(producto: Producto): Int
}
