package com.example.skyapartmentscleaning.utils.maper

import android.content.Context
import com.example.skyapartmentscleaning.data.entites.apart.Apart
import com.example.skyapartmentscleaning.data.entites.checklist.DataForExcelFile
import com.example.skyapartmentscleaning.generateFileReportToInternalStorage
import com.example.skycleaning.data.entity.dailyСleaningOfTheApartment.CleaningApart
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream

class GenerateReport : IGenerateReport {

    override fun generateExcelReport(
        context: Context,
        apart: Apart?,
        cleaningApart: CleaningApart?
    ): File? {
        val newXLSFile = generateFileReportToInternalStorage(
            context,
            "Проверка: " + "${apart?.numberApart} ${apart?.checkDate}" + ".xlsx"
        )
        val fos = FileOutputStream(newXLSFile)
        val book = createBook(apart, cleaningApart)
        book.write(fos)
        fos.close()
        return newXLSFile
    }


    private fun createBook(apart: Apart?, cleaningApart: CleaningApart?): XSSFWorkbook {
        val newBook = XSSFWorkbook()
        val sheet = newBook.createSheet("Отчёт")
        val dataForSheet = apart?.let { cleaningApart?.let { it1 -> createDataMap(it, it1) } }
        val keysData = dataForSheet?.keys
        var rowId = 0

        if (keysData != null) {
            for (key in keysData) {
                val row = sheet.createRow(rowId++)
                val objectData = dataForSheet[key]
                val style = newBook.createCellStyle()
                sheet.setColumnWidth(0, 10000)
                sheet.setColumnWidth(1, 5000)
                style.wrapText = true
                val cell1 = row.createCell(0)
                cell1.cellStyle = style
                cell1.setCellValue(objectData?.checkpointName)
                val cell2 = row.createCell(1)
                cell2.cellStyle = style
                cell2.setCellValue(objectData?.resultOfChecking)
            }
        }
        return newBook
    }

    private fun createDataMap(
        apart: Apart,
        cleaningApart: CleaningApart
    ): Map<String, DataForExcelFile> {
        val mapData: Map<String, DataForExcelFile> = mapOf(
            Pair("1", DataForExcelFile(Apart.NUMBER_APARTS, apart.numberApart)),
            Pair("2", DataForExcelFile(Apart.CHECK_DATE, apart.checkDate.toString())),
            Pair(
                "3",
                DataForExcelFile(CleaningApart.BYPASSING_APART, cleaningApart.bypassingApart)
            ),
            Pair(
                "4",
                DataForExcelFile(CleaningApart.VIDEO_RECORDING, cleaningApart.videoRecording)
            ),
            Pair(
                "5",
                DataForExcelFile(CleaningApart.TEMPERATURE_MODE, cleaningApart.temperarureMode)
            ),
            Pair(
                "6",
                DataForExcelFile(CleaningApart.OPEN_WINDOW_BRIDGES, cleaningApart.openWindowBridges)
            ),
            Pair("7", DataForExcelFile(CleaningApart.FLUSH_TOILET, cleaningApart.flushToilet)),
            Pair(
                "7",
                DataForExcelFile(CleaningApart.COLLECT_GARBAGE, cleaningApart.collectGarbage)
            ),
            Pair(
                "9",
                DataForExcelFile(
                    CleaningApart.CHECK_FORGOTTEN_ITEM,
                    cleaningApart.checkforgottenItem
                )
            ),
            Pair("10", DataForExcelFile(CleaningApart.FORGOTTEN_ITEM, cleaningApart.forgottenItem)),
            Pair("11", DataForExcelFile(CleaningApart.SMART_HOME, cleaningApart.smartHome)),
            Pair("12", DataForExcelFile(CleaningApart.TV, cleaningApart.tv)),
            Pair("13", DataForExcelFile(CleaningApart.TV_CONTOLLER, cleaningApart.tvController)),
            Pair("14", DataForExcelFile(CleaningApart.REFRIGERATOR, cleaningApart.refrigerator)),
            Pair("15", DataForExcelFile(CleaningApart.LIGHTING, cleaningApart.lighting)),
            Pair(
                "16",
                DataForExcelFile(CleaningApart.BLUETOOTH_COLUMN, cleaningApart.bluetoothColumn)
            ),
            Pair(
                "17",
                DataForExcelFile(CleaningApart.OTHER_EQUIPMENT, cleaningApart.otherEquipment)
            ),
            Pair("18", DataForExcelFile(CleaningApart.WASH_DISHES, cleaningApart.washDishes)),
            Pair(
                "19",
                DataForExcelFile(CleaningApart.REMOVE_BED_LINEN, cleaningApart.removeBedLinen)
            ),
            Pair("20", DataForExcelFile(CleaningApart.MAKING_BED, cleaningApart.makingBed)),
            Pair(
                "21",
                DataForExcelFile(
                    CleaningApart.CLEANING_OF_SINKS_AND_HYGIENE_AREAS,
                    cleaningApart.cleaningOfSinksAndHygieneAreas
                )
            ),
            Pair(
                "22",
                DataForExcelFile(
                    CleaningApart.CLEANING_SHOWER_BATH,
                    cleaningApart.cleaningShowerBath
                )
            ),
            Pair(
                "23",
                DataForExcelFile(CleaningApart.CLEANING_TOILET, cleaningApart.cleaningToilet)
            ),
            Pair(
                "24",
                DataForExcelFile(
                    CleaningApart.CHANGING_TOWELS_SUPPLIES,
                    cleaningApart.changingTowelsSupplies
                )
            ),
            Pair(
                "25",
                DataForExcelFile(
                    CleaningApart.WIPE_MIRROR_AND_DOOR,
                    cleaningApart.wipeMirrorAndDoor
                )
            ),
            Pair(
                "26",
                DataForExcelFile(
                    CleaningApart.WIPE_SHELVES_FURNITURE_IN_APART,
                    cleaningApart.wipeShelvesFurnitureInApart
                )
            ),
            Pair(
                "27",
                DataForExcelFile(
                    CleaningApart.WIPE_DECOR_MIRROR_IN_APART,
                    cleaningApart.wipeDecorMirrorInApart
                )
            ),
            Pair("28", DataForExcelFile(CleaningApart.CHECK_WINDOW, cleaningApart.checkWindow)),
            Pair("29", DataForExcelFile(CleaningApart.WASH_WINDOW, cleaningApart.washWindow)),
            Pair(
                "30",
                DataForExcelFile(
                    CleaningApart.TOP_GUEST_ACCESSORIES,
                    cleaningApart.topGuestAccessries
                )
            ),
            Pair("31", DataForExcelFile(CleaningApart.REMOVE_FLOOR, cleaningApart.removeFloor)),
            Pair(
                "32",
                DataForExcelFile(CleaningApart.CLEANING_COMMENT, cleaningApart.cleaningComment)
            )
        )
        return mapData
    }


}