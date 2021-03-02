package com.example.skyapartmentscleaning.data.repository

interface IRepository<T> {
    fun getList(): T
}