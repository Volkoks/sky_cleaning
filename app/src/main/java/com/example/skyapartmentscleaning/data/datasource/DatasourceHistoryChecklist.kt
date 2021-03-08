package com.example.skyapartmentscleaning.data.datasource

import com.example.skyapartmentscleaning.MyApp
import com.example.skyapartmentscleaning.data.hystory_checklist.DataForHistoryChecklist
import com.example.skyapartmentscleaning.data.hystory_checklist.HistoryChecklistPoint
import com.example.skyapartmentscleaning.data.room.database.ApartDatabase

class DatasourceHistoryChecklist(
    private val db: ApartDatabase = MyApp.instance.getDB
) : IDataSource<List<HistoryChecklistPoint>> {

    override fun getData(apartId: String): List<HistoryChecklistPoint> {
        val date = DataForHistoryChecklist(
            db.getApartDao().getApartById(apartId),
            db.getCleaningApartDao().getApartById(apartId)
        )
        return date.getHystoryChecklist()
    }
}