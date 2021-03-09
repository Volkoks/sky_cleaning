package com.example.skyapartmentscleaning.utils.generate_report

import android.content.Context
import com.example.skyapartmentscleaning.data.room.entites.Apart
import com.example.skyapartmentscleaning.data.room.entites.CleaningApart
import java.io.File

interface IGenerateReport {
    fun generateExcelReport(context: Context, apart: Apart?, cleaningApart: CleaningApart?):File?
}