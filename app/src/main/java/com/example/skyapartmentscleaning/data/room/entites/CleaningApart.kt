package com.example.skyapartmentscleaning.data.room.entites

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * @author Alexander Volkov (Volkoks)
 */
@Parcelize
@Entity(
    foreignKeys = [ForeignKey(
        entity = Apart::class,
        parentColumns = ["id"],
        childColumns = ["apartId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class CleaningApart(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo
    var bypassingApart: String = "",
    @ColumnInfo
    var videoRecording: String = "",
    @ColumnInfo
    var temperarureMode: String = "",
    @ColumnInfo
    var openWindowBridges: String = "",
    @ColumnInfo
    var flushToilet: String = "",
    @ColumnInfo
    var collectGarbage: String = "",
    @ColumnInfo
    var checkforgottenItem: String = "",
    @ColumnInfo
    var forgottenItem: String = "",
    @ColumnInfo
    var smartHome: String = "",
    @ColumnInfo
    var tv: String = "",
    @ColumnInfo
    var tvController: String = "",
    @ColumnInfo
    var refrigerator: String = "",
    @ColumnInfo
    var lighting: String = "",
    @ColumnInfo
    var bluetoothColumn: String = "",
    @ColumnInfo
    var otherEquipment: String = "",
    @ColumnInfo
    var washDishes: String = "",
    @ColumnInfo
    var removeBedLinen: String = "",
    @ColumnInfo
    var makingBed: String = "",
    @ColumnInfo
    var cleaningOfSinksAndHygieneAreas: String = "",
    @ColumnInfo
    var cleaningShowerBath: String = "",
    @ColumnInfo
    var cleaningToilet: String = "",
    @ColumnInfo
    var changingTowelsSupplies: String = "",
    @ColumnInfo
    var wipeMirrorAndDoor: String = "",
    @ColumnInfo
    var wipeShelvesFurnitureInApart: String = "",
    @ColumnInfo
    var wipeDecorMirrorInApart: String = "",
    @ColumnInfo
    var checkWindow: String = "",
    @ColumnInfo
    var washWindow: String = "",
    @ColumnInfo
    var topGuestAccessries: String = "",
    @ColumnInfo
    var removeFloor: String = "",
    @ColumnInfo
    var cleaningComment: String = "",
    @ColumnInfo
    var apartId: String? = null
) : Parcelable {

}