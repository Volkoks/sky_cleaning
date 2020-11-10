package com.example.skyapartmentscleaning

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.skyapartmentscleaning.ui.mainActivity.MainActivityViewModel
import ru.terrakok.cicerone.android.support.SupportAppNavigator

/**
 * @author Alexander Volkov (Volkoks)
 */
class MainActivity : AppCompatActivity() {

    private val navigatorHolder = MyApp.instance.getNavigatorHolder
    private val navigator =
        SupportAppNavigator(this, supportFragmentManager, R.id.fragment_container)

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        viewModel.startMainFragment()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

}