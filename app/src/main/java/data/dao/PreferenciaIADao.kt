package data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import data.entities.PreferenciaIA

@Dao
interface PreferenciaIADao {

    @Query("SELECT * FROM Preferencias_IA")
    fun getAllPreferenciasIA(): LiveData<List<PreferenciaIA>>

    @Query("SELECT * FROM Preferencias_IA WHERE preferencia_id = :preferenciaId LIMIT 1")
    fun getPreferenciaIAById(preferenciaId: Int): LiveData<PreferenciaIA?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(preferenciaIA: PreferenciaIA): Long

    @Update
    suspend fun update(preferenciaIA: PreferenciaIA): Int

    @Delete
    suspend fun delete(preferenciaIA: PreferenciaIA): Int
}
