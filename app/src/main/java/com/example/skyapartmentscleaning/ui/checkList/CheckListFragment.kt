package com.example.skyapartmentscleaning.ui.checkList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skyapartmentscleaning.R.layout
import com.example.skyapartmentscleaning.R.string
import com.example.skyapartmentscleaning.data.*
import com.example.skyapartmentscleaning.data.entites.apart.Apart
import com.example.skyapartmentscleaning.databinding.CheckListFforRvFragmentBinding
import com.example.skyapartmentscleaning.ui.adapter.CheckListApartAdapter
import com.example.skyapartmentscleaning.ui.adapter.IClickChipItemCheckList
import com.example.skycleaning.data.entity.dailyСleaningOfTheApartment.CleaningApart

class CheckListFragment : Fragment(layout.check_list_ffor_rv_fragment), IClickChipItemCheckList {

    companion object {
        fun newInstance(apart: Apart, cleaningApart: CleaningApart) = CheckListFragment().apply {
            arguments = Bundle().apply {
                putParcelable(APART, apart)
                putParcelable(CLEANING_APART, cleaningApart)
            }
        }
    }

    private var apart: Apart? = null
    private var cleaningApart: CleaningApart? = null
    private var binding: CheckListFforRvFragmentBinding? = null
    private val viewModel: CheckListViewModel by viewModels()
    private var adapter: CheckListApartAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CheckListFforRvFragmentBinding.bind(view)

        apart = arguments?.getParcelable(APART)
        cleaningApart = arguments?.getParcelable(CLEANING_APART)

        initRV()

        viewModel.dataForCheckList.observe(viewLifecycleOwner, {
            adapter = CheckListApartAdapter(it, this)
            binding?.rvForCheckList?.adapter = adapter
        })

