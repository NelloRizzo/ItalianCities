package it.academy.android.italiancities.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import it.academy.android.italiancities.entities.Province

@Dao
interface DatabaseProvincesDao : ProvincesDao {
    @Query("SELECT * FROM provinces")
    override fun getAll(): List<Province>

    @Insert
    override fun save(province: Province)
}