package com.example.skyapartmentscleaning.data.entites.dailyСleaningOfTheApartment

import androidx.room.*
import com.example.skycleaning.data.entity.dailyСleaningOfTheApartment.CleaningApart
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
}