        binding?.mbClForRvSendReport?.setOnClickListener {
            viewModel.saveApartCleaningReport(apart, cleaningApart)
            activity?.let { it1 -> viewModel.generateCSVFileAndSend(it1, apart, cleaningApart) }
            Toast.makeText(activity, "Отчёт сохранен", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initRV() {
//        binding?.rvForCheckList?.setHasFixedSize(true)
        binding?.rvForCheckList?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    override fun clickChip(textPlain: String, idChip: String) {
        when (textPlain) {
            getString(string.bypassing_the_apartment_the_degree_of_pollution) -> when (idChip) {
                DONE_DAW -> cleaningApart?.bypassingApart = DIRTY
                NOT_DONE_CROSS -> cleaningApart?.bypassingApart = NORMAL
            }
            getString(string.make_a_video_recording_of_the_apartment) -> when (idChip) {
                DONE_DAW -> cleaningApart?.videoRecording = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.videoRecording = NOT_DONE_CROSS
            }
            getString(string.adjust_the_temperature_regime_in_the_apartment_checking) -> when (idChip) {
                DONE_DAW -> cleaningApart?.temperarureMode = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.temperarureMode = NOT_DONE_CROSS
            }
            getString(string.open_bridges_windows_during_the_warm_season) -> when (idChip) {
                DONE_DAW -> cleaningApart?.openWindowBridges = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.openWindowBridges = NOT_DONE_CROSS
            }
            getString(string.flush_toilet) -> when (idChip) {
                DONE_DAW -> cleaningApart?.flushToilet = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.flushToilet = NOT_DONE_CROSS
            }
            getString(string.collect_all_garbage_in_the_apartment) -> when (idChip) {
                DONE_DAW -> cleaningApart?.collectGarbage = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.collectGarbage = NOT_DONE_CROSS
            }
            getString(string.check_forgottem_item) -> when (idChip) {
                DONE_DAW -> cleaningApart?.forgottenItem = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.forgottenItem = NOT_DONE_CROSS
            }
            getString(string.smart_home_operation_if_available) -> when (idChip) {
                DONE_DAW -> cleaningApart?.smartHome = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.smartHome = NOT_DONE_CROSS
            }
            getString(string.tv) -> when (idChip) {
                DONE_DAW -> cleaningApart?.tv = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.tv = NOT_DONE_CROSS
            }
            getString(string.remote_contrller) -> when (idChip) {
                DONE_DAW -> cleaningApart?.tvController = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.tvController = NOT_DONE_CROSS
            }
            getString(string.refrigerator) -> when (idChip) {
                DONE_DAW -> cleaningApart?.refrigerator = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.refrigerator = NOT_DONE_CROSS
            }
            getString(string.lighting) -> when (idChip) {
                DONE_DAW -> cleaningApart?.lighting = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.lighting = NOT_DONE_CROSS
            }
            getString(string.bluetooth_column) -> when (idChip) {
                DONE_DAW -> cleaningApart?.bluetoothColumn = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.bluetoothColumn = NOT_DONE_CROSS
            }
            getString(string.other_equipment) -> when (idChip) {
                DONE_DAW -> cleaningApart?.otherEquipment = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.otherEquipment = NOT_DONE_CROSS
            }
            getString(string.collect_and_wash_dishes) -> when (idChip) {
                DONE_DAW -> cleaningApart?.washDishes = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.washDishes = NOT_DONE_CROSS
            }
            getString(string.collect_and_remove_bed_linen) -> when (idChip) {
                DONE_DAW -> cleaningApart?.removeBedLinen = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.removeBedLinen = NOT_DONE_CROSS
            }
            getString(string.making_bed) -> when (idChip) {
                DONE_DAW -> cleaningApart?.makingBed = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.makingBed = NOT_DONE_CROSS
            }
            getString(string.cleaning_sinks_tiles_hygiene_areas_walls_and_bathroom_doors) -> when (idChip) {
                DONE_DAW -> cleaningApart?.cleaningOfSinksAndHygieneAreas = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.cleaningOfSinksAndHygieneAreas = NOT_DONE_CROSS
            }
            getString(string.shower_bath_cleaning) -> when (idChip) {
                DONE_DAW -> cleaningApart?.cleaningShowerBath = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.cleaningShowerBath = NOT_DONE_CROSS
            }
            getString(string.cleaning_toilet) -> when (idChip) {
                DONE_DAW -> {
                    tostTest("flush toilet")
                    cleaningApart?.cleaningToilet = DONE_DAW
                }
                NOT_DONE_CROSS -> cleaningApart?.cleaningToilet = NOT_DONE_CROSS
            }
            getString(string.changing_towels_and_supplies) -> when (idChip) {
                DONE_DAW -> cleaningApart?.changingTowelsSupplies = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.changingTowelsSupplies = NOT_DONE_CROSS
            }
            getString(string.wipe_down_the_mirror_and_bathroom_door) -> when (idChip) {
                DONE_DAW -> cleaningApart?.wipeMirrorAndDoor = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.wipeMirrorAndDoor = NOT_DONE_CROSS
            }
            getString(string.wipe_the_shelves_furniture_in_the_apartment) -> when (idChip) {
                DONE_DAW -> cleaningApart?.wipeShelvesFurnitureInApart = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.wipeShelvesFurnitureInApart = NOT_DONE_CROSS
            }
            getString(string.wipe_down_the_mirrors_and_decor_in_the_apartment) -> when (idChip) {
                DONE_DAW -> cleaningApart?.wipeDecorMirrorInApart = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.wipeDecorMirrorInApart = NOT_DONE_CROSS
            }
            getString(string.check_window) -> when (idChip) {
                DONE_DAW -> cleaningApart?.checkWindow = DIRTY
                NOT_DONE_CROSS -> {
                    cleaningApart?.checkWindow = NOT_DONE_CROSS
                    cleaningApart?.washWindow = NOT_DONE_CROSS
                }
            }
            getString(string.wash_windows_wash_if_necessary) -> when (idChip) {
                DONE_DAW -> cleaningApart?.washWindow = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.washWindow = NOT_DONE_CROSS
            }
            getString(string.wipe_the_shelves_furniture_in_the_apartment) -> when (idChip) {
                DONE_DAW -> cleaningApart?.wipeShelvesFurnitureInApart = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.wipeShelvesFurnitureInApart = NOT_DONE_CROSS
            }
            getString(string.top_up_the_guest_accessories_of_the_apartment) -> when (idChip) {
                DONE_DAW -> cleaningApart?.topGuestAccessries = DONE_DAW
                NOT_DONE_CROSS -> cleaningApart?.topGuestAccessries = NOT_DONE_CROSS
            }
            getString(string.remove_the_floor_vacuum_wash) -> when (idChip) {
                DONE_DAW -> cleaningApart?.removeFloor = DONE_DAW
                NOT_DONE_CROSS -> {
                    tostTest("floor")
                    cleaningApart?.removeFloor = NOT_DONE_CROSS
                }
            }
        }

    }

    private fun tostTest(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }
}