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
import com.example.skyapartmentscleaning.data.*
import com.example.skyapartmentscleaning.data.entites.apart.Apart
import com.example.skycleaning.data.entity.dailyСleaningOfTheApartment.CleaningApart
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.apart_fragment.*

/**
 * @author Alexander Volkov (Volkoks)
 * Данный фрагмент отображает фрагмент чек листа уборки аппартамента - производит запись в БД, генерацию отправки
 * файла(Пока что CSV файл) в другое приложение.
 */

class ApartFragment : Fragment() {

    companion object {
        fun newInstance(apart: Apart): ApartFragment {
            val fragment = ApartFragment()
            val cleaningApart = CleaningApart()
            val bundle = Bundle()
            bundle.putParcelable(APART, apart)
            bundle.putParcelable(CLEANING_APART, cleaningApart)
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
        apart = arguments?.getParcelable(APART)
        cleaningApart = arguments?.getParcelable(CLEANING_APART)
        
        number_apart_fragment.setText(apart?.numberApart.toString())
        date_test.setText(viewModel.getCurrentFormattedDate(apart))

        handlingRadioBtnClicks()

        save_apart_btn.setOnClickListener { saveAndSendFileReport() }
    }

    /**
     * Функция сохранения и отправки файла отчёта
     */
    private fun saveAndSendFileReport() {
        cleaningApart?.cleaningComment = commentaries_editTextTextMultiLine.text.toString()
        cleaningApart?.forgottenItem = forgotten_item_editText.text.toString()
        apart?.checkDate = viewModel.getCurrentFormattedDate(apart)
        apart?.id = "${apart?.numberApart}/"+viewModel.getCurrentFormattedDate(apart)
        viewModel.saveApartCleaningReport(apart, cleaningApart)
        activity?.let { it1 -> viewModel.generateCSVFileAndSend(it1, apart,cleaningApart) }
        Toast.makeText(activity, "Отчёт сохранен", Toast.LENGTH_SHORT).show()
    }

    /**
     * Метод обработки радио-кнопок в чек листе(запись значений в локальный экземпляр CleaningApart
     */
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
                R.id.yes_make_a_video_recording_radioButton -> cleaningApart?.videoRecording = DONE_DAW
                R.id.no_make_a_video_recording_radioButton -> cleaningApart?.videoRecording =
                    NOT_DONE_CROSS
            }
        }
        temperature_regime_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_temperature_regime_radioButton -> cleaningApart?.temperarureMode = DONE_DAW
                R.id.no_temperature_regime_radioButton -> cleaningApart?.temperarureMode = NOT_DONE_CROSS
            }
        }
        open_window_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_open_window_radioButton -> cleaningApart?.openWindowBridges = DONE_DAW
                R.id.no_open_window_radioButton -> cleaningApart?.openWindowBridges = NOT_DONE_CROSS
            }
        }
        flush_toilet_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_flush_toilet_radioButton -> cleaningApart?.flushToilet = DONE_DAW
                R.id.no_flush_toilet_radioButton -> cleaningApart?.flushToilet = NOT_DONE_CROSS
            }
        }
        collect_all_garbage_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_collect_all_garbage_radioButton -> cleaningApart?.collectGarbage = DONE_DAW
                R.id.no_collect_all_garbage_radioButton -> cleaningApart?.collectGarbage = NOT_DONE_CROSS
            }
        }
        check_forgotten_item_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_check_forgotten_item_radioButton -> {
                    cleaningApart?.checkforgottenItem = AVAILABLE
                    forgotten_item_TIL.layoutParams = visibleLayout()
                }
                R.id.no_check_forgotten_item_radioButton -> {
                    cleaningApart?.checkforgottenItem =
                        NOT_AVAILABLE
                    forgotten_item_TIL.layoutParams = invisibleLayout()
                }
            }
        }

        smart_home_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_smart_home_radioButton -> cleaningApart?.smartHome = DONE_DAW
                R.id.no_smart_home_radioButton -> cleaningApart?.smartHome = NOT_DONE_CROSS
            }
        }
        tv_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_tv_radioButton -> cleaningApart?.tv = DONE_DAW
                R.id.no_tv_radioButton -> cleaningApart?.tv = NOT_DONE_CROSS
            }
        }
        remote_controller_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_remote_controller_radioButton -> cleaningApart?.tvController = DONE_DAW
                R.id.no_remote_controller_radioButton -> cleaningApart?.tvController = NOT_DONE_CROSS
            }
        }
        refrigerator_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_refrigerator_radioButton -> cleaningApart?.refrigerator = DONE_DAW
                R.id.no_refrigerator_radioButton -> cleaningApart?.refrigerator = NOT_DONE_CROSS
            }
        }
        lighting_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_lighting_radioButton -> cleaningApart?.lighting = DONE_DAW
                R.id.no_lighting_radioButton -> cleaningApart?.lighting = NOT_DONE_CROSS
            }
        }
        bluetooth_column_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_bluetooth_column_radioButton -> cleaningApart?.bluetoothColumn = DONE_DAW
                R.id.no_bluetooth_column_radioButton -> cleaningApart?.bluetoothColumn =
                    NOT_DONE_CROSS
            }
        }
        other_equipment_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_other_equipment_radioButton -> cleaningApart?.otherEquipment = DONE_DAW
                R.id.no_other_equipment_radioButton -> cleaningApart?.otherEquipment = NOT_DONE_CROSS
            }
        }
        wash_dishes_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wash_dishes_radioButton -> cleaningApart?.washDishes = DONE_DAW
                R.id.no_wash_dishes_radioButton -> cleaningApart?.washDishes = NOT_DONE_CROSS
            }
        }
        remove_bed_linen_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_remove_bed_linen_radioButton -> cleaningApart?.removeBedLinen = DONE_DAW
                R.id.no_remove_bed_linen_radioButton -> cleaningApart?.removeBedLinen = NOT_DONE_CROSS
            }
        }
        making_bed_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_making_bed_radioButton -> cleaningApart?.makingBed = DONE_DAW
                R.id.no_making_bed_radioButton -> cleaningApart?.makingBed = NOT_DONE_CROSS
            }
        }
        cleaning_hygiene_areas_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_cleaning_hygiene_areas_radioButton -> cleaningApart?.cleaningOfSinksAndHygieneAreas =
                    DONE_DAW
                R.id.no_cleaning_hygiene_areas_radioButton -> cleaningApart?.cleaningOfSinksAndHygieneAreas =
                    NOT_DONE_CROSS
            }
        }
        cleaning_shower_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_cleaning_shower_radioButton -> cleaningApart?.cleaningShowerBath = DONE_DAW
                R.id.no_cleaning_shower_radioButton -> cleaningApart?.cleaningShowerBath =
                    NOT_DONE_CROSS
            }
        }
        cleaning_toilet_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_cleaning_toilet_radioButton -> cleaningApart?.cleaningToilet = DONE_DAW
                R.id.no_cleaning_toilet_radioButton -> cleaningApart?.cleaningToilet = NOT_DONE_CROSS
            }
        }
        changing_towels_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_changing_towels_radioButton -> cleaningApart?.changingTowelsSupplies =
                    DONE_DAW
                R.id.no_changing_towels_radioButton -> cleaningApart?.changingTowelsSupplies =
                    NOT_DONE_CROSS
            }
        }
        wipe_mirror_and_dooor_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wipe_mirror_and_dooor_radioButton -> cleaningApart?.wipeMirrorAndDoor =
                    DONE_DAW
                R.id.no_wipe_mirror_and_dooor_radioButton -> cleaningApart?.wipeMirrorAndDoor =
                    NOT_DONE_CROSS
            }
        }
        wipe_shelves_furniture_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wipe_shelves_furniture_radioButton -> cleaningApart?.wipeShelvesFurnitureInApart =
                    DONE_DAW
                R.id.no_wipe_shelves_furniture_radioButton -> cleaningApart?.wipeShelvesFurnitureInApart =
                    NOT_DONE_CROSS
            }
        }
        wipe_decor_mirror_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wipe_decor_mirror_radioButton -> cleaningApart?.wipeDecorMirrorInApart =
                    DONE_DAW
                R.id.no_wipe_decor_mirror_radioButton -> cleaningApart?.wipeDecorMirrorInApart =
                    NOT_DONE_CROSS
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
        wash_window_mirror_radiogroup.setOnCheckedChangeListener{group,checkedId->
            when(checkedId){
                R.id.yes_wash_window_mirror_radioButton->cleaningApart?.washWindow = DONE_DAW
                R.id.no_wash_window_radioButton->cleaningApart?.washWindow = NOT_DONE_CROSS
            }
        }

        top_guest_accessories_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_top_guest_accessories_radioButton -> cleaningApart?.topGuestAccessries =
                    DONE_DAW
                R.id.no_top_guest_accessories_radioButton -> cleaningApart?.topGuestAccessries =
                    NOT_DONE_CROSS
            }
        }
        remove_floor_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_remove_floor_radioButton -> cleaningApart?.removeFloor = DONE_DAW
                R.id.no_remove_floor_radioButton -> cleaningApart?.removeFloor = NOT_DONE_CROSS
            }
        }
    }

    /**
     * Метод скрытия LinearLayout в чек листе(для программного скрытия)
     */
    private fun invisibleLayout() =
        LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0)

    /**
     * Метод появления LinearLayout в чек листе(для программного появления)
     */
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
