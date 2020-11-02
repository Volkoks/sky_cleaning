package com.example.skyapartmentscleaning.ui.apart

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.skyapartmentscleaning.data.entites.apart.Apart
import com.example.skyapartmentscleaning.data.entites.apart.ApartSource
import com.example.skyapartmentscleaning.MyApp
import com.example.skyapartmentscleaning.generateFileCSVToInternalStorage
import com.example.skyapartmentscleaning.shareFile
import com.example.skycleaning.data.entity.dailyСleaningOfTheApartment.CleaningApart
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class ApartViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.IO
    }
    private val apartDao by lazy {
        MyApp.apartDB.getApartDao()
    }
    private val cleaningApartDao by lazy {
        MyApp.apartDB.getCleaningApartDao()
    }
    private val apartSource: ApartSource? by lazy {
        ApartSource(apartDao)
    }

    fun getCurrentFormattedDate(apart: Apart?): String {
        val date = Date()
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val formattedDate: String = sdf.format(date)
        apart?.checkDate = formattedDate
        return formattedDate
    }

    /**
     * Возможно понадоится сделать класс для работы с CleaningApart по аналогии с ApartSource
     */
    fun saveApartCleaningReport(apart: Apart?,cleaningApart: CleaningApart?) {
        launch {
            apart?.let { apartSource?.addApart(it) }
            cleaningApart?.let { cleaningApartDao.addCA(it) }
        }
    }

    fun generateCSVFileAndSend(context: Context, apart: Apart?) {
        launch {
            val newCSVFile = generateFileCSVToInternalStorage(context, "checkApart.csv")
            newCSVFile?.let {
                csvWriter().open(it) {
                    apart?.let { writeRow(listOf("[${Apart.NUMBER_APARTS}]", apart.numberApart)) }
                }
            }
            newCSVFile?.let { shareFile(context, it) }
        }
    }


    override fun onCleared() {
        coroutineContext.cancel()
        super.onCleared()
    }

}