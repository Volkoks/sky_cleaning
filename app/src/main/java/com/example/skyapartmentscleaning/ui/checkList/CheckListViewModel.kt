package com.example.skyapartmentscleaning.ui.checkList

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skyapartmentscleaning.MyApp
import com.example.skyapartmentscleaning.data.room.entites.Apart
import com.example.skyapartmentscleaning.data.room.datasource.ApartSource
import com.example.skyapartmentscleaning.data.checklist.DataPointCheckList
import com.example.skyapartmentscleaning.data.repository.IRepository
import com.example.skyapartmentscleaning.shareFile
import com.example.skyapartmentscleaning.utils.generate_report.IGenerateReport
import com.example.skyapartmentscleaning.data.room.entites.CleaningApart
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class CheckListViewModel(
    private val repo: IRepository<MutableList<DataPointCheckList>>,
    private val genReport: IGenerateReport
) : ViewModel(), CoroutineScope {

    val dataForPointCheckList: MutableLiveData<MutableList<DataPointCheckList>> = MutableLiveData()


    init {
        dataForPointCheckList.value = repo.getData()
    }

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.IO
    }
    private val apartDao by lazy {
        MyApp.instance.getDB.getApartDao()
    }
    private val cleaningApartDao by lazy {
        MyApp.instance.getDB.getCleaningApartDao()
    }
    private val apartSource: ApartSource? by lazy {
        ApartSource(apartDao)
    }

    /**
     * Возможно понадоится сделать класс для работы с CleaningApart по аналогии с ApartSource
     */
    fun saveApartCleaningReport(apart: Apart?, cleaningApart: CleaningApart?) {
        launch {
            apart?.let { apartSource?.addApart(it) }
            cleaningApart?.let { cleaningApartDao.addCA(it) }
        }
    }

    fun generateCSVFileAndSend(context: Context, apart: Apart?, cleaningApart: CleaningApart?) {
        launch {
            val report = genReport.generateExcelReport(context, apart, cleaningApart)
            report?.let { shareFile(context, it) }
        }
    }

    fun getCurrentFormattedDate(): String {
        val date = Date()
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val formattedDate: String = sdf.format(date)
        return formattedDate
    }
}
