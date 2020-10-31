package com.example.skyapartmentscleaning.data.entites.apart

class ApartSource(apartDao: ApartDao) {
    private val apartDao: ApartDao = apartDao
    private var apartList: List<Apart>? = null

    fun loadApart(){
        apartList = apartDao.getAllApart()
    }

    fun addApart(apart: Apart) {
        apartDao.addApart(apart)
        loadApart()
    }

    fun deleteApart(apart: Apart) {
        apartDao.deleteApart(apart)
        loadApart()
    }

    fun getCountApart(): Long {
        return apartDao.getCountApart()
    }
    fun loadListAllApart(): List<Apart>? {
        if (apartList==null){
            loadApart()
        }
        return apartList
    }

}