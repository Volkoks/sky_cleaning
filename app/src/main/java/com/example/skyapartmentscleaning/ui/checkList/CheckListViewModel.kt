package com.example.skyapartmentscleaning.ui.checkList

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skyapartmentscleaning.application.MyApp
import com.example.skyapartmentscleaning.data.ViewState
import com.example.skyapartmentscleaning.data.room.entites.Apart
import com.example.skyapartmentscleaning.data.room.datasource.ApartSource
import com.example.skyapartmentscleaning.data.checklist.DataPointCheckList
import com.example.skyapartmentscleaning.data.repository.IRepository
import com.example.skyapartmentscleaning.data.room.database.ApartDatabase
import com.example.skyapartmentscleaning.shareFile
import com.example.skyapartmentscleaning.utils.generate_report.IGenerateReport
import com.example.skyapartmentscleaning.data.room.entites.CleaningApart
import com.example.skyapartmentscleaning.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CheckListViewModel @Inject constructor(
    private val db: ApartDatabase,
    repo: IRepository<MutableList<DataPointCheckList>>,
    private val genReport: IGenerateReport
) : BaseViewModel<ViewState>() {

    init {
        liveDataToObserve.value = ViewState.SuccesDataCheckList(repo.getData())
    }

    fun sunscribeLivedata():LiveData<ViewState>{
        return liveDataToObserve
    }

    private val apartSource: ApartSource? by lazy {
        ApartSource(db.getApartDao())
    }

    /**
     * Возможно понадоится сделать класс для работы с CleaningApart по аналогии с ApartSource
     */
    fun saveApartCleaningReport(apart: Apart?, cleaningApart: CleaningApart?) {
        viewModelCoroutineScope.launch {
            apart?.let { apartSource?.addApart(it) }
            cleaningApart?.let { db.getCleaningApartDao().addCA(it) }
        }
    }

    fun generateCSVFileAndSend(context: Context, apart: Apart?, cleaningApart: CleaningApart?) {
        viewModelCoroutineScope.launch {
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

    fun getCurrentFormattedTime(): String {
        val date = Date()
        val sdf = SimpleDateFormat("HH:mm")
        val formattedDate: String = sdf.format(date)
        return formattedDate
    }

    override fun errorReturned(t: Throwable) {

    }
}
