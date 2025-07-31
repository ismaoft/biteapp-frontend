package data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import data.entities.Usuario

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM Usuarios")
    fun getAllUsuarios(): LiveData<List<Usuario>>

    @Query("SELECT * FROM Usuarios WHERE user_id = :userId LIMIT 1")
    fun getUsuarioById(userId: Int): LiveData<Usuario?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: Usuario): Long

    @Update
    suspend fun update(usuario: Usuario): Int

    @Delete
    suspend fun delete(usuario: Usuario): Int
}
