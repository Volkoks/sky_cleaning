package com.example.skyapartmentscleaning.ui.activity

import androidx.lifecycle.ViewModel
import com.example.skyapartmentscleaning.MyApp
import com.example.skyapartmentscleaning.navigator.Screens
import ru.terrakok.cicerone.Router

/**
 * @author Alexander Volkov (Volkoks)
 *
 * Данный класс ViewModel для MainActivity
 */
class MainActivityViewModel:ViewModel() {

private val router:Router = MyApp.instance.getRouter

    /**
     * Метод включения фрагмента MainFragment
     */
    fun startMainFragment(){
        router.replaceScreen(Screens.MainScreen())
    }

}