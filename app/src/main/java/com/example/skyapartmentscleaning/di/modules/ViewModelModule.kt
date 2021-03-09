package com.example.skyapartmentscleaning.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skyapartmentscleaning.di.annatation.VIewModelKey
import com.example.skyapartmentscleaning.di.factory.ViewModelFactory
import com.example.skyapartmentscleaning.ui.activity.MainActivityViewModel
import com.example.skyapartmentscleaning.ui.allApart.AllApartmentsViewModel
import com.example.skyapartmentscleaning.ui.checkList.CheckListViewModel
import com.example.skyapartmentscleaning.ui.historyChecklist.CheckHistoryViewModel
import com.example.skyapartmentscleaning.ui.main.MainViewModel
import com.example.skyapartmentscleaning.ui.setting.SettingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @VIewModelKey(MainActivityViewModel::class)
    protected abstract fun mainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @VIewModelKey(MainViewModel::class)
    protected abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @VIewModelKey(AllApartmentsViewModel::class)
    protected abstract fun allApartViewModel(allApartmentsViewModel: AllApartmentsViewModel): ViewModel

    @Binds
    @IntoMap
    @VIewModelKey(CheckListViewModel::class)
    protected abstract fun checkListViewModel(checkListViewModel: CheckListViewModel): ViewModel

    @Binds
    @IntoMap
    @VIewModelKey(CheckHistoryViewModel::class)
    protected abstract fun checkHistoryViewModel(checkHistoryViewModel: CheckHistoryViewModel): ViewModel

    @Binds
    @IntoMap
    @VIewModelKey(SettingViewModel::class)
    protected abstract fun settingViewModel(settingViewModel: SettingViewModel): ViewModel
}