package com.example.skyapartmentscleaning.data.entites.apart

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Apart::class), version = 1)
abstract class ApartDatabase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): ApartDatabase {
            var db = Room.databaseBuilder(context.applicationContext,
                ApartDatabase::class.java, "Apart.db").build()
            return db
        }
    }

    abstract fun getApartDao(): ApartDao

}