package com.example.skyapartmentscleaning.data.room.dao

import androidx.room.*
import com.example.skyapartmentscleaning.data.room.entites.Apart

/**
 * @author Alexander Volkov (Volkoks)
 */
@Dao
interface ApartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addApart(apart: Apart)

    @Delete
    fun deleteApart(apart: Apart)

    @Query("SELECT * FROM apart")
    fun getAllApart(): List<Apart>

    @Query("SELECT COUNT() FROM apart")
    fun getCountApart(): Long

    @Query("SELECT * FROM apart WHERE id = :id LIMIT 1")
    fun getApartById(id: String): Apart

}