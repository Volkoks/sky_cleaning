package com.example.skyapartmentscleaning.navigator


import com.example.skyapartmentscleaning.data.room.entites.Apart
import com.example.skyapartmentscleaning.ui.allApart.AllApartmentsFragment
import com.example.skyapartmentscleaning.ui.historyChecklist.CheckHistoryFragment
import com.example.skyapartmentscleaning.ui.checkList.ChekListFragment
import com.example.skyapartmentscleaning.ui.main.MainFragment
import com.example.skyapartmentscleaning.ui.setting.SettingFragment
import com.example.skyapartmentscleaning.data.room.entites.CleaningApart
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class AllApartmentsScreen : SupportAppScreen() {
        override fun getFragment() = AllApartmentsFragment.newInstance()
    }

    class CheckHistoryScreen(val apart: Apart) : SupportAppScreen() {
        override fun getFragment() = CheckHistoryFragment.newInstance(apart)
    }

    class MainScreen : SupportAppScreen() {
        override fun getFragment() = MainFragment.newInstance()
    }

    class CheckListScreen(val apart: Apart) : SupportAppScreen() {
        override fun getFragment() = ChekListFragment.newInstance(apart, CleaningApart())
    }

    class SettingScreen() : SupportAppScreen() {
        override fun getFragment() = SettingFragment.newInstance()
    }
}