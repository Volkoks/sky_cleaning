package com.example.skyapartmentscleaning.data.entites.checklist

import com.example.skyapartmentscleaning.data.entites.checklist.points.CheckListBtnPoint
import com.example.skyapartmentscleaning.data.entites.checklist.points.CheckListHeadingPoint
import com.example.skyapartmentscleaning.data.entites.checklist.points.CheckListEntryFieldPoint
import com.example.skyapartmentscleaning.data.entites.checklist.points.CheckListCheckPoint

data class DataPointCheckList(
    val viewType: Int,
    val dataCheckPoint: CheckListCheckPoint? = null,
    val dataHeadingPoint: CheckListHeadingPoint? = null,
    val dataEntryFieldPoint: CheckListEntryFieldPoint? = null,
    val dataBtnPoint: CheckListBtnPoint? = null

)