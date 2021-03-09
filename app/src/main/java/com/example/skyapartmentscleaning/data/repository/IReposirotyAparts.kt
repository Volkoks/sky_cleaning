package com.example.skyapartmentscleaning.data.repository

import com.example.skyapartmentscleaning.data.room.entites.Apart

interface IReposirotyAparts {
    fun getTowerFederation(): List<Apart>
    fun getTowerOKO(): List<Apart>
    fun getTowerImpery(): List<Apart>
    fun getToweGorodStolic(): List<Apart>
}