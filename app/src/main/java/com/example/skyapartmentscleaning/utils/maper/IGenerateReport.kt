package com.example.skyapartmentscleaning.utils.maper

import android.content.Context
import com.example.skyapartmentscleaning.data.entites.apart.Apart
import com.example.skycleaning.data.entity.dailyСleaningOfTheApartment.CleaningApart
import java.io.File

interface IGenerateReport {
    fun generateExcelReport(context: Context,apart: Apart?, cleaningApart: CleaningApart?):File?
}