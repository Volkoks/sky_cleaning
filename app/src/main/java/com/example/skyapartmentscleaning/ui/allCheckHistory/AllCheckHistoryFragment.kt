package com.example.skyapartmentscleaning.ui.allCheckHistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.ui.allApart.AllApartsAdapter
import kotlinx.android.synthetic.main.all_check_history_fragment.*


class AllCheckHistoryFragment : Fragment() {


    companion object {
        fun newInstance() = AllCheckHistoryFragment()
    }

    lateinit var adapter: AllApartsAdapter
    private val viewModelAll: AllCheckHistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.all_check_history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        check_history_RecyclerView.setHasFixedSize(true)
        check_history_RecyclerView.layoutManager = GridLayoutManager(activity,2)
        adapter = AllApartsAdapter{ }
        check_history_RecyclerView.adapter = adapter
        viewModelAll.verifiedApartments.observe(viewLifecycleOwner,{
            it?.let {
                adapter.listAparts = it.listApart
            }
        })
    }

}