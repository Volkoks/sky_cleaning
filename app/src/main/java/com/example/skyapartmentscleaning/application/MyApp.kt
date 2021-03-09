package com.example.skyapartmentscleaning.application

import android.app.Application
import com.example.skyapartmentscleaning.data.room.database.ApartDatabase
import com.example.skyapartmentscleaning.di.AppComponent
import com.example.skyapartmentscleaning.di.DaggerAppComponent
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

/**
 * @author Alexander Volkov (Volkoks)
 */
class MyApp : Application() {

    companion object {
        lateinit var instance: MyApp
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().app(this).build()
    }



}