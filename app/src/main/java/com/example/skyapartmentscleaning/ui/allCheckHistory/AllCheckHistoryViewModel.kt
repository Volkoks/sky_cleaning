package com.example.skycleaning.ui.allCheckHistory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skycleaning.MyApp
import com.example.skycleaning.data.ViewState
import com.example.skycleaning.data.entity.apart.Apart
import com.example.skycleaning.data.entity.apart.ApartSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AllCheckHistoryViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Default
    }
    private val apartDao by lazy {
        MyApp.apartDB.getApartDao()
    }
    private val apartSource: ApartSource? by lazy {
        ApartSource(apartDao)
    }
    var verifiedApartments: MutableLiveData<ViewState> = MutableLiveData()

    init {
        launch {
            verifiedApartments.postValue(apartSource?.loadListAllApart()?.let { ViewState(it) })

        }
    }
}