package com.example.skyapartmentscleaning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.skyapartmentscleaning.ui.main.MainFragment
import ru.terrakok.cicerone.android.support.SupportAppNavigator

/**
 * @author Alexander Volkov (Volkoks)
 */
class MainActivity : AppCompatActivity() {

    private val  navigatorHolder = MyApp.instance.getNavigatorHolder
    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.fragment_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val mainFragment = MainFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, mainFragment)
            .commit()
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