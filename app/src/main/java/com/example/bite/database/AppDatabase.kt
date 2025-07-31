package com.example.bite.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import data.dao.CategoriaDao
import data.dao.DetalleTransaccionDao
import data.dao.PreferenciaIADao
import data.dao.PrivilegeDao
import data.dao.ProductoDao
import data.dao.RestauranteDao
import data.dao.RoleDao
import data.dao.RolePrivilegeDao
import data.dao.TransaccionDao
import data.dao.UserRoleDao
import data.dao.ValoracionDao
import data.dao.*
import data.entities.*

@Database(
    entities = [
        Usuario::class,
        Restaurante::class,
        Categoria::class,
        Producto::class,
        Transaccion::class,
        DetalleTransaccion::class,
        Valoracion::class,
        PreferenciaIA::class,
        Role::class,
        Privilege::class,
        UserRole::class,
        RolePrivilege::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun restauranteDao(): RestauranteDao
    abstract fun categoriaDao(): CategoriaDao
    abstract fun productoDao(): ProductoDao
    abstract fun transaccionDao(): TransaccionDao
    abstract fun detalleTransaccionDao(): DetalleTransaccionDao
    abstract fun valoracionDao(): ValoracionDao
    abstract fun preferenciaIADao(): PreferenciaIADao
    abstract fun roleDao(): RoleDao
    abstract fun privilegeDao(): PrivilegeDao
    abstract fun userRoleDao(): UserRoleDao
    abstract fun rolePrivilegeDao(): RolePrivilegeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
