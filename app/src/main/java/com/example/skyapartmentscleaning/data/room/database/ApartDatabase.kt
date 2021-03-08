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
        fun getInstance(context: Context): ApartDatabase {
            var db = Room.databaseBuilder(
                context.applicationContext,
                ApartDatabase::class.java, "Apart.db"
            ).fallbackToDestructiveMigration()
                .build()
            return db
        }
    }

    abstract fun getApartDao(): ApartDao
    abstract fun getCleaningApartDao(): CleaningApartDao

}