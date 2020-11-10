package com.example.skyapartmentscleaning.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skyapartmentscleaning.MyApp
import com.example.skyapartmentscleaning.data.ViewState
import com.example.skyapartmentscleaning.data.entites.apart.ApartSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import kotlin.coroutines.CoroutineContext
/**
 * @author Alexander Volkov (Volkoks)
 */
class MainViewModel : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Default
    }
    private val apartDao by lazy {
        MyApp.instance.getDB.getApartDao()
    }
    private val apartSource: ApartSource? by lazy {
        ApartSource(apartDao)
    }
    var verifiedApartments: MutableLiveData<ViewState> = MutableLiveData()

    init {
        loadData()
    }

    fun loadData() {
        launch {
            verifiedApartments.postValue(apartSource?.loadListAllApart()?.let { ViewState(it) })
        }
    }
}