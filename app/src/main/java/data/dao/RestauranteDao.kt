package data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import data.entities.Restaurante

@Dao
interface RestauranteDao {

    @Query("SELECT * FROM Restaurantes")
    fun getAllRestaurantes(): LiveData<List<Restaurante>>

    @Query("SELECT * FROM Restaurantes WHERE restaurant_id = :restaurantId LIMIT 1")
    fun getRestauranteById(restaurantId: Int): LiveData<Restaurante?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurante: Restaurante): Long

    @Update
    suspend fun update(restaurante: Restaurante): Int

    @Delete
    suspend fun delete(restaurante: Restaurante): Int
}
