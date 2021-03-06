package com.example.skyapartmentscleaning.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skyapartmentscleaning.MyApp
import com.example.skyapartmentscleaning.data.ViewState
import com.example.skyapartmentscleaning.data.entites.apart.ApartSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import kotlin.coroutines.CoroutineContext

/**
 * @author Alexander Volkov (Volkoks)
 */
class MainViewModel : ViewModel(), CoroutineScope {

    val router: Router = MyApp.instance.getRouter

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
        verifiedApartments.value = ViewState.Loading(1)
        launch {
            verifiedApartments.postValue(
                apartSource?.loadListAllApart()?.let { ViewState.Succes(it) })
        }
    }
}