package com.example.skyapartmentscleaning.data.repository

import com.example.skyapartmentscleaning.application.MyApp
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.data.*
import com.example.skyapartmentscleaning.data.checklist.points.CheckListHeadingPoint
import com.example.skyapartmentscleaning.data.checklist.points.CheckListEntryFieldPoint
import com.example.skyapartmentscleaning.data.checklist.points.CheckListCheckPoint
import com.example.skyapartmentscleaning.data.checklist.DataPointCheckList
import com.example.skyapartmentscleaning.data.checklist.points.CheckListBtnPoint

class CheckListPointRespository : IRepository<MutableList<DataPointCheckList>> {

    private val context = MyApp.instance

    private val listDataCheckList: MutableList<DataPointCheckList> = mutableListOf(
        DataPointCheckList(
            VT_HEADING_POINT,
            dataHeadingPoint = CheckListHeadingPoint(getTextToRes(R.string.cleaning_start))
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.bypassing_the_apartment_the_degree_of_pollution),
                getTextToRes(R.string.dirty), getTextToRes(R.string.normal)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.make_a_video_recording_of_the_apartment),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.adjust_the_temperature_regime_in_the_apartment_checking),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT,
            dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.open_bridges_windows_during_the_warm_season),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT,
            dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.flush_toilet),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT,
            dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.collect_all_garbage_in_the_apartment),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.check_forgottem_item),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT_ENTRY_FIELD,
            dataEntryFieldPoint = CheckListEntryFieldPoint(getTextToRes(R.string.forgotten_things))
        ),
        //        Техническая часть аппартамента
        DataPointCheckList(
            VT_HEADING_POINT,
            dataHeadingPoint = CheckListHeadingPoint(getTextToRes(R.string.the_technical_part_of_the_apartment))
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.smart_home_operation_if_available),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.tv),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.remote_contrller),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.refrigerator),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.lighting),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.bluetooth_column),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.other_equipment),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        //        Уборка ванной комнатыz
        DataPointCheckList(
            VT_HEADING_POINT,
            dataHeadingPoint = CheckListHeadingPoint(getTextToRes(R.string.bathroom_cleaning))
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.collect_and_wash_dishes),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.collect_and_remove_bed_linen),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.making_bed),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT,
            dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.cleaning_sinks_tiles_hygiene_areas_walls_and_bathroom_doors),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.shower_bath_cleaning),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.cleaning_toilet),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.changing_towels_and_supplies),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.wipe_down_the_mirror_and_bathroom_door),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),

        //      Уборка аппартамента
        DataPointCheckList(
            VT_HEADING_POINT,
            dataHeadingPoint = CheckListHeadingPoint(getTextToRes(R.string.cleaning_of_the_apartment))
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.wipe_the_shelves_furniture_in_the_apartment),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.wipe_down_the_mirrors_and_decor_in_the_apartment),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.check_window),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.wash_windows_wash_if_necessary),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT,
            dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.top_up_the_guest_accessories_of_the_apartment),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),
        DataPointCheckList(
            VT_POINT, dataCheckPoint = CheckListCheckPoint(
                getTextToRes(R.string.remove_the_floor_vacuum_wash),
                getTextToRes(R.string.yes_daw),
                getTextToRes(R.string.no_cross)
            )
        ),

        DataPointCheckList(
            VT_POINT_ENTRY_FIELD,
            dataEntryFieldPoint = CheckListEntryFieldPoint(getTextToRes(R.string.commentaries))
        ),
        DataPointCheckList(
            VT_BTN,
            dataBtnPoint = CheckListBtnPoint(getTextToRes(R.string.send_report))
        ),
        DataPointCheckList(VT_PLUG)
    )

    private fun getTextToRes(resId: Int): String = context.getString(resId)

    override fun getData(): MutableList<DataPointCheckList> = listDataCheckList


}