package com.example.skyapartmentscleaning.data

import com.example.skyapartmentscleaning.data.checklist.DataPointCheckList
import com.example.skyapartmentscleaning.data.hystory_checklist.HistoryChecklistPoint
import com.example.skyapartmentscleaning.data.room.entites.Apart

/**
 * @author Alexander Volkov (Volkoks)
 */
sealed class ViewState {
    data class SuccesApart(val listApart: List<Apart>) : ViewState()
    data class SuccesDataCheckList(val dataCheckList: MutableList<DataPointCheckList>) : ViewState()
    data class SuccesDataHistoryCheckList(val dataHistoryChecklist: List<HistoryChecklistPoint>) :
        ViewState()

    data class Loading(val progress: Int?) : ViewState()
    data class Error(val e: Throwable) : ViewState()

}