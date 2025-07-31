package data.dao

import androidx.room.*
import data.entities.UserRole

@Dao
interface UserRoleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userRole: UserRole): Long

    @Query("DELETE FROM user_role WHERE user_id = :userId AND role_id = :roleId")
    suspend fun delete(userId: Int, roleId: Long): Int
}
