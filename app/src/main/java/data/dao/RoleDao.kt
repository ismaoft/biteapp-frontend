package data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import data.entities.Role

@Dao
interface RoleDao {

    @Query("SELECT * FROM role")
    fun getAllRoles(): LiveData<List<Role>>

    @Query("SELECT * FROM role WHERE id = :roleId LIMIT 1")
    fun getRoleById(roleId: Long): LiveData<Role?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(role: Role): Long

    @Update
    suspend fun update(role: Role): Int

    @Delete
    suspend fun delete(role: Role): Int
}
