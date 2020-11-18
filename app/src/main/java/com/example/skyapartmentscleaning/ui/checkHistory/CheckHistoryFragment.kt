package com.example.skyapartmentscleaning.ui.checkHistory


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.data.APART
import com.example.skyapartmentscleaning.data.entites.apart.Apart
import com.example.skycleaning.data.entity.dailyСleaningOfTheApartment.CheckListCleaningApart
import kotlinx.android.synthetic.main.check_history_fragment.*

/**
 * @author Alexander Volkov (Volkoks)
 */
class CheckHistoryFragment : Fragment() {

    companion object {
        fun newInstance(apart: Apart): CheckHistoryFragment {
            var fragment = CheckHistoryFragment()
            val bundle = Bundle()
            bundle.putParcelable(APART, apart)
            fragment.arguments = bundle
            return fragment
        }
    }

    private val viewModel: CheckHistoryViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.check_history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apart = arguments?.getParcelable(APART)
        viewModel.loadData()

        viewModel.checkList.observe(viewLifecycleOwner,{
            init(it)
        })


    }

    private fun init(checkList:CheckListCleaningApart) {
        number_apart_check_list_tv.text = checkList?.id
        commentaries_tv.text = checkList?.cleaningComment
    }

}