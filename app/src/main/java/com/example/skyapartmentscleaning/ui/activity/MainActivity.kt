package com.example.skyapartmentscleaning.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.skyapartmentscleaning.application.MyApp
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.data.DARK_THEME
import com.example.skyapartmentscleaning.data.LIGHT_THEME
import com.example.skyapartmentscleaning.data.MY_SETTING
import com.example.skyapartmentscleaning.data.THEME_KEY
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

/**
 * @author Alexander Volkov (Volkoks)
 */
class MainActivity : AppCompatActivity() {


    private val navigator =
        SupportAppNavigator(this, supportFragmentManager, R.id.fragment_container)


    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApp.instance.appComponent.inject(this)
        setTheme(
            when (loadCurrentTheme()) {
                DARK_THEME -> DARK_THEME
                else -> LIGHT_THEME
            }
        )
        setContentView(R.layout.activity_main)
        viewModel.startMainFragment()

    }

    private fun loadCurrentTheme(): Int =
        getSharedPreferences(MY_SETTING, Context.MODE_PRIVATE).getInt(
            THEME_KEY,
            LIGHT_THEME
        )

    override fun onResumeFragments() {
        super.onResumeFragments()
        viewModel.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        viewModel.navigatorHolder.removeNavigator()
    }

}