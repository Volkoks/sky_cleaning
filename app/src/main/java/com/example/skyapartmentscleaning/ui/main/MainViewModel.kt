package com.example.skyapartmentscleaning.ui.main

import androidx.lifecycle.LiveData
import com.example.skyapartmentscleaning.data.ViewState
import com.example.skyapartmentscleaning.data.room.database.ApartDatabase
import com.example.skyapartmentscleaning.data.room.datasource.ApartSource
import com.example.skyapartmentscleaning.data.room.entites.Apart
import com.example.skyapartmentscleaning.ui.base.BaseViewModel
import kotlinx.coroutines.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Alexander Volkov (Volkoks)
 */
class MainViewModel @Inject constructor(
    val router: Router,
    val db: ApartDatabase
) : BaseViewModel<ViewState>() {

    private val apartSource: ApartSource? by lazy {
        ApartSource(db.getApartDao())
    }

    fun sunscribeLivedata(): LiveData<ViewState> {
        return liveDataToObserve
    }

    init {
        loadData()
    }

    fun loadData() {
        liveDataToObserve.value = ViewState.Loading(1)
        viewModelCoroutineScope.launch {

            liveDataToObserve.postValue(
                apartSource?.loadListAllApart()?.let { ViewState.SuccesApart(it) })
        }
    }

    fun cancelJobCoroutines() {
        cancelJob()
    }

    fun deleteApart(apart: Apart) {
        viewModelCoroutineScope.launch {
            delay(2500)
            db.getApartDao().deleteApart(apart)
            liveDataToObserve.postValue(
                apartSource?.loadListAllApart()?.let { ViewState.SuccesApart(it) })
        }

    }

    override fun errorReturned(t: Throwable) {
        liveDataToObserve.value = ViewState.Error(t)
    }


}