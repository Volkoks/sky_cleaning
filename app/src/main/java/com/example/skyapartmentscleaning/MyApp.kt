package com.example.skyapartmentscleaning

import android.app.Application
import com.example.skyapartmentscleaning.data.entites.apart.ApartDatabase
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

/**
 * @author Alexander Volkov (Volkoks)
 */
class MyApp : Application() {

    companion object {
        lateinit var instance: MyApp
    }

    private val apartDB: ApartDatabase by lazy {
        ApartDatabase.getInstance(this)
    }
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    val getDB
        get() = apartDB
    val getRouter
        get() = cicerone.router
    val getNavigatorHolder
        get() = cicerone.navigatorHolder

}