package data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import data.entities.Transaccion

@Dao
interface TransaccionDao {

    @Query("SELECT * FROM Transacciones")
    fun getAllTransacciones(): LiveData<List<Transaccion>>

    @Query("SELECT * FROM Transacciones WHERE transaccion_id = :transaccionId LIMIT 1")
    fun getTransaccionById(transaccionId: Int): LiveData<Transaccion?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaccion: Transaccion): Long

    @Update
    suspend fun update(transaccion: Transaccion): Int

    @Delete
    suspend fun delete(transaccion: Transaccion): Int
}
