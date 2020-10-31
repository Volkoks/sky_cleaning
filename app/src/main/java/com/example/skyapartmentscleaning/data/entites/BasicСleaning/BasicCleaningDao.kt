package com.example.skycleaning.data.entity.BasicСleaning

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface BasicCleaningDao {
    @Insert
    fun addBasicCleaning(basicCleaning: BasicCleaning)

    @Update
    fun updateBasicCleaning(basicCleaning: BasicCleaning)

    @Delete
    fun deleteBasicCleaning(basicCleaning: BasicCleaning)
}