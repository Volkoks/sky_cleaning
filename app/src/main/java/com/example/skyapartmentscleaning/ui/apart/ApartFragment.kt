package com.example.skyapartmentscleaning.ui.apart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.data.DIRTY
import com.example.skyapartmentscleaning.data.DONE
import com.example.skyapartmentscleaning.data.NORMAL
import com.example.skyapartmentscleaning.data.NOT_DONE
import com.example.skyapartmentscleaning.data.entites.apart.Apart
import com.example.skycleaning.data.entity.dailyСleaningOfTheApartment.CleaningApart
import kotlinx.android.synthetic.main.apart_fragment.*


class ApartFragment : Fragment() {

    companion object {
        fun newInstance(apart: Apart): ApartFragment {
            val fragment = ApartFragment()
            var cleaningApart = CleaningApart()
            val bundle = Bundle()
            bundle.putParcelable("apart", apart)
            bundle.putParcelable("cleaningApart", cleaningApart)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var apart: Apart? = null
    private var cleaningApart: CleaningApart? = null
    private val viewModel: ApartViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.apart_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apart = arguments?.getParcelable("apart")
        cleaningApart = arguments?.getParcelable("cleaningApart")
        number_apart_fragment.setText(apart?.numberApart.toString())
        date_test.setText(viewModel.getCurrentFormattedDate(apart))

        handlingRadioBtnClicks()

        save_apart_btn.setOnClickListener {
            cleaningApart?.cleaningComment = commentaries_editTextTextMultiLine.text.toString()
            viewModel.saveApartCleaningReport(apart, cleaningApart)
            activity?.let { it1 -> viewModel.generateCSVFileAndSend(it1, apart) }
            Toast.makeText(activity, "СОХРАНИЛИ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handlingRadioBtnClicks() {
        bypassing_apart_radiogroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when (checkedId) {
                R.id.yes_bypassing_apart_radioButton -> {
                    cleaningApart?.bypassingApart = DIRTY
                    video_recording_linear_layout.layoutParams = visibleLayout()
                }
                R.id.no_bypassing_apart_radioButton -> {
                    cleaningApart?.bypassingApart = NORMAL
                    cleaningApart?.videoRecording = "-"
                    video_recording_linear_layout.layoutParams =
                        invisibleLayout()
                }
            }
        }
        make_a_video_recording_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_make_a_video_recording_radioButton -> cleaningApart?.videoRecording = DONE
                R.id.no_make_a_video_recording_radioButton -> cleaningApart?.videoRecording =
                    NOT_DONE
            }
        }
        temperature_regime_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_temperature_regime_radioButton -> cleaningApart?.temperarureMode = DONE
                R.id.no_temperature_regime_radioButton -> cleaningApart?.temperarureMode = NOT_DONE
            }
        }
        open_window_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_open_window_radioButton -> cleaningApart?.openWindowBridges = DONE
                R.id.no_open_window_radioButton -> cleaningApart?.openWindowBridges = NOT_DONE
            }
        }
        flush_toilet_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_flush_toilet_radioButton -> cleaningApart?.flushToilet = DONE
                R.id.no_flush_toilet_radioButton -> cleaningApart?.flushToilet = NOT_DONE
            }
        }
        collect_all_garbage_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_collect_all_garbage_radioButton -> cleaningApart?.collectGarbage = DONE
                R.id.no_collect_all_garbage_radioButton -> cleaningApart?.collectGarbage = NOT_DONE
            }
        }
        smart_home_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_smart_home_radioButton -> cleaningApart?.smartHome = DONE
                R.id.no_smart_home_radioButton -> cleaningApart?.smartHome = NOT_DONE
            }
        }
        tv_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_tv_radioButton -> cleaningApart?.tv = DONE
                R.id.no_tv_radioButton -> cleaningApart?.tv = NOT_DONE
            }
        }
        remote_controller_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_remote_controller_radioButton -> cleaningApart?.tvController = DONE
                R.id.no_remote_controller_radioButton -> cleaningApart?.tvController = NOT_DONE
            }
        }
        refrigerator_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_refrigerator_radioButton -> cleaningApart?.regrigerator = DONE
                R.id.no_refrigerator_radioButton -> cleaningApart?.regrigerator = NOT_DONE
            }
        }
        lighting_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_lighting_radioButton -> cleaningApart?.lighting = DONE
                R.id.no_lighting_radioButton -> cleaningApart?.lighting = NOT_DONE
            }
        }
        bluetooth_column_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_bluetooth_column_radioButton -> cleaningApart?.bluetoothColumn = DONE
                R.id.no_bluetooth_column_radioButton -> cleaningApart?.bluetoothColumn = NOT_DONE
            }
        }
        other_equipment_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_other_equipment_radioButton -> cleaningApart?.otherEquipment = DONE
                R.id.no_other_equipment_radioButton -> cleaningApart?.otherEquipment = NOT_DONE
            }
        }
        wash_dishes_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wash_dishes_radioButton -> cleaningApart?.washDishes = DONE
                R.id.no_wash_dishes_radioButton -> cleaningApart?.washDishes = NOT_DONE
            }
        }
        remove_bed_linen_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_remove_bed_linen_radioButton -> cleaningApart?.removeBedLinen = DONE
                R.id.no_remove_bed_linen_radioButton -> cleaningApart?.removeBedLinen = NOT_DONE
            }
        }
        making_bed_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_making_bed_radioButton -> cleaningApart?.makingBed = DONE
                R.id.no_making_bed_radioButton -> cleaningApart?.makingBed = NOT_DONE
            }
        }
        cleaning_hygiene_areas_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_cleaning_hygiene_areas_radioButton -> cleaningApart?.cleaningOfSinksAndHygieneAreas =
                    DONE
                R.id.no_cleaning_hygiene_areas_radioButton -> cleaningApart?.cleaningOfSinksAndHygieneAreas =
                    NOT_DONE
            }
        }
        cleaning_shower_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_cleaning_shower_radioButton -> cleaningApart?.cleaningShowerBath = DONE
                R.id.no_cleaning_shower_radioButton -> cleaningApart?.cleaningShowerBath = NOT_DONE
            }
        }
        cleaning_toilet_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_cleaning_toilet_radioButton -> cleaningApart?.cleaningToilet = DONE
                R.id.no_cleaning_toilet_radioButton -> cleaningApart?.cleaningToilet = NOT_DONE
            }
        }
        changing_towels_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_changing_towels_radioButton -> cleaningApart?.changingTowelsSupplies = DONE
                R.id.no_changing_towels_radioButton -> cleaningApart?.changingTowelsSupplies =
                    NOT_DONE
            }
        }
        wipe_mirror_and_dooor_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wipe_mirror_and_dooor_radioButton -> cleaningApart?.wipeMirrorAndDoor =
                    DONE
                R.id.no_wipe_mirror_and_dooor_radioButton -> cleaningApart?.wipeMirrorAndDoor =
                    NOT_DONE
            }
        }
        wipe_shelves_furniture_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wipe_shelves_furniture_radioButton -> cleaningApart?.wipeShelvesFurnitureInApart =
                    DONE
                R.id.no_wipe_shelves_furniture_radioButton -> cleaningApart?.wipeShelvesFurnitureInApart =
                    NOT_DONE
            }
        }
        wipe_decor_mirror_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wipe_decor_mirror_radioButton -> cleaningApart?.wipeDecorMirrorInApart =
                    DONE
                R.id.no_wipe_decor_mirror_radioButton -> cleaningApart?.wipeDecorMirrorInApart =
                    NOT_DONE
            }
        }
        check_window_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_check_window_radioButton -> {
                    cleaningApart?.checkWindow = DIRTY
                    wash_window_layout_container.layoutParams = visibleLayout()
                }
                R.id.no_check_window_radioButton -> {
                    cleaningApart?.checkWindow = NORMAL
                    cleaningApart?.washWindow = "-"
                    wash_window_layout_container.layoutParams = invisibleLayout()
                }
            }
        }
        top_guest_accessories_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_top_guest_accessories_radioButton -> cleaningApart?.topGuestAccessries =
                    DONE
                R.id.no_top_guest_accessories_radioButton -> cleaningApart?.topGuestAccessries =
                    NOT_DONE
            }
        }
        remove_floor_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_remove_floor_radioButton -> cleaningApart?.removeFloor = DONE
                R.id.no_remove_floor_radioButton -> cleaningApart?.removeFloor = NOT_DONE
            }
        }
    }

    private fun invisibleLayout() =
        LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0)

    private fun visibleLayout(): ViewGroup.LayoutParams? {
        return LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onPause() {
        apart?.checkDate = ""
        super.onPause()
    }

}