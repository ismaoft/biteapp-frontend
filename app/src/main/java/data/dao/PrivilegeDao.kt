package data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import data.entities.Privilege

@Dao
interface PrivilegeDao {

    @Query("SELECT * FROM privilege")
    fun getAllPrivileges(): LiveData<List<Privilege>>

    @Query("SELECT * FROM privilege WHERE id = :privilegeId LIMIT 1")
    fun getPrivilegeById(privilegeId: Long): LiveData<Privilege?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(privilege: Privilege): Long

    @Update
    suspend fun update(privilege: Privilege): Int

    @Delete
    suspend fun delete(privilege: Privilege): Int
}
