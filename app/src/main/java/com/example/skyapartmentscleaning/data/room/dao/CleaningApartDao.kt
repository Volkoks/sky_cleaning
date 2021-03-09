package com.example.skyapartmentscleaning.data.room.dao

import androidx.room.*
import com.example.skyapartmentscleaning.data.room.entites.Apart
import com.example.skyapartmentscleaning.data.room.entites.CleaningApart

/**
 * @author Alexander Volkov (Volkoks)
 */
@Dao
interface CleaningApartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCA(cleaningApart: CleaningApart)

    @Update
    fun updateCA(cleaningApart: CleaningApart)

    @Delete
    fun deleteCA(cleaningApart: CleaningApart)

    @Query("SELECT * FROM CleaningApart WHERE apartId = :apartId LIMIT 1")
    fun getApartById(apartId: String): CleaningApart

}