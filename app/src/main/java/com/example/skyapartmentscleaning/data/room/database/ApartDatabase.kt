package com.example.skyapartmentscleaning.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.skyapartmentscleaning.data.room.entites.Apart
import com.example.skyapartmentscleaning.data.room.dao.ApartDao
import com.example.skyapartmentscleaning.data.room.dao.CleaningApartDao
import com.example.skyapartmentscleaning.data.room.entites.CleaningApart

/**
 * @author Alexander Volkov (Volkoks)
 */
@Database(entities = arrayOf(Apart::class, CleaningApart::class), version = 1)
abstract class ApartDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "Apart.db"
    }

    abstract fun getApartDao(): ApartDao
    abstract fun getCleaningApartDao(): CleaningApartDao

}