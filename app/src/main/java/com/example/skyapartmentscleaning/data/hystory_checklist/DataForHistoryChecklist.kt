package com.example.skyapartmentscleaning.data.hystory_checklist


import com.example.skyapartmentscleaning.data.room.ConstantApartDB
import com.example.skyapartmentscleaning.data.room.entites.Apart
import com.example.skyapartmentscleaning.data.room.entites.CleaningApart

class DataForHistoryChecklist(val apart: Apart, val cleaningApart: CleaningApart) {

    fun getHystoryChecklist(): List<HistoryChecklistPoint> {
        return listHistoryChecklist
    }

    private val listHistoryChecklist: List<HistoryChecklistPoint> = listOf(
        HistoryChecklistPoint(ConstantApartDB.NUMBER_APARTS, apart.numberApart),
        HistoryChecklistPoint(ConstantApartDB.CHECK_DATE, apart.checkDate.toString()),
        HistoryChecklistPoint(ConstantApartDB.CHECK_TIME, apart.checkTime.toString()),
        HistoryChecklistPoint(ConstantApartDB.BYPASSING_APART, cleaningApart.bypassingApart),
        HistoryChecklistPoint(ConstantApartDB.VIDEO_RECORDING, cleaningApart.videoRecording),
        HistoryChecklistPoint(ConstantApartDB.TEMPERATURE_MODE, cleaningApart.temperarureMode),
        HistoryChecklistPoint(ConstantApartDB.OPEN_WINDOW_BRIDGES, cleaningApart.openWindowBridges),
        HistoryChecklistPoint(ConstantApartDB.FLUSH_TOILET, cleaningApart.flushToilet),
        HistoryChecklistPoint(ConstantApartDB.COLLECT_GARBAGE, cleaningApart.collectGarbage),
        HistoryChecklistPoint(
            ConstantApartDB.CHECK_FORGOTTEN_ITEM,
            cleaningApart.checkforgottenItem
        ),
        HistoryChecklistPoint(ConstantApartDB.FORGOTTEN_ITEM, cleaningApart.forgottenItem),
        HistoryChecklistPoint(ConstantApartDB.SMART_HOME, cleaningApart.smartHome),
        HistoryChecklistPoint(ConstantApartDB.TV, cleaningApart.tv),
        HistoryChecklistPoint(ConstantApartDB.TV_CONTOLLER, cleaningApart.tvController),
        HistoryChecklistPoint(ConstantApartDB.REFRIGERATOR, cleaningApart.refrigerator),
        HistoryChecklistPoint(ConstantApartDB.LIGHTING, cleaningApart.lighting),
        HistoryChecklistPoint(ConstantApartDB.BLUETOOTH_COLUMN, cleaningApart.bluetoothColumn),
        HistoryChecklistPoint(ConstantApartDB.OTHER_EQUIPMENT, cleaningApart.otherEquipment),
        HistoryChecklistPoint(ConstantApartDB.WASH_DISHES, cleaningApart.washDishes),
        HistoryChecklistPoint(ConstantApartDB.REMOVE_BED_LINEN, cleaningApart.removeBedLinen),
        HistoryChecklistPoint(ConstantApartDB.MAKING_BED, cleaningApart.makingBed),
        HistoryChecklistPoint(
            ConstantApartDB.CLEANING_OF_SINKS_AND_HYGIENE_AREAS,
            cleaningApart.cleaningOfSinksAndHygieneAreas
        ),
        HistoryChecklistPoint(
            ConstantApartDB.CLEANING_SHOWER_BATH,
            cleaningApart.cleaningShowerBath
        ),
        HistoryChecklistPoint(ConstantApartDB.CLEANING_TOILET, cleaningApart.cleaningToilet),
        HistoryChecklistPoint(
            ConstantApartDB.CHANGING_TOWELS_SUPPLIES,
            cleaningApart.changingTowelsSupplies
        ),
        HistoryChecklistPoint(
            ConstantApartDB.WIPE_MIRROR_AND_DOOR,
            cleaningApart.wipeMirrorAndDoor
        ),
        HistoryChecklistPoint(
            ConstantApartDB.WIPE_SHELVES_FURNITURE_IN_APART,
            cleaningApart.wipeShelvesFurnitureInApart
        ),
        HistoryChecklistPoint(
            ConstantApartDB.WIPE_DECOR_MIRROR_IN_APART,
            cleaningApart.wipeDecorMirrorInApart
        ),
        HistoryChecklistPoint(ConstantApartDB.CHECK_WINDOW, cleaningApart.checkWindow),
        HistoryChecklistPoint(ConstantApartDB.WASH_WINDOW, cleaningApart.washWindow),
        HistoryChecklistPoint(
            ConstantApartDB.TOP_GUEST_ACCESSORIES,
            cleaningApart.topGuestAccessries
        ),
        HistoryChecklistPoint(ConstantApartDB.REMOVE_FLOOR, cleaningApart.removeFloor),
        HistoryChecklistPoint(ConstantApartDB.CLEANING_COMMENT, cleaningApart.cleaningComment)
    )
}