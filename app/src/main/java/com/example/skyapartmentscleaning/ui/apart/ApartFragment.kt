package com.example.skyapartmentscleaning.ui.apart

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.data.*
import com.example.skyapartmentscleaning.data.entites.apart.Apart
import com.example.skycleaning.data.entity.dailyСleaningOfTheApartment.CleaningApart
import kotlinx.android.synthetic.main.check_list_fragment.*

/**
 * @author Alexander Volkov (Volkoks)
 * Данный фрагмент отображает фрагмент чек листа уборки аппартамента - производит запись в БД(через ViewModel), генерацию отправки
 * файла(Пока что CSV файл) в другое приложение.
 */

class ApartFragment : Fragment(R.layout.check_list_fragment) {

    companion object {
        fun newInstance(apart: Apart, cleaningApart: CleaningApart) = ApartFragment().apply {
            arguments = Bundle().apply {
                putParcelable(APART, apart)
                putParcelable(CLEANING_APART, cleaningApart)
            }
        }

    }

    private var apart: Apart? = null
    private var cleaningApart: CleaningApart? = null
    private val viewModel: ApartViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apart = arguments?.getParcelable(APART)
        cleaningApart = arguments?.getParcelable(CLEANING_APART)
        activity?.title = "Апартамент №: ${apart?.numberApart}"
        tv_clf_date_cleaning.setText(viewModel.getCurrentFormattedDate(apart))
        handlingRadioBtnClicks()
        mb_clf_send_report.setOnClickListener { saveAndSendFileReport() }
    }

    override fun onStop() {
        super.onStop()
        activity?.title = APP_NAME
    }


    /**
     * Функция сохранения и отправки файла отчёта
     */
    private fun saveAndSendFileReport() {
        getEditTextCheckList()
        dateFormationForApart()
        viewModel.saveApartCleaningReport(apart, cleaningApart)
        activity?.let { it1 -> viewModel.generateCSVFileAndSend(it1, apart, cleaningApart) }
        Toast.makeText(activity, "Отчёт сохранен", Toast.LENGTH_SHORT).show()
    }

    /**
     * Метод формирования и записи даты для экземпляра Apart.kt
     */
    private fun dateFormationForApart() {
        apart?.checkDate = viewModel.getCurrentFormattedDate(apart)
        apart?.id = "${apart?.numberApart}/" + viewModel.getCurrentFormattedDate(apart)
    }

    /**
     * Метод получения того что написано в EditText Чек листов
     */
    private fun getEditTextCheckList() {
        cleaningApart?.cleaningComment = tiet_clf_comment.text.toString()
        cleaningApart?.forgottenItem = tiet_clf_forgotten_item.text.toString()
    }

    /**
     * Метод обработки радио-кнопок в чек листе(запись значений в локальный экземпляр CleaningApart)
     */
    private fun handlingRadioBtnClicks() {

        cg_clf_bypassing_apart.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_bypassing_apart_chip -> {
                    cleaningApart?.bypassingApart = DIRTY
                    tv_clf_text_video_recording.visibility = ViewGroup.VISIBLE
                    cg_clf_video_recording.visibility = ViewGroup.VISIBLE
                }
                R.id.no_bypassing_apart_chip -> {
                    cleaningApart?.bypassingApart = NORMAL
                    cleaningApart?.videoRecording = "-"
                    tv_clf_text_video_recording.visibility = ViewGroup.GONE
                    cg_clf_video_recording.visibility = ViewGroup.GONE
                }
            }
        }

        cg_clf_video_recording.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_video_recording_chip -> cleaningApart?.videoRecording =
                    DONE_DAW
                R.id.no_video_recording_chip -> cleaningApart?.videoRecording =
                    NOT_DONE_CROSS
            }
        }
        cg_clf_temperature_regime.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_temperature_regime_chip -> cleaningApart?.temperarureMode = DONE_DAW
                R.id.no_temperature_regime_chip -> cleaningApart?.temperarureMode =
                    NOT_DONE_CROSS
            }
        }
        cg_clf_open_window.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_open_window_chip -> cleaningApart?.openWindowBridges = DONE_DAW
                R.id.no_open_window_chip -> cleaningApart?.openWindowBridges = NOT_DONE_CROSS
            }
        }
        cg_clf_flush_toilet.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_flush_toilet_chip -> cleaningApart?.flushToilet = DONE_DAW
                R.id.no_flush_toilet_chip -> cleaningApart?.flushToilet = NOT_DONE_CROSS
            }
        }
        cg_clf_collect_all_garbage_cleaning.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_collect_all_garbage_cleaning_chip -> cleaningApart?.collectGarbage =
                    DONE_DAW
                R.id.no_collect_all_garbage_cleaning_chip -> cleaningApart?.collectGarbage =
                    NOT_DONE_CROSS
            }
        }
        cg_clf_check_forgotten_item.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_check_forgotten_item_chip -> {
                    cleaningApart?.checkforgottenItem = AVAILABLE
                    til_clf_forgotten_item.visibility = ViewGroup.VISIBLE
                }
                R.id.no_check_forgotten_item_chip -> {
                    cleaningApart?.checkforgottenItem =
                        NOT_AVAILABLE
                    til_clf_forgotten_item.visibility = ViewGroup.GONE
                }
            }
        }

        cg_clf_smart_home.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_smart_home_chip -> cleaningApart?.smartHome = DONE_DAW
                R.id.no_smart_home_chip -> cleaningApart?.smartHome = NOT_DONE_CROSS
            }
        }

        cg_clf_tv.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_tv_chip -> cleaningApart?.tv = DONE_DAW
                R.id.no_tv_chip -> cleaningApart?.tv = NOT_DONE_CROSS
            }
        }
        cg_clf_remote_contoller.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_remote_contoller_chip -> cleaningApart?.tvController = DONE_DAW
                R.id.no_remote_contoller_chip -> cleaningApart?.tvController =
                    NOT_DONE_CROSS
            }
        }
        cg_clf_refrigerator.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_refrigerator_chip -> cleaningApart?.refrigerator = DONE_DAW
                R.id.no_refrigerator_chip -> cleaningApart?.refrigerator = NOT_DONE_CROSS
            }
        }
        cg_clf_lightning.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_lightning_chip -> cleaningApart?.lighting = DONE_DAW
                R.id.no_lightning_chip -> cleaningApart?.lighting = NOT_DONE_CROSS
            }
        }
        cg_clf_bluetooth_column.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_bluetooth_column_chip -> cleaningApart?.bluetoothColumn = DONE_DAW
                R.id.no_bluetooth_column_chip -> cleaningApart?.bluetoothColumn =
                    NOT_DONE_CROSS
            }
        }
        cg_clf_other_equipment.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_other_equipment_chip -> cleaningApart?.otherEquipment = DONE_DAW
                R.id.no_other_equipment_chip -> cleaningApart?.otherEquipment =
                    NOT_DONE_CROSS
            }
        }
        cg_clf_wash_dishes.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wash_dishes_chip -> cleaningApart?.washDishes = DONE_DAW
                R.id.no_wash_dishes_chip -> cleaningApart?.washDishes = NOT_DONE_CROSS
            }
        }
        cg_clf_remove_bed_linen.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_remove_bed_linen_chip -> cleaningApart?.removeBedLinen = DONE_DAW
                R.id.no_remove_bed_linen_chip -> cleaningApart?.removeBedLinen =
                    NOT_DONE_CROSS
            }
        }
        cg_clf_making_bed.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_making_bed_chip -> cleaningApart?.makingBed = DONE_DAW
                R.id.no_making_bed_chip -> cleaningApart?.makingBed = NOT_DONE_CROSS
            }
        }
        cg_clf_cleaning_hygiene_areas.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_cleaning_hygiene_areas_chip -> cleaningApart?.cleaningOfSinksAndHygieneAreas =
                    DONE_DAW
                R.id.no_cleaning_hygiene_areas_chip -> cleaningApart?.cleaningOfSinksAndHygieneAreas =
                    NOT_DONE_CROSS
            }
        }
        cg_clf_cleaning_shower.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_cleaning_shower_chip -> cleaningApart?.cleaningShowerBath = DONE_DAW
                R.id.no_cleaning_shower_chip -> cleaningApart?.cleaningShowerBath =
                    NOT_DONE_CROSS
            }
        }
        cg_clf_cleaning_toilet.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_cleaning_toilet_chip -> cleaningApart?.cleaningToilet = DONE_DAW
                R.id.no_cleaning_toilet_chip -> cleaningApart?.cleaningToilet =
                    NOT_DONE_CROSS
            }
        }
        cg_clf_changing_towels.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_changing_towels_chip -> cleaningApart?.changingTowelsSupplies =
                    DONE_DAW
                R.id.no_changing_towels_chip -> cleaningApart?.changingTowelsSupplies =
                    NOT_DONE_CROSS
            }
        }
        cg_clf_wipe_mirror_and_door.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wipe_mirror_and_dooor_chip -> cleaningApart?.wipeMirrorAndDoor =
                    DONE_DAW
                R.id.no_wipe_mirror_and_dooor_chip -> cleaningApart?.wipeMirrorAndDoor =
                    NOT_DONE_CROSS
            }
        }
        cg_clf_wipe_shelves_furniture.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wipe_shelves_furniture_chip -> cleaningApart?.wipeShelvesFurnitureInApart =
                    DONE_DAW
                R.id.no_wipe_shelves_furniture_chip -> cleaningApart?.wipeShelvesFurnitureInApart =
                    NOT_DONE_CROSS
            }
        }
        cg_clf_wipe_decor_mirror.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wipe_decor_mirror_chip -> cleaningApart?.wipeDecorMirrorInApart =
                    DONE_DAW
                R.id.no_wipe_decor_mirror_chip -> cleaningApart?.wipeDecorMirrorInApart =
                    NOT_DONE_CROSS
            }
        }
        cg_clf_check_window.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_check_window_chip -> {
                    cleaningApart?.checkWindow = DIRTY
                    tv_clf_text_wash_window.visibility = ViewGroup.VISIBLE
                    cg_clf_wash_window.visibility = ViewGroup.VISIBLE
                }
                R.id.no_check_window_chip -> {
                    cleaningApart?.checkWindow = NORMAL
                    cleaningApart?.washWindow = "-"
                    tv_clf_text_wash_window.visibility = ViewGroup.GONE
                    cg_clf_wash_window.visibility = ViewGroup.GONE

                }
            }
        }
        cg_clf_wash_window.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_wash_window_chip -> cleaningApart?.washWindow = DONE_DAW
                R.id.no_wash_window_chip -> cleaningApart?.washWindow = NOT_DONE_CROSS
            }
        }

        cg_clf_top_guest_accessories.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_top_guest_accessories_chip -> cleaningApart?.topGuestAccessries =
                    DONE_DAW
                R.id.no_top_guest_accessories_chip -> cleaningApart?.topGuestAccessries =
                    NOT_DONE_CROSS
            }
        }
        cg_clf_remove_floor.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_remove_floor_chip -> cleaningApart?.removeFloor = DONE_DAW
                R.id.no_remove_floor_chip -> cleaningApart?.removeFloor = NOT_DONE_CROSS
            }
        }
    }


    override fun onPause() {
        apart?.checkDate = ""
        super.onPause()
    }

}
