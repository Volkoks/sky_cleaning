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
import com.example.skycleaning.data.entity.dailyСleaningOfTheApartment.CheckListCleaningApart
import kotlinx.android.synthetic.main.apart_fragment.*

/**
 * @author Alexander Volkov (Volkoks)
 * Данный фрагмент отображает фрагмент чек листа уборки аппартамента - производит запись в БД(через ViewModel), генерацию отправки
 * файла(Пока что CSV файл) в другое приложение.
 */

class ApartFragment : Fragment() {

    companion object {
        fun newInstance(apart: Apart): ApartFragment {
            val fragment = ApartFragment()
            val cleaningApart = CheckListCleaningApart()
            val bundle = Bundle()
            bundle.putParcelable(APART, apart)
            bundle.putParcelable(CLEANING_APART, cleaningApart)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var apart: Apart? = null
    private var checkListCleaningApart: CheckListCleaningApart? = null
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
        checkListCleaningApart = arguments?.getParcelable(CLEANING_APART)

        number_apart_fragment.setText(apart?.numberApart.toString())
        date_test.setText(viewModel.getCurrentFormattedDate(apart))

        handlingRadioBtnClicks()

        save_apart_btn.setOnClickListener { saveAndSendFileReport() }
    }

    /**
     * Функция сохранения и отправки файла отчёта
     */
    private fun saveAndSendFileReport() {
        getEditTextCheckList()
        dateFormationForApart()
        viewModel.saveApartCleaningReport(apart, checkListCleaningApart)
        activity?.let { it1 -> viewModel.generateCSVFileAndSend(it1, apart, checkListCleaningApart) }
        Toast.makeText(activity, "Отчёт сохранен", Toast.LENGTH_SHORT).show()
    }

    /**
     * Метод формирования и записи даты для экземпляра Apart.kt
     */
    private fun dateFormationForApart() {
        apart?.checkDate = viewModel.getCurrentFormattedDate(apart)
        apart?.id = "${apart?.numberApart}/" + viewModel.getCurrentFormattedDate(apart)
        checkListCleaningApart?.id = "${apart?.numberApart}/" + viewModel.getCurrentFormattedDate(apart)
    }

    /**
     * Метод получения того что написано в EditText Чек листов
     */
    private fun getEditTextCheckList() {
        checkListCleaningApart?.cleaningComment = commentaries_editTextTextMultiLine.text.toString()
        checkListCleaningApart?.forgottenItem = forgotten_item_editText.text.toString()
    }

    /**
     * Метод обработки радио-кнопок в чек листе(запись значений в локальный экземпляр CleaningApart)
     */
    private fun handlingRadioBtnClicks() {
        bypassing_apart_radiogroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when (checkedId) {
                R.id.yes_bypassing_apart_radioButton -> {
                    checkListCleaningApart?.bypassingApart = DIRTY
                    video_recording_linear_layout.layoutParams = visibleLayout()
                }
                R.id.no_bypassing_apart_radioButton -> {
                    checkListCleaningApart?.bypassingApart = NORMAL
                    checkListCleaningApart?.videoRecording = "-"
                    video_recording_linear_layout.layoutParams =
                        invisibleLayout()
                }
            }
        }
        make_a_video_recording_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_make_a_video_recording_radioButton -> checkListCleaningApart?.videoRecording =
                    DONE_DAW
                R.id.no_make_a_video_recording_radioButton -> checkListCleaningApart?.videoRecording =
                    NOT_DONE_CROSS
            }
        }
        temperature_regime_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_temperature_regime_radioButton -> checkListCleaningApart?.temperarureMode = DONE_DAW
                R.id.no_temperature_regime_radioButton -> checkListCleaningApart?.temperarureMode =
                    NOT_DONE_CROSS
            }
        }
        open_window_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_open_window_radioButton -> checkListCleaningApart?.openWindowBridges = DONE_DAW
                R.id.no_open_window_radioButton -> checkListCleaningApart?.openWindowBridges = NOT_DONE_CROSS
            }
        }
        flush_toilet_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_flush_toilet_radioButton -> checkListCleaningApart?.flushToilet = DONE_DAW
                R.id.no_flush_toilet_radioButton -> checkListCleaningApart?.flushToilet = NOT_DONE_CROSS
            }
        }
        collect_all_garbage_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_collect_all_garbage_radioButton -> checkListCleaningApart?.collectGarbage = DONE_DAW
                R.id.no_collect_all_garbage_radioButton -> checkListCleaningApart?.collectGarbage =
                    NOT_DONE_CROSS
            }
        }
        check_forgotten_item_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_check_forgotten_item_radioButton -> {
                    checkListCleaningApart?.checkforgottenItem = AVAILABLE
                    forgotten_item_TIL.layoutParams = visibleLayout()
                }
                R.id.no_check_forgotten_item_radioButton -> {
                    checkListCleaningApart?.checkforgottenItem =
                        NOT_AVAILABLE
                    forgotten_item_TIL.layoutParams = invisibleLayout()
                }
            }
        }

        smart_home_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_smart_home_radioButton -> checkListCleaningApart?.smartHome = DONE_DAW
                R.id.no_smart_home_radioButton -> checkListCleaningApart?.smartHome = NOT_DONE_CROSS
            }
        }
        tv_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_tv_radioButton -> checkListCleaningApart?.tv = DONE_DAW
                R.id.no_tv_radioButton -> checkListCleaningApart?.tv = NOT_DONE_CROSS
            }
        }
        remote_controller_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_remote_controller_radioButton -> checkListCleaningApart?.tvController = DONE_DAW
                R.id.no_remote_controller_radioButton -> checkListCleaningApart?.tvController =
                    NOT_DONE_CROSS
            }
        }
        refrigerator_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_refrigerator_radioButton -> checkListCleaningApart?.refrigerator = DONE_DAW
                R.id.no_refrigerator_radioButton -> checkListCleaningApart?.refrigerator = NOT_DONE_CROSS
            }
        }
        lighting_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_lighting_radioButton -> checkListCleaningApart?.lighting = DONE_DAW
                R.id.no_lighting_radioButton -> checkListCleaningApart?.lighting = NOT_DONE_CROSS
            }
        }
        bluetooth_column_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_bluetooth_column_radioButton -> checkListCleaningApart?.bluetoothColumn = DONE_DAW
                R.id.no_bluetooth_column_radioButton -> checkListCleaningApart?.bluetoothColumn =
                    NOT_DONE_CROSS
            }
        }
        other_equipment_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_other_equipment_radioButton -> checkListCleaningApart?.otherEquipment = DONE_DAW
                R.id.no_other_equipment_radioButton -> checkListCleaningApart?.otherEquipment =
                    NOT_DONE_CROSS
            }
        }
        wash_dishes_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wash_dishes_radioButton -> checkListCleaningApart?.washDishes = DONE_DAW
                R.id.no_wash_dishes_radioButton -> checkListCleaningApart?.washDishes = NOT_DONE_CROSS
            }
        }
        remove_bed_linen_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_remove_bed_linen_radioButton -> checkListCleaningApart?.removeBedLinen = DONE_DAW
                R.id.no_remove_bed_linen_radioButton -> checkListCleaningApart?.removeBedLinen =
                    NOT_DONE_CROSS
            }
        }
        making_bed_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_making_bed_radioButton -> checkListCleaningApart?.makingBed = DONE_DAW
                R.id.no_making_bed_radioButton -> checkListCleaningApart?.makingBed = NOT_DONE_CROSS
            }
        }
        cleaning_hygiene_areas_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_cleaning_hygiene_areas_radioButton -> checkListCleaningApart?.cleaningOfSinksAndHygieneAreas =
                    DONE_DAW
                R.id.no_cleaning_hygiene_areas_radioButton -> checkListCleaningApart?.cleaningOfSinksAndHygieneAreas =
                    NOT_DONE_CROSS
            }
        }
        cleaning_shower_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_cleaning_shower_radioButton -> checkListCleaningApart?.cleaningShowerBath = DONE_DAW
                R.id.no_cleaning_shower_radioButton -> checkListCleaningApart?.cleaningShowerBath =
                    NOT_DONE_CROSS
            }
        }
        cleaning_toilet_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_cleaning_toilet_radioButton -> checkListCleaningApart?.cleaningToilet = DONE_DAW
                R.id.no_cleaning_toilet_radioButton -> checkListCleaningApart?.cleaningToilet =
                    NOT_DONE_CROSS
            }
        }
        changing_towels_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_changing_towels_radioButton -> checkListCleaningApart?.changingTowelsSupplies =
                    DONE_DAW
                R.id.no_changing_towels_radioButton -> checkListCleaningApart?.changingTowelsSupplies =
                    NOT_DONE_CROSS
            }
        }
        wipe_mirror_and_dooor_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wipe_mirror_and_dooor_radioButton -> checkListCleaningApart?.wipeMirrorAndDoor =
                    DONE_DAW
                R.id.no_wipe_mirror_and_dooor_radioButton -> checkListCleaningApart?.wipeMirrorAndDoor =
                    NOT_DONE_CROSS
            }
        }
        wipe_shelves_furniture_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wipe_shelves_furniture_radioButton -> checkListCleaningApart?.wipeShelvesFurnitureInApart =
                    DONE_DAW
                R.id.no_wipe_shelves_furniture_radioButton -> checkListCleaningApart?.wipeShelvesFurnitureInApart =
                    NOT_DONE_CROSS
            }
        }
        wipe_decor_mirror_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wipe_decor_mirror_radioButton -> checkListCleaningApart?.wipeDecorMirrorInApart =
                    DONE_DAW
                R.id.no_wipe_decor_mirror_radioButton -> checkListCleaningApart?.wipeDecorMirrorInApart =
                    NOT_DONE_CROSS
            }
        }
        check_window_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_check_window_radioButton -> {
                    checkListCleaningApart?.checkWindow = DIRTY
                    wash_window_layout_container.layoutParams = visibleLayout()
                }
                R.id.no_check_window_radioButton -> {
                    checkListCleaningApart?.checkWindow = NORMAL
                    checkListCleaningApart?.washWindow = "-"
                    wash_window_layout_container.layoutParams = invisibleLayout()
                }
            }
        }
        wash_window_mirror_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wash_window_mirror_radioButton -> checkListCleaningApart?.washWindow = DONE_DAW
                R.id.no_wash_window_radioButton -> checkListCleaningApart?.washWindow = NOT_DONE_CROSS
            }
        }

        top_guest_accessories_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_top_guest_accessories_radioButton -> checkListCleaningApart?.topGuestAccessries =
                    DONE_DAW
                R.id.no_top_guest_accessories_radioButton -> checkListCleaningApart?.topGuestAccessries =
                    NOT_DONE_CROSS
            }
        }
        remove_floor_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_remove_floor_radioButton -> checkListCleaningApart?.removeFloor = DONE_DAW
                R.id.no_remove_floor_radioButton -> checkListCleaningApart?.removeFloor = NOT_DONE_CROSS
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
