package com.example.skyapartmentscleaning.ui.activity

import androidx.lifecycle.ViewModel
import com.example.skyapartmentscleaning.application.MyApp
import com.example.skyapartmentscleaning.navigator.Screens
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Alexander Volkov (Volkoks)
 *
 * Данный класс ViewModel для MainActivity
 */
class MainActivityViewModel @Inject constructor(
    private val router: Router,
    var navigatorHolder: NavigatorHolder
) : ViewModel() {


    /**
     * Метод включения фрагмента MainFragment
     */
    fun startMainFragment() {
        router.replaceScreen(Screens.MainScreen())
    }

    fun navigatorHolder(): NavigatorHolder {
        return navigatorHolder
    }

}