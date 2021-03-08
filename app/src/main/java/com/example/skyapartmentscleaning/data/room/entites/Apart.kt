package com.example.skyapartmentscleaning.data.room.entites

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * @author Alexander Volkov (Volkoks)
 */
@Parcelize
@Entity(tableName = "apart")
data class Apart(
    @ColumnInfo
    var numberApart: String,
    @ColumnInfo
    var checkDate: String? = null,
    @PrimaryKey
    var id: String = ""

) : Parcelable