package data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "user_role",
    primaryKeys = ["user_id", "role_id"],
    foreignKeys = [
        ForeignKey(entity = Usuario::class, parentColumns = ["user_id"], childColumns = ["user_id"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Role::class, parentColumns = ["id"], childColumns = ["role_id"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [
        Index(value = ["user_id"]),
        Index(value = ["role_id"])
    ]
)
data class UserRole(
    val user_id: Int,
    val role_id: Long
)

