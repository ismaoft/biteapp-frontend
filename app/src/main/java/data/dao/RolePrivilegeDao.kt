package data.dao

import androidx.room.*
import data.entities.RolePrivilege

@Dao
interface RolePrivilegeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rolePrivilege: RolePrivilege): Long

    @Query("DELETE FROM role_privilege WHERE role_id = :roleId AND privilege_id = :privilegeId")
    suspend fun delete(roleId: Long, privilegeId: Long): Int
}
