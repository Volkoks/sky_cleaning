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
import com.example.skyapartmentscleaning.data.entites.apart.Apart
import kotlinx.android.synthetic.main.apart_fragment.*


class ApartFragment : Fragment() {

    companion object {
        fun newInstance(apart: Apart): ApartFragment {
            val fragment = ApartFragment()
            val bundle = Bundle()
            bundle.putParcelable("apart", apart)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var apart: Apart? = null
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
        number_apart_fragment.setText(apart?.numberApart.toString())
        date_test.setText(viewModel.getCurrentFormattedDate(apart))
        bypassing_apart_radiogroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId){
                R.id.yes_bypassing_apart_radioButton->{
                    video_recording_linear_layout.layoutParams = visibleLayout()
                }
                R.id.no_bypassing_apart_radioButton->{
                    video_recording_linear_layout.layoutParams =
                        invisibleLayout()
                }
            }
        }
        save_apart_btn.setOnClickListener {
            viewModel.saveApartCleaningReport(apart)
            activity?.let { it1 -> viewModel.generateCSVFileAndSend(it1,apart) }
            Toast.makeText(activity, "СОХРАНИЛИ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun invisibleLayout() =
        LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0)

    private fun visibleLayout(): ViewGroup.LayoutParams? {
        return LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onPause() {
        apart?.checkDate = ""
        super.onPause()
    }

}