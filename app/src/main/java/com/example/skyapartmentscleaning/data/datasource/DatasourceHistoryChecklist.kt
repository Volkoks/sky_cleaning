package com.example.skyapartmentscleaning.data.datasource

import com.example.skyapartmentscleaning.data.hystory_checklist.DataForHistoryChecklist
import com.example.skyapartmentscleaning.data.hystory_checklist.HistoryChecklistPoint
import com.example.skyapartmentscleaning.data.room.database.ApartDatabase
import javax.inject.Inject

class DatasourceHistoryChecklist @Inject constructor(
    private val db: ApartDatabase
) : IDataSource<List<HistoryChecklistPoint>> {

    override fun getData(apartId: String): List<HistoryChecklistPoint> {
        val date = DataForHistoryChecklist(
            db.getApartDao().getApartById(apartId),
            db.getCleaningApartDao().getApartById(apartId)
        )
        return date.getHystoryChecklist()
    }
}