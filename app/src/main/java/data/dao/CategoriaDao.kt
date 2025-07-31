package data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import data.entities.Categoria

@Dao
interface CategoriaDao {

    @Query("SELECT * FROM Categorias")
    fun getAllCategorias(): LiveData<List<Categoria>>

    @Query("SELECT * FROM Categorias WHERE categoria_id = :categoriaId LIMIT 1")
    fun getCategoriaById(categoriaId: Int): LiveData<Categoria?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoria: Categoria): Long

    @Update
    suspend fun update(categoria: Categoria): Int

    @Delete
    suspend fun delete(categoria: Categoria): Int
}
