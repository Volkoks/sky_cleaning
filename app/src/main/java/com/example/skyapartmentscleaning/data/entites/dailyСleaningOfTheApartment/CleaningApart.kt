package com.example.skycleaning.data.entity.dailyСleaningOfTheApartment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CleaningApart(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = BYPASSING_APART)
    var bypassingApart: String = "",
    @ColumnInfo(name = VIDEO_RECORDING)
    var cideoRecording: String = "",
    @ColumnInfo(name = TEMPERATURE_MODE)
    var temperarureMode: String = "",
    @ColumnInfo(name = OPEN_WINDOW_BRIDGES)
    var openWindowBridges: String = "",
    @ColumnInfo(name = FLUSH_TOILET)
    var flushToilet: String = "",
    @ColumnInfo(name = COLLECT_GARBAGE)
    var collectGarbage: String = "",
    @ColumnInfo(name = CHECK_FORGOTTEN_ITEM)
    var checkforgottenItem: String = "",
    @ColumnInfo(name = SMART_HOME)
    var smartHome: String = "",
    @ColumnInfo(name = TV)
    var tv: String = "",
    @ColumnInfo(name = TV_CONTOLLER)
    var tvController: String = "",
    @ColumnInfo(name = REFRIGERATOR)
    var regrigerator: String = "",
    @ColumnInfo(name = LIGHTING)
    var lighting: String = "",
    @ColumnInfo(name = BLUETOOTH_COLUMN)
    var bluetoothColumn: String = "",
    @ColumnInfo(name = OTHER_EQUIPMENT)
    var otherEquipment: String = "",
    @ColumnInfo(name = WASH_DISHES)
    var washDishes: String = "",
    @ColumnInfo(name = REMOVE_BED_LINEN)
    var removeBedLinen: String = "",
    @ColumnInfo(name = MAKING_BED)
    var makengBed: String = "",
    @ColumnInfo(name = CLEANING_OF_SINKS_AND_HYGIENE_AREAS)
    var cleaningOfSinksAndHygieneAreas: String = "",
    @ColumnInfo(name = CLEANING_SHOWER_BATH)
    var cleaningShowerBath: String = "",
    @ColumnInfo(name = CLEANING_TOILET)
    var cleaningToilet: String = "",
    @ColumnInfo(name = CHANGING_TOWELS_SUPPLIES)
    var changingTowelsSupplies: String = "",
    @ColumnInfo(name = WIPE_MIRROR_AND_DOOR)
    var wipeMirrorAndDoor: String = "",
    @ColumnInfo(name = WIPE_SHELVES_FURNITURE_IN_APART)
    var wipeShelvesFurnitureInApart: String = "",
    @ColumnInfo(name = WIPE_DECOR_MIRROR_IN_APART)
    var wipeDecorMirrorInApart: String = "",
    @ColumnInfo(name = WASH_WINDOW)
    var washWindow: String = "",
    @ColumnInfo(name = TOP_GUEST_ACCESSORIES)
    var topGuestAccessries: String = "",
    @ColumnInfo(name = REMOVE_FLOOR)
    var removeFloor: String = "",
    @ColumnInfo(name = CLEANING_COMMENT)
    var cleaningComment: String = "",

    ) {
    companion object {
        //        Начало уборки
        const val BYPASSING_APART = "Обход аппартамента.Степень загрезнения"
        const val VIDEO_RECORDING = "Сделать видеозапись аппартамента"
        const val TEMPERATURE_MODE = "Настроить температурный режим в аппартаменте(провертривание)"
        const val OPEN_WINDOW_BRIDGES = "Открыть штроры, окна(в теплое время года)"
        const val FLUSH_TOILET = "Спустите туалет и нанесите чистящее средство в унитаз"
        const val COLLECT_GARBAGE = "Собрать весь мусор в аппартаменте"
        const val CHECK_FORGOTTEN_ITEM = "Проверить на наличие забытых вещей гостей"

        //        Техническая часть аппартамента
        const val SMART_HOME = "Работа умного дома(при наличии)"
        const val TV = "Телевизор"
        const val TV_CONTOLLER = "Пульты"
        const val REFRIGERATOR = "Холодильник"
        const val LIGHTING = "Освещение"
        const val BLUETOOTH_COLUMN = "BLUETOOTH Колонка"
        const val OTHER_EQUIPMENT = "Инная аппартура по необходимости"

        //        Уборка ванной комнаты
        const val WASH_DISHES = "Собрать и вымыть посуду"
        const val REMOVE_BED_LINEN = "Собрать и убрать постельное белье"
        const val MAKING_BED = "Заправка постели"
        const val CLEANING_OF_SINKS_AND_HYGIENE_AREAS =
            "Чистка раковины, кафеля, гигиенических зон, стен и двери ванной комнаты."
        const val CLEANING_SHOWER_BATH = "Чистка душевой кабины/ванны"
        const val CLEANING_TOILET = "Чистка туалета"
        const val CHANGING_TOWELS_SUPPLIES = "Замена полотенец  и расходных материалов"
        const val WIPE_MIRROR_AND_DOOR = "Протереть зеркало и  дверь ванной комнаты"

        //      Уборка аппартамента
        const val WIPE_SHELVES_FURNITURE_IN_APART = "Протереть  полки, мебель в аппартаменте"
        const val WIPE_DECOR_MIRROR_IN_APART = "Протереть  зеркала и декор в аппартаменте"
        const val CHECK_WINDOW = "Проверить чистоту окон"
        const val WASH_WINDOW = "Вымыть окна(при необходимости вымыть)"
        const val TOP_GUEST_ACCESSORIES = "Пополнить гостевые принадлежности аппартамента"
        const val REMOVE_FLOOR = "Убрать пол(пропылесосить/вымыть)"
        const val CLEANING_COMMENT = "Коментарий по уборке(Доп. Информация)"
    }
}