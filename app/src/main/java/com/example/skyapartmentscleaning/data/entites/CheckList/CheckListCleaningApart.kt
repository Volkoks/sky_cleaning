package com.example.skycleaning.data.entity.dailyСleaningOfTheApartment

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
/**
 * @author Alexander Volkov (Volkoks)
 */
@Parcelize
@Entity(tableName = "check_list")
data class CheckListCleaningApart(
    @PrimaryKey
    @ColumnInfo
    var id:String = "",
    @ColumnInfo
    var bypassingApart: String = "",
    @ColumnInfo()
    var videoRecording: String = "",
    @ColumnInfo()
    var temperarureMode: String = "",
    @ColumnInfo()
    var openWindowBridges: String = "",
    @ColumnInfo()
    var flushToilet: String = "",
    @ColumnInfo()
    var collectGarbage: String = "",
    @ColumnInfo()
    var checkforgottenItem: String = "",
    @ColumnInfo()
    var forgottenItem:String = "",
    @ColumnInfo()
    var smartHome: String = "",
    @ColumnInfo()
    var tv: String = "",
    @ColumnInfo()
    var tvController: String = "",
    @ColumnInfo()
    var refrigerator: String = "",
    @ColumnInfo()
    var lighting: String = "",
    @ColumnInfo()
    var bluetoothColumn: String = "",
    @ColumnInfo()
    var otherEquipment: String = "",
    @ColumnInfo()
    var washDishes: String = "",
    @ColumnInfo()
    var removeBedLinen: String = "",
    @ColumnInfo()
    var makingBed: String = "",
    @ColumnInfo()
    var cleaningOfSinksAndHygieneAreas: String = "",
    @ColumnInfo()
    var cleaningShowerBath: String = "",
    @ColumnInfo()
    var cleaningToilet: String = "",
    @ColumnInfo()
    var changingTowelsSupplies: String = "",
    @ColumnInfo()
    var wipeMirrorAndDoor: String = "",
    @ColumnInfo()
    var wipeShelvesFurnitureInApart: String = "",
    @ColumnInfo()
    var wipeDecorMirrorInApart: String = "",
    @ColumnInfo()
    var checkWindow:String = "",
    @ColumnInfo()
    var washWindow: String = "",
    @ColumnInfo()
    var topGuestAccessries: String = "",
    @ColumnInfo()
    var removeFloor: String = "",
    @ColumnInfo()
    var cleaningComment: String = "",

    ): Parcelable {
    companion object {
//        //        Начало уборки
//        const val BYPASSING_APART = "Обход аппартамента.Степень загрезнения"
//        const val VIDEO_RECORDING = "Сделать видеозапись аппартамента"
//        const val TEMPERATURE_MODE = "Настроить температурный режим в аппартаменте(провертривание)"
//        const val OPEN_WINDOW_BRIDGES = "Открыть штроры, окна(в теплое время года)"
//        const val FLUSH_TOILET = "Спустите туалет и нанесите чистящее средство в унитаз"
//        const val COLLECT_GARBAGE = "Собрать весь мусор в аппартаменте"
//        const val CHECK_FORGOTTEN_ITEM = "Проверить на наличие забытых вещей гостей"
//        const val FORGOTTEN_ITEM = "Забытые вещи"
//
//        //        Техническая часть аппартамента
//        const val SMART_HOME = "Работа умного дома(при наличии)"
//        const val TV = "Телевизор"
//        const val TV_CONTOLLER = "Пульты"
//        const val REFRIGERATOR = "Холодильник"
//        const val LIGHTING = "Освещение"
//        const val BLUETOOTH_COLUMN = "BLUETOOTH Колонка"
//        const val OTHER_EQUIPMENT = "Инная аппартура по необходимости"
//
//        //        Уборка ванной комнаты
//
//        const val CLEANING_OF_SINKS_AND_HYGIENE_AREAS =
//            "Чистка раковины, кафеля, гигиенических зон, стен и двери ванной комнаты."
//        const val CLEANING_SHOWER_BATH = "Чистка душевой кабины/ванны"
//        const val CLEANING_TOILET = "Чистка туалета"
//        const val CHANGING_TOWELS_SUPPLIES = "Замена полотенец  и расходных материалов"
//        const val WIPE_MIRROR_AND_DOOR = "Протереть зеркало и  дверь ванной комнаты"
//
//
//        //      Уборка аппартамента
//        const val WASH_DISHES = "Собрать и вымыть посуду"
//        const val WIPE_SHELVES_FURNITURE_IN_APART = "Протереть  полки, мебель в аппартаменте"
//        const val REMOVE_BED_LINEN = "Собрать и убрать постельное белье"
//        const val MAKING_BED = "Заправка постели"
//        const val WIPE_DECOR_MIRROR_IN_APART = "Протереть  зеркала и декор в аппартаменте"
//        const val CHECK_WINDOW = "Проверить чистоту окон"
//        const val WASH_WINDOW = "Вымыть окна(при необходимости вымыть)"
//        const val TOP_GUEST_ACCESSORIES = "Пополнить гостевые принадлежности аппартамента"
//        const val REMOVE_FLOOR = "Убрать пол(пропылесосить/вымыть)"
//        const val CLEANING_COMMENT = "Коментарий по уборке(Доп. Информация)"
    }
}