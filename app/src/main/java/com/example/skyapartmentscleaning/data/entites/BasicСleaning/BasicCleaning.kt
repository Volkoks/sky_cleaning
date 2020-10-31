package com.example.skycleaning.data.entity.BasicСleaning

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BasicCleaning(
    @PrimaryKey
    var id: Int? = null,

    @ColumnInfo(name = CLEANING_FLOOR)
    var cleaningFloor: Boolean? = null,

    @ColumnInfo(name = CLEANING_WALLS)
    var cleaningWalls: Boolean? = null,

    @ColumnInfo(name = CLEANING_CEILING)
    var cleaningCeiling: Boolean? = null,

    @ColumnInfo(name = CLEANING_WINDOW)
    var cleaningWindow: Boolean? = null,

    @ColumnInfo(name = DESCRIPTION_OF_DAMAGE_FLOOR)
    var descriptionOfDamageFloor: String = "",

    @ColumnInfo(name = DESCRIPTION_OF_DAMAGE_WALLS)
    var descriptionOfDamageWalls: String = "",

    @ColumnInfo(name = DESCRIPTION_OF_DAMAGE_CEILING)
    var descriptionOfDamageCeiling: String = "",

    @ColumnInfo(name = DESCRIPTION_OF_DAMAGE_WINDOW)
    var descriptionOfDamageWindow: String = "",
    @ColumnInfo(name = DECOR)
    var decor: String = "",
    @ColumnInfo(name = DESCRIPTION_OF_DAMAGE_DECOR)
    var descriptionOfDemageDecor:String = ""
) {
    companion object {
        const val CLEANING_FLOOR = "Чистота полов?"
        const val CLEANING_WALLS = "Чистота стен?"
        const val CLEANING_CEILING = "Чистота потолка?"
        const val CLEANING_WINDOW = "Чистота окон?"
        const val DESCRIPTION_OF_DAMAGE_FLOOR = "Повреждение пола"
        const val DESCRIPTION_OF_DAMAGE_WALLS = "Поареждение стен"
        const val DESCRIPTION_OF_DAMAGE_CEILING = "Повреждение потолка"
        const val DESCRIPTION_OF_DAMAGE_WINDOW = "Повреждение окон"
        const val DECOR = "Декор"
        const val DESCRIPTION_OF_DAMAGE_DECOR = "Повреждение декора"


    }
}
