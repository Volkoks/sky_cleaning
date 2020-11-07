package com.example.skyapartmentscleaning.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.ui.allApart.AllApartmentsFragment
import com.example.skyapartmentscleaning.ui.allCheckHistory.AllCheckHistoryFragment
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        apartments_btn.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.fragment_container, AllApartmentsFragment.newInstance())
                .addToBackStack("AllApart")
                .commit()
        }
        floatingActionButton.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.fragment_container, AllApartmentsFragment.newInstance())
                .addToBackStack("AllApart")
                .commit()
        }
        check_history_apartments_btn.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.fragment_container, AllCheckHistoryFragment.newInstance())
                .addToBackStack("AllCheckHistoryApart")
                .commit()
        }
    }

}