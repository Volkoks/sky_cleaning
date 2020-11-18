package com.example.skyapartmentscleaning.data.entites.apart
/**
 * @author Alexander Volkov (Volkoks)
 */
class ApartSource(val apartDao: ApartDao) {
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
            loadApart()
        return apartList
    }

}