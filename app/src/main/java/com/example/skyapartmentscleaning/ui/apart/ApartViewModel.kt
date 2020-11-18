package com.example.skyapartmentscleaning.ui.apart

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.skyapartmentscleaning.data.entites.apart.Apart
import com.example.skyapartmentscleaning.data.entites.apart.ApartSource
import com.example.skyapartmentscleaning.MyApp
import com.example.skyapartmentscleaning.generateFileCSVToInternalStorage
import com.example.skyapartmentscleaning.shareFile
import com.example.skycleaning.data.entity.dailyСleaningOfTheApartment.CheckListCleaningApart
import com.github.doyaaaaaken.kotlincsv.client.ICsvFileWriter
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext
/**
 * @author Alexander Volkov (Volkoks)
 */
class ApartViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.IO
    }
    private val apartDao by lazy {
        MyApp.instance.getDB.getApartDao()
    }
    private val cleaningApartDao by lazy {
        MyApp.instance.getDB.getCheckListCADao()
    }
    private val apartSource: ApartSource? by lazy {
        ApartSource(apartDao)
    }

    fun getCurrentFormattedDate(apart: Apart?): String {
        val date = Date()
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val formattedDate: String = sdf.format(date)
        return formattedDate
    }

    /**
     * Возможно понадоится сделать класс для работы с CleaningApart по аналогии с ApartSource
     */
    fun saveApartCleaningReport(apart: Apart?, checkListCleaningApart: CheckListCleaningApart?) {
        launch {
            apart?.let { apartSource?.addApart(it) }
            checkListCleaningApart?.let { cleaningApartDao.addCheckList(it) }
        }
    }

    fun generateCSVFileAndSend(context: Context, apart: Apart?, checkListCleaningApart: CheckListCleaningApart?) {
        launch {
            val newCSVFile = generateFileCSVToInternalStorage(context, "Проверка: "+"${apart?.numberApart} ${apart?.checkDate}"+".csv")
            newCSVFile?.let {
                csvWriter().open(it) {
                    apart?.let { writingStringsApart(apart)}
                    checkListCleaningApart?.let { writingStringsCleaningApart(checkListCleaningApart) }
                }
            }
            newCSVFile?.let { shareFile(context, it) }
        }
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
    private fun ICsvFileWriter.writingStringsCleaningApart(checkListCleaningApart: CheckListCleaningApart) {
        //      Начало уборки
        writeRow(listOf("Обход аппартамента.Степень загрезнения", checkListCleaningApart.bypassingApart))
        writeRow(listOf("Сделать видеозапись аппартамента", checkListCleaningApart.videoRecording))
        writeRow(listOf("Настроить температурный режим в аппартаменте(провертривание)", checkListCleaningApart.temperarureMode))
        writeRow(listOf("Открыть штроры, окна(в теплое время года)", checkListCleaningApart.openWindowBridges))
        writeRow(listOf("Спустите туалет и нанесите чистящее средство в унитаз", checkListCleaningApart.flushToilet))
        writeRow(listOf("Собрать весь мусор в аппартаменте", checkListCleaningApart.collectGarbage))
        writeRow(listOf("Проверить на наличие забытых вещей гостей",checkListCleaningApart.checkforgottenItem))
        writeRow(listOf("Забытые вещи", checkListCleaningApart.forgottenItem))
        //        Техническая часть аппартамента
        writeRow(listOf("Работа умного дома(при наличии)", checkListCleaningApart.smartHome))
        writeRow(listOf("Телевизор", checkListCleaningApart.tv))
        writeRow(listOf("Пульты", checkListCleaningApart.tvController))
        writeRow(listOf("Холодильник", checkListCleaningApart.refrigerator))
        writeRow(listOf("Освещение", checkListCleaningApart.lighting))
        writeRow(listOf("BLUETOOTH Колонка", checkListCleaningApart.bluetoothColumn))
        writeRow(listOf("Инная аппартура по необходимости", checkListCleaningApart.otherEquipment))
        //        Уборка ванной комнаты
        writeRow(
            listOf(
                "Чистка раковины, кафеля, гигиенических зон, стен и двери ванной комнаты",
                checkListCleaningApart.cleaningOfSinksAndHygieneAreas
            )
        )
        writeRow(
            listOf(
                "Чистка душевой кабины/ванны",
                checkListCleaningApart.cleaningShowerBath
            )
        )
        writeRow(listOf("Чистка туалета", checkListCleaningApart.cleaningToilet))
        writeRow(
            listOf(
                "Замена полотенец  и расходных материалов",
                checkListCleaningApart.changingTowelsSupplies
            )
        )
        writeRow(listOf("Протереть зеркало и  дверь ванной комнаты", checkListCleaningApart.wipeMirrorAndDoor))

        //      Уборка аппартамента
        writeRow(listOf("Собрать и вымыть посуду", checkListCleaningApart.washDishes))
        writeRow(listOf("Протереть  полки, мебель в аппартаменте", checkListCleaningApart.removeBedLinen))
        writeRow(listOf("Собрать и убрать постельное белье", checkListCleaningApart.makingBed))
        writeRow(
            listOf(
                "Заправка постели",
                checkListCleaningApart.wipeShelvesFurnitureInApart
            )
        )
        writeRow(
            listOf(
                "Протереть  зеркала и декор в аппартаменте",
                checkListCleaningApart.wipeDecorMirrorInApart
            )
        )
        writeRow(listOf("Проверить чистоту окон", checkListCleaningApart.checkWindow))
        writeRow(listOf("Вымыть окна(при необходимости вымыть)", checkListCleaningApart.washWindow))
        writeRow(
            listOf(
                "Пополнить гостевые принадлежности аппартамента",
                checkListCleaningApart.topGuestAccessries
            )
        )
        writeRow(listOf("Убрать пол(пропылесосить/вымыть)", checkListCleaningApart.removeFloor))
        writeRow(listOf("Коментарий по уборке(Доп. Информация)", checkListCleaningApart.cleaningComment))
    }


    override fun onCleared() {
        coroutineContext.cancel()
        super.onCleared()
    }

}