package com.example.skyapartmentscleaning.navigator


import com.example.skyapartmentscleaning.data.entites.apart.Apart
import com.example.skyapartmentscleaning.ui.allApart.AllApartmentsFragment
import com.example.skyapartmentscleaning.ui.checkHistory.CheckHistoryFragment
import com.example.skyapartmentscleaning.ui.checkList.ChekListListenerFragment
import com.example.skyapartmentscleaning.ui.main.MainFragment
import com.example.skyapartmentscleaning.ui.setting.SettingFragment
import com.example.skycleaning.data.entity.dailyСleaningOfTheApartment.CleaningApart
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class AllApartmentsScreen : SupportAppScreen() {
        override fun getFragment() = AllApartmentsFragment.newInstance()
    }

    class CheckHistoryScreen : SupportAppScreen() {
        override fun getFragment() = CheckHistoryFragment.newInstance()
    }

    class MainScreen : SupportAppScreen() {
        override fun getFragment() = MainFragment.newInstance()
    }

    class CheckListScreen(val apart: Apart) : SupportAppScreen() {
        override fun getFragment() = ChekListListenerFragment.newInstance(apart, CleaningApart())
    }

    class SettingScreen() : SupportAppScreen() {
        override fun getFragment() = SettingFragment.newInstance()
    }
}