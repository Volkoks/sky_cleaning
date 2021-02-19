package com.example.skyapartmentscleaning.ui.checkHistory


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.skyapartmentscleaning.R

/**
 * @author Alexander Volkov (Volkoks)
 */
class CheckHistoryFragment : Fragment(R.layout.check_list_history_fragment) {

    companion object {
        fun newInstance() = CheckHistoryFragment()
    }

    private val viewModel: CheckHistoryViewModel by viewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}