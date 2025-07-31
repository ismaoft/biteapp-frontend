package data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import data.entities.DetalleTransaccion

@Dao
interface DetalleTransaccionDao {

    @Query("SELECT * FROM Detalles_Transaccion")
    fun getAllDetallesTransaccion(): LiveData<List<DetalleTransaccion>>

    @Query("SELECT * FROM Detalles_Transaccion WHERE detalle_id = :detalleId LIMIT 1")
    fun getDetalleTransaccionById(detalleId: Int): LiveData<DetalleTransaccion?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(detalleTransaccion: DetalleTransaccion): Long

    @Update
    suspend fun update(detalleTransaccion: DetalleTransaccion): Int

    @Delete
    suspend fun delete(detalleTransaccion: DetalleTransaccion): Int
}
