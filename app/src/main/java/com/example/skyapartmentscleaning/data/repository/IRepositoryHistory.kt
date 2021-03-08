package com.example.skyapartmentscleaning.data.repository

interface IRepositoryHistory<T> {
    fun getData(apartId: String): T
}
