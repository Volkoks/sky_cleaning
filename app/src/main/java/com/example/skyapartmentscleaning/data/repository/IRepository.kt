package com.example.skyapartmentscleaning.data.repository

interface IRepository<T> {
    fun getData(): T
}