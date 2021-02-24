package com.example.skycleaning.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.skyapartmentscleaning.data.entites.apart.Apart
/**
 * @author Alexander Volkov (Volkoks)
 */

object ApartsRepository {

    var listApartsTowerFederationLevel49: List<Apart> = listOf(
        Apart("492"),
        Apart("495"),
        Apart("496"),
        Apart("497"),
        Apart("498"),
        Apart("5010"),
        Apart("5001"),
        Apart("5002")
    )
    var listApartsTowerOKO: List<Apart> = listOf(
        Apart("2307"),
        Apart("4502"),
        Apart("6302а"),
        Apart("6302в"),
        Apart("6401а"),
        Apart("6401в")
    )
    var listApartTowerImpery: List<Apart> = listOf(
        Apart("5703"),
        Apart("5707"),
    )
    var listApartTowerGorodStolic: List<Apart> = listOf(
        Apart("444м"),
        Apart("243с")
    )
}