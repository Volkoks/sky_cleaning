package com.example.skyapartmentscleaning.ui.adapter


interface IItemChekListListener {
    fun clickChip(textPlain: String, idChip: String)
    fun sendTextEditText(hint: String, text: String)
    fun sendReport()
}