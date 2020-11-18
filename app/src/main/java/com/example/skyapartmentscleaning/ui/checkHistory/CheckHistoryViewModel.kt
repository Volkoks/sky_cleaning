package com.example.skyapartmentscleaning.ui.checkHistory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skyapartmentscleaning.MyApp
import com.example.skyapartmentscleaning.data.entites.CheckList.CheckListCASource
import com.example.skyapartmentscleaning.data.entites.apart.Apart
import com.example.skycleaning.data.entity.dailyСleaningOfTheApartment.CheckListCleaningApart
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * @author Alexander Volkov (Volkoks)
 */
class CheckHistoryViewModel : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.IO
    }
    private val checkListDao by lazy {
        MyApp.instance.getDB.getCheckListCADao()
    }
    private val checkListSource by lazy {
        CheckListCASource(checkListDao)
    }
    var apart: Apart? = null
    var checkList: MutableLiveData<CheckListCleaningApart> = MutableLiveData()

    fun loadData() {
        launch {
            checkList.postValue(apart?.id?.let { checkListSource.getCheckListById(it) })
        }
    }


}