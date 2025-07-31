package data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "role_privilege",
    primaryKeys = ["role_id", "privilege_id"],
    foreignKeys = [
        ForeignKey(entity = Role::class, parentColumns = ["id"], childColumns = ["role_id"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Privilege::class, parentColumns = ["id"], childColumns = ["privilege_id"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [
        Index(value = ["role_id"]),
        Index(value = ["privilege_id"])
    ]
)
data class RolePrivilege(
    val role_id: Long,
    val privilege_id: Long
)
