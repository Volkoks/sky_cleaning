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
import com.example.skyapartmentscleaning.data.DONE
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

        bypassing_apart_radiogroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when (checkedId) {
                R.id.yes_bypassing_apart_radioButton -> {
                    cleaningApart?.bypassingApart = DONE
                    video_recording_linear_layout.layoutParams = visibleLayout()
                }
                R.id.no_bypassing_apart_radioButton -> {
                    cleaningApart?.bypassingApart = NOT_DONE
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
        open_window_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_open_window_radioButton -> cleaningApart?.openWindowBridges = DONE
                R.id.no_open_window_radioButton -> cleaningApart?.openWindowBridges = NOT_DONE
            }
        }
        collect_all_garbage_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.yes_collect_all_garbage_radioButton -> cleaningApart?.collectGarbage = DONE
                R.id.no_collect_all_garbage_radioButton -> cleaningApart?.collectGarbage = NOT_DONE
            }
        }
        smart_home_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.yes_smart_home_radioButton->cleaningApart?.smartHome = DONE
                R.id.no_smart_home_radioButton->cleaningApart?.smartHome = NOT_DONE
            }
        }
        tv_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.yes_tv_radioButton->cleaningApart?.tv = DONE
                R.id.no_tv_radioButton->cleaningApart?.tv = NOT_DONE
            }
        }
        remote_controller_cleaning_radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.yes_remote_controller_radioButton->cleaningApart?.tvController = DONE
                R.id.no_remote_controller_radioButton->cleaningApart?.tvController = NOT_DONE
            }
        }



        save_apart_btn.setOnClickListener {
            viewModel.saveApartCleaningReport(apart, cleaningApart)
            activity?.let { it1 -> viewModel.generateCSVFileAndSend(it1, apart) }
            Toast.makeText(activity, "СОХРАНИЛИ", Toast.LENGTH_SHORT).show()
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