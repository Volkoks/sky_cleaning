package com.example.skyapartmentscleaning.data.repository

import com.example.skyapartmentscleaning.data.datasource.IDataSource
import com.example.skyapartmentscleaning.data.hystory_checklist.HistoryChecklistPoint
import javax.inject.Inject

class HistoryChecklistRepository @Inject constructor(
    val localData: IDataSource<List<HistoryChecklistPoint>>
) : IRepositoryHistory<List<HistoryChecklistPoint>> {
    override fun getData(apartId: String): List<HistoryChecklistPoint> {
        return localData.getData(apartId)
    }
}