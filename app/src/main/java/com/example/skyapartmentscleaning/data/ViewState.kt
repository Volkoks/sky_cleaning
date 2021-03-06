package com.example.skyapartmentscleaning.data

import com.example.skyapartmentscleaning.data.entites.apart.Apart

/**
 * @author Alexander Volkov (Volkoks)
 */
sealed class ViewState {
    data class Succes(val listApart: List<Apart>) : ViewState()
    data class Loading(val progress: Int?) : ViewState()
    data class Error(val e: Throwable) : ViewState()

}