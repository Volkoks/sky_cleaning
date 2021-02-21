package com.example.skyapartmentscleaning.ui.checkList

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skyapartmentscleaning.MyApp
import com.example.skyapartmentscleaning.data.entites.apart.Apart
import com.example.skyapartmentscleaning.data.entites.apart.ApartSource
import com.example.skyapartmentscleaning.data.entites.checklist.DataPointCheckList
import com.example.skyapartmentscleaning.data.repository.CheckListPointRespository
import com.example.skyapartmentscleaning.generateFileCSVToInternalStorage
import com.example.skyapartmentscleaning.shareFile
import com.example.skycleaning.data.entity.dailyСleaningOfTheApartment.CleaningApart
import com.github.doyaaaaaken.kotlincsv.client.ICsvFileWriter
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class CheckListViewModel : ViewModel(), CoroutineScope {

    val dataForPointCheckList: MutableLiveData<List<DataPointCheckList>> = MutableLiveData()

    init {
        dataForPointCheckList.value = CheckListPointRespository.getListDataForCheckList()
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
     *Метод записи строк с класса чек листа уборки апартамента класса Apart
     */
    private fun ICsvFileWriter.writingStringsApart(apart: Apart) {
        writeRow(listOf("[${Apart.NUMBER_APARTS}]", apart.numberApart))
        writeRow(listOf("[${Apart.CHECK_DATE}]", apart.checkDate))
    }

    /**
     *Метод записи строк с класса чек листа уборки апартамента класса CleaningApart
     */
    private fun ICsvFileWriter.writingStringsCleaningApart(cleaningApart: CleaningApart) {

        writeRow(listOf("[${CleaningApart.BYPASSING_APART}]", cleaningApart.bypassingApart))
        writeRow(listOf("[${CleaningApart.VIDEO_RECORDING}]", cleaningApart.videoRecording))
        writeRow(listOf("[${CleaningApart.TEMPERATURE_MODE}]", cleaningApart.temperarureMode))
        writeRow(listOf("[${CleaningApart.OPEN_WINDOW_BRIDGES}]", cleaningApart.openWindowBridges))
        writeRow(listOf("[${CleaningApart.FLUSH_TOILET}]", cleaningApart.flushToilet))
        writeRow(listOf("[${CleaningApart.COLLECT_GARBAGE}]", cleaningApart.collectGarbage))
        writeRow(
            listOf(
                "[${CleaningApart.CHECK_FORGOTTEN_ITEM}]",
                cleaningApart.checkforgottenItem
            )
        )
        writeRow(listOf("[${CleaningApart.FORGOTTEN_ITEM}]", cleaningApart.forgottenItem))
        writeRow(listOf("[${CleaningApart.COLLECT_GARBAGE}]", cleaningApart.collectGarbage))
        writeRow(listOf("[${CleaningApart.SMART_HOME}]", cleaningApart.smartHome))
        writeRow(listOf("[${CleaningApart.TV}]", cleaningApart.tv))
        writeRow(listOf("[${CleaningApart.TV_CONTOLLER}]", cleaningApart.tvController))
        writeRow(listOf("[${CleaningApart.REFRIGERATOR}]", cleaningApart.refrigerator))
        writeRow(listOf("[${CleaningApart.LIGHTING}]", cleaningApart.lighting))
        writeRow(listOf("[${CleaningApart.BLUETOOTH_COLUMN}]", cleaningApart.bluetoothColumn))
        writeRow(listOf("[${CleaningApart.OTHER_EQUIPMENT}]", cleaningApart.otherEquipment))
        writeRow(listOf("[${CleaningApart.WASH_DISHES}]", cleaningApart.washDishes))
        writeRow(listOf("[${CleaningApart.REMOVE_BED_LINEN}]", cleaningApart.removeBedLinen))
        writeRow(listOf("[${CleaningApart.MAKING_BED}]", cleaningApart.makingBed))
        writeRow(
            listOf(
                "[${CleaningApart.CLEANING_OF_SINKS_AND_HYGIENE_AREAS}]",
                cleaningApart.cleaningOfSinksAndHygieneAreas
            )
        )
        writeRow(
            listOf(
                "[${CleaningApart.CLEANING_SHOWER_BATH}]",
                cleaningApart.cleaningShowerBath
            )
        )
        writeRow(listOf("[${CleaningApart.CLEANING_TOILET}]", cleaningApart.cleaningToilet))
        writeRow(
            listOf(
                "[${CleaningApart.CHANGING_TOWELS_SUPPLIES}]",
                cleaningApart.changingTowelsSupplies
            )
        )
        writeRow(listOf("[${CleaningApart.WIPE_MIRROR_AND_DOOR}]", cleaningApart.wipeMirrorAndDoor))
        writeRow(
            listOf(
                "[${CleaningApart.WIPE_SHELVES_FURNITURE_IN_APART}]",
                cleaningApart.wipeShelvesFurnitureInApart
            )
        )
        writeRow(
            listOf(
                "[${CleaningApart.WIPE_DECOR_MIRROR_IN_APART}]",
                cleaningApart.wipeDecorMirrorInApart
            )
        )
        writeRow(listOf("[${CleaningApart.CHECK_WINDOW}]", cleaningApart.checkWindow))
        writeRow(listOf("[${CleaningApart.WASH_WINDOW}]", cleaningApart.washWindow))
        writeRow(
            listOf(
                "[${CleaningApart.TOP_GUEST_ACCESSORIES}]",
                cleaningApart.topGuestAccessries
            )
        )
        writeRow(listOf("[${CleaningApart.REMOVE_FLOOR}]", cleaningApart.removeFloor))
        writeRow(listOf("[${CleaningApart.CLEANING_COMMENT}]", cleaningApart.cleaningComment))
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
            val newCSVFile = generateFileCSVToInternalStorage(
                context,
                "Проверка: " + "${apart?.numberApart} ${apart?.checkDate}" + ".csv"
            )
            newCSVFile?.let {
                csvWriter().open(it) {
                    apart?.let { writingStringsApart(apart) }
                    cleaningApart?.let { writingStringsCleaningApart(cleaningApart) }
                }
            }
            newCSVFile?.let { shareFile(context, it) }
        }
    }
    fun getCurrentFormattedDate(apart: Apart?): String {
        val date = Date()
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val formattedDate: String = sdf.format(date)
        return formattedDate
    }

}