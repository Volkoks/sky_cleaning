package com.example.skycleaning.data.repository

import com.example.skyapartmentscleaning.data.room.entites.Apart

/**
 * @author Alexander Volkov (Volkoks)
 */

class ApartsRepository {

    fun getTowerFederation(): List<Apart> = listApartsTowerFederation
    fun getTowerOKO(): List<Apart> = listApartsTowerOKO
    fun getTowerImpery(): List<Apart> = listApartTowerImpery
    fun getToweGorodStolic(): List<Apart> = listApartTowerGorodStolic

    private var listApartsTowerFederation: List<Apart> = listOf(
        Apart("492"),
        Apart("495"),
        Apart("496"),
        Apart("497"),
        Apart("498"),
        Apart("5010"),
        Apart("5001"),
        Apart("5002")
    )
    private var listApartsTowerOKO: List<Apart> = listOf(
        Apart("2307"),
        Apart("4502"),
        Apart("6302а"),
        Apart("6302в"),
        Apart("6401а"),
        Apart("6401в")
    )
    private var listApartTowerImpery: List<Apart> = listOf(
        Apart("5703"),
        Apart("5707"),
    )
    private var listApartTowerGorodStolic: List<Apart> = listOf(
        Apart("444м"),
        Apart("243с")
    )
}