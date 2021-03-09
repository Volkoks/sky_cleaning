package com.example.skyapartmentscleaning.di.modules

import android.content.Context
import androidx.room.Room
import com.example.skyapartmentscleaning.data.room.database.ApartDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideApartDatabase(context: Context): ApartDatabase {
        return Room.databaseBuilder(
            context,
            ApartDatabase::class.java,
            ApartDatabase.DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }
}