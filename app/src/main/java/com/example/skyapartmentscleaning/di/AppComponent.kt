package com.example.skyapartmentscleaning.di

import android.content.Context
import com.example.skyapartmentscleaning.di.modules.*
import com.example.skyapartmentscleaning.ui.activity.MainActivity
import com.example.skyapartmentscleaning.ui.allApart.AllApartmentsFragment
import com.example.skyapartmentscleaning.ui.checkList.CheckListFragment
import com.example.skyapartmentscleaning.ui.historyChecklist.CheckHistoryFragment
import com.example.skyapartmentscleaning.ui.main.MainFragment
import com.example.skyapartmentscleaning.ui.setting.SettingFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        CiceroneModule::class,
        DatabaseModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        UtilsModule::class
    ]
)
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun app(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)

    fun inject(allApartmentsFragment: AllApartmentsFragment)
    fun inject(checkListFragment: CheckListFragment)
    fun inject(checkHistoryFragment: CheckHistoryFragment)
    fun inject(mainFragment: MainFragment)
    fun inject(settingFragment: SettingFragment)
}