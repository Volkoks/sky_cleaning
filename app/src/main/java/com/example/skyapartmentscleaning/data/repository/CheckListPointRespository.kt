package com.example.skyapartmentscleaning.data.repository

import com.example.skyapartmentscleaning.MyApp
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.data.VT_HEADING_POINT
import com.example.skyapartmentscleaning.data.VT_PLUG
import com.example.skyapartmentscleaning.data.VT_POINT
import com.example.skyapartmentscleaning.data.entites.checklist.CheckListHeadingPoints
import com.example.skyapartmentscleaning.data.entites.checklist.CheckListPoint
import com.example.skyapartmentscleaning.data.entites.checklist.DataCheckList

object CheckListPointRespository {

    private val context = MyApp.instance

    private val listDataCheckList = listOf(
        DataCheckList(
            VT_HEADING_POINT,
            dataHeadingPoints = CheckListHeadingPoints(getTextToRes(R.string.cleaning_start))
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.bypassing_the_apartment_the_degree_of_pollution),
                getTextToRes(R.string.dirty), getTextToRes(R.string.normal)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.make_a_video_recording_of_the_apartment),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.adjust_the_temperature_regime_in_the_apartment_checking),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT,
            dataPoint = CheckListPoint(
                getTextToRes(R.string.open_bridges_windows_during_the_warm_season),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT,
            dataPoint = CheckListPoint(
                getTextToRes(R.string.flush_toilet),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT,
            dataPoint = CheckListPoint(
                getTextToRes(R.string.collect_all_garbage_in_the_apartment),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.check_forgottem_item),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        //        Техническая часть аппартамента
        DataCheckList(
            VT_HEADING_POINT,
            dataHeadingPoints = CheckListHeadingPoints(getTextToRes(R.string.the_technical_part_of_the_apartment))
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.smart_home_operation_if_available),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.tv),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.remote_contrller),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.refrigerator),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.lighting),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.bluetooth_column),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.other_equipment),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        //        Уборка ванной комнатыz
        DataCheckList(
            VT_HEADING_POINT,
            dataHeadingPoints = CheckListHeadingPoints(getTextToRes(R.string.bathroom_cleaning))
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.collect_and_wash_dishes),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.collect_and_remove_bed_linen),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.making_bed),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT,
            dataPoint = CheckListPoint(
                getTextToRes(R.string.cleaning_sinks_tiles_hygiene_areas_walls_and_bathroom_doors),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.shower_bath_cleaning),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.cleaning_toilet),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.changing_towels_and_supplies),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.check_forgottem_item),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),

        //      Уборка аппартамента
        DataCheckList(
            VT_HEADING_POINT,
            dataHeadingPoints = CheckListHeadingPoints(getTextToRes(R.string.cleaning_of_the_apartment))
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.wipe_the_shelves_furniture_in_the_apartment),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.wipe_down_the_mirrors_and_decor_in_the_apartment),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.check_window),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.wash_windows_wash_if_necessary),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT,
            dataPoint = CheckListPoint(
                getTextToRes(R.string.top_up_the_guest_accessories_of_the_apartment),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(
            VT_POINT, dataPoint = CheckListPoint(
                getTextToRes(R.string.remove_the_floor_vacuum_wash),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataCheckList(VT_PLUG)
    )

    private fun getTextToRes(resId: Int): String = context.getString(resId)

    fun getListDataForCheckList() = listDataCheckList
}