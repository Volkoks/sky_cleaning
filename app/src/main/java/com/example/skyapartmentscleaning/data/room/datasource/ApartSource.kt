package com.example.skyapartmentscleaning.data.room.datasource

import com.example.skyapartmentscleaning.data.room.dao.ApartDao
import com.example.skyapartmentscleaning.data.room.entites.Apart

/**
 * @author Alexander Volkov (Volkoks)
 */
class ApartSource(apartDao: ApartDao) {
    private val apartDao: ApartDao = apartDao
    private var apartList: List<Apart>? = null

    fun loadApart() {
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

    fun getApartById(id: String): Apart {
        return apartDao.getApartById(id)
    }

}