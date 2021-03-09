package com.example.skyapartmentscleaning.data.datasource

interface IDataSource<T> {
    fun getData(apartId:String): T
}