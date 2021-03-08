package com.example.skyapartmentscleaning.utils.generate_report

import android.content.Context
import com.example.skyapartmentscleaning.data.room.entites.Apart
import com.example.skyapartmentscleaning.data.checklist.DataForExcelFile
import com.example.skyapartmentscleaning.data.room.ConstantApartDB
import com.example.skyapartmentscleaning.generateFileReportToInternalStorage
import com.example.skyapartmentscleaning.data.room.entites.CleaningApart
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
            Pair("1", DataForExcelFile(ConstantApartDB.NUMBER_APARTS, apart.numberApart)),
            Pair("2", DataForExcelFile(ConstantApartDB.CHECK_DATE, apart.checkDate.toString())),
            Pair(
                "3",
                DataForExcelFile(ConstantApartDB.BYPASSING_APART, cleaningApart.bypassingApart)
            ),
            Pair(
                "4",
                DataForExcelFile(ConstantApartDB.VIDEO_RECORDING, cleaningApart.videoRecording)
            ),
            Pair(
                "5",
                DataForExcelFile(ConstantApartDB.TEMPERATURE_MODE, cleaningApart.temperarureMode)
            ),
            Pair(
                "6",
                DataForExcelFile(
                    ConstantApartDB.OPEN_WINDOW_BRIDGES,
                    cleaningApart.openWindowBridges
                )
            ),
            Pair("7", DataForExcelFile(ConstantApartDB.FLUSH_TOILET, cleaningApart.flushToilet)),
            Pair(
                "7",
                DataForExcelFile(ConstantApartDB.COLLECT_GARBAGE, cleaningApart.collectGarbage)
            ),
            Pair(
                "9",
                DataForExcelFile(
                    ConstantApartDB.CHECK_FORGOTTEN_ITEM,
                    cleaningApart.checkforgottenItem
                )
            ),
            Pair(
                "10",
                DataForExcelFile(ConstantApartDB.FORGOTTEN_ITEM, cleaningApart.forgottenItem)
            ),
            Pair("11", DataForExcelFile(ConstantApartDB.SMART_HOME, cleaningApart.smartHome)),
            Pair("12", DataForExcelFile(ConstantApartDB.TV, cleaningApart.tv)),
            Pair("13", DataForExcelFile(ConstantApartDB.TV_CONTOLLER, cleaningApart.tvController)),
            Pair("14", DataForExcelFile(ConstantApartDB.REFRIGERATOR, cleaningApart.refrigerator)),
            Pair("15", DataForExcelFile(ConstantApartDB.LIGHTING, cleaningApart.lighting)),
            Pair(
                "16",
                DataForExcelFile(ConstantApartDB.BLUETOOTH_COLUMN, cleaningApart.bluetoothColumn)
            ),
            Pair(
                "17",
                DataForExcelFile(ConstantApartDB.OTHER_EQUIPMENT, cleaningApart.otherEquipment)
            ),
            Pair("18", DataForExcelFile(ConstantApartDB.WASH_DISHES, cleaningApart.washDishes)),
            Pair(
                "19",
                DataForExcelFile(ConstantApartDB.REMOVE_BED_LINEN, cleaningApart.removeBedLinen)
            ),
            Pair("20", DataForExcelFile(ConstantApartDB.MAKING_BED, cleaningApart.makingBed)),
            Pair(
                "21",
                DataForExcelFile(
                    ConstantApartDB.CLEANING_OF_SINKS_AND_HYGIENE_AREAS,
                    cleaningApart.cleaningOfSinksAndHygieneAreas
                )
            ),
            Pair(
                "22",
                DataForExcelFile(
                    ConstantApartDB.CLEANING_SHOWER_BATH,
                    cleaningApart.cleaningShowerBath
                )
            ),
            Pair(
                "23",
                DataForExcelFile(ConstantApartDB.CLEANING_TOILET, cleaningApart.cleaningToilet)
            ),
            Pair(
                "24",
                DataForExcelFile(
                    ConstantApartDB.CHANGING_TOWELS_SUPPLIES,
                    cleaningApart.changingTowelsSupplies
                )
            ),
            Pair(
                "25",
                DataForExcelFile(
                    ConstantApartDB.WIPE_MIRROR_AND_DOOR,
                    cleaningApart.wipeMirrorAndDoor
                )
            ),
            Pair(
                "26",
                DataForExcelFile(
                    ConstantApartDB.WIPE_SHELVES_FURNITURE_IN_APART,
                    cleaningApart.wipeShelvesFurnitureInApart
                )
            ),
            Pair(
                "27",
                DataForExcelFile(
                    ConstantApartDB.WIPE_DECOR_MIRROR_IN_APART,
                    cleaningApart.wipeDecorMirrorInApart
                )
            ),
            Pair("28", DataForExcelFile(ConstantApartDB.CHECK_WINDOW, cleaningApart.checkWindow)),
            Pair("29", DataForExcelFile(ConstantApartDB.WASH_WINDOW, cleaningApart.washWindow)),
            Pair(
                "30",
                DataForExcelFile(
                    ConstantApartDB.TOP_GUEST_ACCESSORIES,
                    cleaningApart.topGuestAccessries
                )
            ),
            Pair("31", DataForExcelFile(ConstantApartDB.REMOVE_FLOOR, cleaningApart.removeFloor)),
            Pair(
                "32",
                DataForExcelFile(ConstantApartDB.CLEANING_COMMENT, cleaningApart.cleaningComment)
            )
        )
        return mapData
    }


}