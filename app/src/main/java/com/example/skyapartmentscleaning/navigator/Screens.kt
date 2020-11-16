package com.example.skyapartmentscleaning.navigator


import com.example.skyapartmentscleaning.data.entites.apart.Apart
import com.example.skyapartmentscleaning.ui.allApart.AllApartmentsFragment
import com.example.skyapartmentscleaning.ui.apart.ApartFragment
import com.example.skyapartmentscleaning.ui.checkHistory.CheckHistoryFragment
import com.example.skyapartmentscleaning.ui.main.MainFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class AllApartmentsScreen : SupportAppScreen() {
        override fun getFragment() = AllApartmentsFragment.newInstance()
    }

    class ApartScreen(val apart: Apart) : SupportAppScreen() {
        override fun getFragment() = ApartFragment.newInstance(apart)
    }

    class CheckHistoryScreen:SupportAppScreen(){
        override fun getFragment()=CheckHistoryFragment.newInstance()
    }
    class MainScreen:SupportAppScreen(){
        override fun getFragment()=MainFragment.newInstance()
    }
}