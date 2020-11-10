package com.example.skyapartmentscleaning

import android.app.Application
import com.example.skyapartmentscleaning.data.entites.apart.ApartDatabase

/**
 * @author Alexander Volkov (Volkoks)
 */
class MyApp : Application() {

    companion object{
    lateinit var apartDB: ApartDatabase
    }

    override fun onCreate() {
        super.onCreate()
        apartDB = ApartDatabase.getInstance(this)
    }

}