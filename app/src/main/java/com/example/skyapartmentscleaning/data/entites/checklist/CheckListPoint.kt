package com.example.skyapartmentscleaning.data.entites.checklist

data class CheckListPoint(
    val textPoint: String,
    val textChipYes: String? = null,
    val textChipNo: String? = null,
    var chipSelection: Int? = null

)
