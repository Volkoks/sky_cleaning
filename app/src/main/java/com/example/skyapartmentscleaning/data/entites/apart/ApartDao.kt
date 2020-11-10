package com.example.skyapartmentscleaning.data.entites.apart

import androidx.room.*
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

}