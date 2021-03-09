package com.example.skyapartmentscleaning.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skyapartmentscleaning.application.MyApp
import com.example.skyapartmentscleaning.data.ViewState
import com.example.skyapartmentscleaning.data.room.database.ApartDatabase
import com.example.skyapartmentscleaning.data.room.datasource.ApartSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * @author Alexander Volkov (Volkoks)
 */
class MainViewModel @Inject constructor(
    val router: Router,
    val db: ApartDatabase
) : ViewModel(), CoroutineScope {

//    val router: Router = MyApp.instance.getRouter

    //    private val apartDao by lazy {
//        MyApp.instance.getDB.getApartDao()
//    }
    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Default
    }
    private val apartSource: ApartSource? by lazy {
        ApartSource(db.getApartDao())
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