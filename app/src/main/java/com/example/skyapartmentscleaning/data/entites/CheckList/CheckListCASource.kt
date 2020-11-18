package com.example.skyapartmentscleaning.data.entites.CheckList

import com.example.skycleaning.data.entity.dailyСleaningOfTheApartment.CheckListCleaningApart

class CheckListCASource(val checkListCADao: CheckListCADao) {
    private var checkListCleaningApartLists: List<CheckListCleaningApart>? = null
private var checkList:CheckListCleaningApart? = null

    fun loadData() {
        checkListCleaningApartLists = checkListCADao?.getAllCheckLists()
    }
    fun loadListCheckList(): List<CheckListCleaningApart>? {
        loadData()
        return checkListCleaningApartLists
    }
    fun addCheckList(checkListCleaningApart: CheckListCleaningApart){
        checkListCADao.addCheckList(checkListCleaningApart)
        loadData()
    }
    fun deleteCheckList(checkListCleaningApart: CheckListCleaningApart){
        checkListCADao.deleteCheckList(checkListCleaningApart)
        loadData()
    }
    fun getCheckListById(id:String):CheckListCleaningApart?{
        checkList = checkListCADao.getCAById(id)
        return checkList
    }



}