package data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import data.entities.Valoracion

@Dao
interface ValoracionDao {

    @Query("SELECT * FROM Valoraciones")
    fun getAllValoraciones(): LiveData<List<Valoracion>>

    @Query("SELECT * FROM Valoraciones WHERE valoracion_id = :valoracionId LIMIT 1")
    fun getValoracionById(valoracionId: Int): LiveData<Valoracion?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(valoracion: Valoracion): Long

    @Update
    suspend fun update(valoracion: Valoracion): Int

    @Delete
    suspend fun delete(valoracion: Valoracion): Int
}
