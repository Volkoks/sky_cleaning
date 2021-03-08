package com.example.skyapartmentscleaning.data.repository

import com.example.skyapartmentscleaning.data.datasource.DatasourceHistoryChecklist
import com.example.skyapartmentscleaning.data.datasource.IDataSource
import com.example.skyapartmentscleaning.data.hystory_checklist.HistoryChecklistPoint

class HistoryChecklistRepository(
    private val localData: IDataSource<List<HistoryChecklistPoint>> = DatasourceHistoryChecklist()
) : IRepositoryHistory<List<HistoryChecklistPoint>> {
    override fun getData(apartId: String): List<HistoryChecklistPoint> {
        return localData.getData(apartId)
    }
}