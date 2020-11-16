package com.example.skyapartmentscleaning.data.entites.apart

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * @author Alexander Volkov (Volkoks)
 */
@Parcelize
@Entity(tableName = "apart")
data class Apart(
    @ColumnInfo(name = NUMBER_APARTS)
    var numberApart: String,
    @ColumnInfo(name = CHECK_DATE)
    var checkDate: String = "",
    @PrimaryKey
    var id: String =""

) : Parcelable {
    companion object {
        const val NUMBER_APARTS = "номер апартаментов"
        const val CHECK_DATE = "дата проверки"
    }
}