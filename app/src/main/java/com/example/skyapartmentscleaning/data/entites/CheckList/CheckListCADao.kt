package com.example.skyapartmentscleaning.data.entites.CheckList

import androidx.room.*
import com.example.skycleaning.data.entity.dailyСleaningOfTheApartment.CheckListCleaningApart

/**
 * @author Alexander Volkov (Volkoks)
 */
@Dao
interface CheckListCADao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCheckList(checkListCleaningApart: CheckListCleaningApart)

    @Update
    fun updateCheckList(checkListCleaningApart: CheckListCleaningApart)

    @Delete
    fun deleteCheckList(checkListCleaningApart: CheckListCleaningApart)

    @Query("SELECT * FROM check_list")
    fun getAllCheckLists():List<CheckListCleaningApart>

    @Query("SELECT * FROM check_list WHERE id = :id")
    fun getCAById(id: String): CheckListCleaningApart
}