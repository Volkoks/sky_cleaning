package com.example.skycleaning

import android.app.Application
import com.example.skyapartmentscleaning.data.entites.apart.ApartDatabase


class MyApp : Application() {

    companion object{
    lateinit var apartDB: ApartDatabase
    }

    override fun onCreate() {
        super.onCreate()
        apartDB = ApartDatabase.getInstance(this)
    }

}