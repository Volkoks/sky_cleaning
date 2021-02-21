package com.example.skyapartmentscleaning.data.entites.checklist

data class DataPointCheckList(
    val viewType: Int,
    val dataPoint: CheckListPoint? = null,
    val dataHeadingPoints: CheckListHeadingPoints? = null,

)